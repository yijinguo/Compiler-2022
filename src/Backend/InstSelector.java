package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFunction;
import Assembly.ASMProgram;
import Assembly.Inst.*;
import Assembly.Operand.*;
import MIR.Statmemt.*;
import MIR.entity.*;
import MIR.terminalStmt.*;
import MIR.type.IRClass;
import MIR.type.IRInt;
import Middlend.IRBuilder;
import MIR.*;

import java.util.HashMap;
import java.util.Map;

public class InstSelector implements IRVisitor {

    public ASMProgram program = new ASMProgram(); //保存需要print的内容

    public ASMFunction currFunc = new ASMFunction(null);
    public ASMBlock currBlk = new ASMBlock(null);
    public HashMap<block, ASMBlock> blockList = new HashMap<>();

    public InstSelector(IRBuilder ir){
        for (Map.Entry<String, entity> x : ir.gScope.entities.entrySet()) visitGlobal(x.getValue());
        for (Map.Entry<String, function> f : ir.functions.entrySet()) f.getValue().accept(this);
    }

    public Reg getReg(entity e){
        if (e.asmReg != null) return e.asmReg;
        if (e instanceof constant) {
            e.asmReg = new VirtualImm((constant) e);
        } else { //register
            e.asmReg = new VirtualReg(e.irType.size);
        }
        return e.asmReg;
    }

    public void LoadReg(int type, Reg dest, Reg src, int offset){
        if (offset < 1 << 11) {
            currBlk.addInst(new Load(type, dest, src, new Imm(offset)));
        } else {
            VirtualReg t = new VirtualReg(4);
            currBlk.addInst(new Binary("add", t, src, new VirtualImm(offset)));
            currBlk.addInst(new Load(type, dest, t));
        }
    }

    public void StoreReg(int type, Reg value, Reg dest, int offset){
        //value: rs2; dest:rs1
        if (offset < 1 << 11) {
            currBlk.addInst(new Store(type, dest, value, new Imm(offset)));
        } else {
            VirtualReg t = new VirtualReg(4);
            currBlk.addInst(new Binary("add", t, dest, new VirtualImm(offset)));
            currBlk.addInst(new Store(type, t, value));
        }

    }

    public void visitGlobal(entity x){
        if (x instanceof consString) {
            program.globals.add(new GlobalString((consString) x));
        } else { //globalVar
            program.globals.add(new GlobalValue((globalVar) x));
        }
    }

    public void visit(function it){
        currFunc = new ASMFunction(it.funcName);
        blockList.clear();
        VirtualReg.cnt = 0;

        int maxCnt = 0;
        for (block blk : it.blocks) {
            blockList.put(blk, new ASMBlock(blk.label == 0 ? null : ".L" + blk.label));
            for (statement inst : blk.stmtList)
                if (inst instanceof call)
                    maxCnt = Math.max(maxCnt, ((call) inst).paramList.size());
        }
        currFunc.paramsUsed = (maxCnt > 8 ? maxCnt - 8 : 0) << 2;

        for (int i = 0; i < it.paraList.size(); ++i) {
            if (i < 8) {
                it.paraList.get(i).asmReg = PhyReg.regMap.get("a" + i);
            } else {
                //todo
                //这里该怎么申请VirtualReg？
                it.paraList.get(i).asmReg = new VirtualReg(4);
            }
        }

        for (int i = 0; i < it.blocks.size(); ++i) {
            currBlk = blockList.get(it.blocks.get(i));
            if (i == 0)
                StoreReg(4, PhyReg.regMap.get("ra"), PhyReg.regMap.get("sp"), currFunc.paramsUsed);
            it.blocks.get(i).accept(this);
            currFunc.Blocks.add(currBlk);
            currBlk = null;
        }
        currFunc.virtualRegCnt = VirtualReg.cnt;
        currFunc.totalStack = currFunc.paramsUsed + currFunc.allocaUsed + currFunc.virtualRegCnt * 4 + 4;

        ASMBlock firstBlk = currFunc.Blocks.get(0), lastBlk = currFunc.Blocks.get(currFunc.Blocks.size() - 1);
        if (currFunc.totalStack < 1 << 11) {
            firstBlk.insts.addFirst(new Unary("addi", PhyReg.regMap.get("sp"), PhyReg.regMap.get("sp"), new Imm(-currFunc.totalStack)));
            lastBlk.insts.add(new Unary("addi", PhyReg.regMap.get("sp"), PhyReg.regMap.get("sp"), new Imm(currFunc.totalStack)));
        } else {
            firstBlk.insts.addFirst(new Binary("add", PhyReg.regMap.get("sp"), PhyReg.regMap.get("sp"), new VirtualImm(-currFunc.totalStack)));
            lastBlk.insts.add(new Binary("add", PhyReg.regMap.get("sp"), PhyReg.regMap.get("sp"), new VirtualImm(currFunc.totalStack)));
        }

        program.functions.add(currFunc);
        currFunc = null;
    }
    public void visit(block it){
        it.stmtList.forEach(x->x.accept(this));
    }

    //statement
    public void visit(alloca it) {
        currFunc.allocaUsed += 4;
        int imm = currFunc.paramsUsed + currFunc.allocaUsed;
        if (imm < 1 << 11) {
            currBlk.addInst(new Unary("addi", getReg(it.dest), PhyReg.regMap.get("sp"),
                    new Imm(currFunc.paramsUsed + currFunc.allocaUsed)));
        } else {
            currBlk.addInst(new Binary("add", getReg(it.dest), PhyReg.regMap.get("sp"),
                    new VirtualImm(currFunc.paramsUsed + currFunc.allocaUsed)));
        }
    }
    public void visit(load it){
        LoadReg(it.cont.irType.size, getReg(it.dest), getReg(it.cont), 0);
    }
    public void visit(store it){
        StoreReg(it.cont.irType.size, getReg(it.cont), getReg(it.dest), 0);
    }
    public void visit(binary it){
        switch (it.op) {
            case "add", "and", "or", "xor": {
                if (it.op1 instanceof consInt) {
                    entity tmp = it.op1;
                    it.op1 = it.op2;
                    it.op2 = tmp;
                }
                if (it.op2 instanceof consInt && ((consInt) it.op2).value < 1<<11
                        && ((consInt) it.op2).value >= -(1<<11)) {
                    currBlk.addInst(new Unary(it.op + "i", getReg(it.lhs), getReg(it.op1), new Imm(((consInt) it.op2).value)));
                    return;
                }
            }
            default:
                currBlk.addInst(new Binary(it.op, getReg(it.lhs), getReg(it.op1), getReg(it.op2)));
        }
    }

    public void visit(icmp it){
        VirtualReg tmp = new VirtualReg(4);
        switch (it.op) {
            case "slt" -> currBlk.addInst(new Binary("slt", getReg(it.dest), getReg(it.lhs), getReg(it.rhs)));
            case "sgt" -> currBlk.addInst(new Binary("slt", getReg(it.dest), getReg(it.rhs), getReg(it.lhs)));
            case "sle" -> {
                currBlk.addInst(new Binary("slt", tmp, getReg(it.rhs), getReg(it.lhs)));
                currBlk.addInst(new Unary("xori", getReg(it.dest), tmp, new Imm(1)));
            }
            case "sge" -> {
                currBlk.addInst(new Binary("slt", tmp, getReg(it.lhs), getReg(it.rhs)));
                currBlk.addInst(new Unary("xori", getReg(it.dest), tmp, new Imm(1)));
            }
            case "eq" -> {
                currBlk.addInst(new Binary("sub", tmp, getReg(it.lhs), getReg(it.rhs)));
                currBlk.addInst(new Unary("seqz", getReg(it.dest), tmp));
            }
            case "ne" -> {
                currBlk.addInst(new Binary("sub", tmp, getReg(it.lhs), getReg(it.rhs)));
                currBlk.addInst(new Unary("snez", getReg(it.dest), tmp));
            }
            default -> {}
        }
    }
    public void visit(zext it){}
    public void visit(call it){
        for (int i = 0; i < it.paramList.size(); ++i) {
            entity e = it.paramList.get(i);
            if (i < 8) {
                currBlk.addInst(new Mv(PhyReg.regMap.get("a" + i), getReg(e)));
            } else {
                StoreReg(e.irType.size, getReg(e), PhyReg.regMap.get("sp"), i - 8 << 2);
            }
        }
        currBlk.addInst(new Call(it.className == null ? it.functionName : it.className + "::" + it.functionName));
        //if (!(it.returnReg.irType instanceof IRVoid))
        if (it.returnReg != null)
            currBlk.addInst(new Mv(getReg(it.returnReg), PhyReg.regMap.get("a0")));
    }
    public void visit(getelementptr it){
        if (it.ptrType instanceof IRInt && it.ptrType.size == 1) {
            currBlk.addInst(new Binary("add", getReg(it.dest), getReg(it.ptr), getReg(it.indexList.get(0))));
        } else {
            Reg idx = it.ptrType instanceof IRClass ? getReg(it.indexList.get(1)) : getReg(it.indexList.get(0));
            VirtualReg t = new VirtualReg(4);
            currBlk.addInst(new Unary("slli", t, idx, new Imm(2)));
            currBlk.addInst(new Binary("add", getReg(it.dest), getReg(it.ptr), t));
        }
    }
    public void visit(cast it){
        currBlk.addInst(new Mv(getReg(it.dest), getReg(it.val)));
    }

    //terminal
    public void visit(branch it){
        currBlk.addInst(new Beqz(getReg(it.op), blockList.get(it.falseBranch)));
        currBlk.addInst(new Jump(blockList.get(it.trueBranch)));
    }
    public void visit(jump it){
        currBlk.addInst(new Jump(blockList.get(it.destination)));
    }
    public void visit(ret it){
        if (!(it.value instanceof consNull))
            currBlk.addInst(new Mv(PhyReg.regMap.get("a0"), getReg(it.value)));
        LoadReg(4, PhyReg.regMap.get("ra"), PhyReg.regMap.get("sp"), currFunc.paramsUsed);
    }
    public void visit(loop it){
        currBlk.addInst(new Jump(blockList.get(it.condition)));
    }
    
}
