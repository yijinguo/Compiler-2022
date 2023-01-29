package Backend;
/*
import Assembly.ASMBlock;
import Assembly.ASMFunction;
import Assembly.ASMProgram;
import Assembly.Inst.*;
import Assembly.Operand.*;
import MIR.Statmemt.*;
import MIR.entity.constant;
import MIR.entity.entity;
import MIR.entity.register;
import MIR.terminalStmt.*;
import Middlend.IRBuilder;
import MIR.*;

import java.util.HashMap;
import java.util.Map;


public class InstSelect implements IRVisitor{

    public ASMProgram program = new ASMProgram(); //保存需要print的内容

    public ASMFunction currFunc = new ASMFunction(null);
    public ASMBlock currBlk = new ASMBlock(null);
    public HashMap<block, ASMBlock> blockList = new HashMap<>();

    public InstSelect(IRBuilder ir){
        for (Map.Entry<String, entity> x : ir.gScope.entities.entrySet()) visitGlobal(x.getValue());
        for (Map.Entry<String, function> f : ir.functions.entrySet()) f.getValue().accept(this);
    }

    public Reg getReg(entity e){
        //todo
    }

    public Reg getNewReg(entity e) {
        //todo
    }

    public void LoadReg(Reg r){

    }

    public void StoreReg(Reg r){

    }

    public void visitGlobal(entity x){
        if (x instanceof constant) {
            program.globals.add(new GlobalString((constant) x));
        } else {
            program.globals.add(new GlobalValue((register) x));
        }
    }

    public void visit(function f) {
        String funcName = (f.isInClass) ? (f.className + "::" + f.funcName) : f.funcName;
        currFunc = new ASMFunction(funcName);
        //todo
        //param
        blockList.clear();
        for (block b : f.blocks) {
            if (b.label == 0) blockList.put(b, new ASMBlock(""));
            else blockList.put(b, new ASMBlock(".LBB1_" + b.label));
        }
        currBlk = blockList.get(f.rootBlock);
        f.rootBlock.accept(this);
        //todo
        program.functions.add(currFunc);
        currFunc = null;
    }

    public void visit(block b){
        b.stmtList.forEach(x->x.accept(this));
        b.tailStmt.accept(this);
    }

    //statement
    public void visit(assign it){
        currBlk.addInst(new Mv(getReg(it.lhs), getReg(it.rhs)));
    }

    public void visit(binary it){
        currBlk.addInst(new Binary(it.op, getReg(it.lhs), getReg(it.op1), getReg(it.op2)));
    }

    public void visit(call it){
        for (int i = 0; i < it.paramList.size(); i++) {
            entity x = it.paramList.get(i);
            if (i<8) {
                currBlk.addInst(new Mv(PhyReg.regMap.get("a" + i),getReg(x)));
            } else {

            }
        }
        currBlk.addInst(new Call((it.className.equals("")) ? (it.functionName) : (it.className + "::" + it.functionName)));
        if (it.returnReg.irType.irType != type.IRType.VOID)
            currBlk.addInst(new Mv(getReg(it.returnReg), PhyReg.regMap.get("a0")));
    }

    public void visit(unary it){}

    public void visit(varDef it){}

    //terminal
    public void visit(branch it){
        currBlk.addInst(new Beqz(getReg(it.op), blockList.get(it.falseBranch)));
        currBlk.addInst(new Jump(blockList.get(it.trueBranch)));
        currBlk.tailBlock.add(blockList.get(it.trueBranch));
        currBlk.tailBlock.add(blockList.get(it.falseBranch));
        blockList.get(it.trueBranch).headBlock = blockList.get(it.falseBranch).headBlock = currBlk;
        currBlk = blockList.get(it.trueBranch);
        it.trueBranch.accept(this);
        currBlk = blockList.get(it.falseBranch);
        it.falseBranch.accept(this);
    }

    public void visit(jump it){
        currBlk.addInst(new Jump(blockList.get(it.destination)));
        currBlk.tailBlock.add(blockList.get(it.destination));
        blockList.get(it.destination).headBlock = currBlk;
        currBlk = blockList.get(it.destination);
        it.destination.accept(this);
    }

    public void visit(ret it){
        currBlk.addInst(new Ret());
    }

    public void visit(loop it) {
        currBlk.addInst(new Jump(blockList.get(it.condition)));
        currBlk.tailBlock.add(blockList.get(it.condition));
        blockList.get(it.condition).headBlock = currBlk;
        currBlk = blockList.get(it.condition);
        it.condition.accept(this);
        currBlk.tailBlock.add(blockList.get(it.loopBlk));
        blockList.get(it.loopBlk).headBlock = currBlk;
        currBlk = blockList.get(it.loopBlk);
        it.loopBlk.accept(this);
    }

}


 */