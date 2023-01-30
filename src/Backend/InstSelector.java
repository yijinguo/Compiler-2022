package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFunction;
import Assembly.ASMProgram;
import Assembly.Inst.*;
import Assembly.Operand.*;
import MIR.Statmemt.*;
import MIR.entity.*;
import MIR.terminalStmt.*;
import MIR.type.IRVoid;
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
        String funcName = it.isInClass ? it.className + "::" + it.funcName : it.funcName;
        currFunc = new ASMFunction(funcName);
        //todo
        //paramsUsed
        blockList.clear();
        int l = 0;
        for (block b : it.blocks) blockList.put(b, new ASMBlock(b.label == 0 ? "" : ".LBB1_" + (++l)));
        it.blocks.forEach(x->x.accept(this));

        program.functions.add(currFunc);
        currFunc = null;
    }
    public void visit(block it){
        currBlk = blockList.get(it);
        it.stmtList.forEach(x->x.accept(this));
        currBlk = null;
    }

    //statement
    public void visit(alloca it){
        currBlk.addInst(new Binary("add", getReg(it.dest), PhyReg.regMap.get("sp"),
                new VirtualImm(currFunc.paramsUsed + currFunc.allocaUsed)));
        currFunc.allocaUsed += 4;
    }
    public void visit(load it){
        LoadReg(it.cont.irType.size, getReg(it.dest), getReg(it.cont), 0);
    }
    public void visit(store it){
        StoreReg(it.cont.irType.size, getReg(it.cont), getReg(it.dest), 0);
    }
    public void visit(binary it){
        currBlk.addInst(new Binary(it.op, getReg(it.lhs), getReg(it.op1), getReg(it.op2)));
    }
    public void visit(unary it){
        currBlk.addInst(new Unary(it.op, getReg(it.dest), getReg(it.cont), new Imm(((consInt)it.cons).value)));
    }
    public void visit(icmp it){
        VirtualReg tmp = new VirtualReg(4);
        switch (it.op) {
            case "slt" -> currBlk.addInst(new Binary("slt", getReg(it.dest), getReg(it.lhs), getReg(it.rhs)));
            case "sqt" -> currBlk.addInst(new Binary("slt", getReg(it.dest), getReg(it.rhs), getReg(it.lhs)));
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
    public void visit(call it){
        for (int i = 0; i < it.paramList.size(); ++i) {
            entity e = it.paramList.get(i);
            if (i < 8) {
                currBlk.addInst(new Mv(PhyReg.regMap.get("a" + i), getReg(e)));
            } else {
                //todo
                //不确定，再看看
                StoreReg(e.irType.size, getReg(e), PhyReg.regMap.get("sp"), i - 8 << 2);
            }
        }
        currBlk.addInst(new Call(it.className == null ? it.functionName : it.className + "::" + it.functionName));
        if (!(it.returnReg.irType instanceof IRVoid))
            currBlk.addInst(new Mv(getReg(it.returnReg), PhyReg.regMap.get("a0")));
    }
    public void visit(getelementptr it){

    }
    public void visit(createPtr it){

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
