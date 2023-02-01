package Middlend;

import MIR.*;

import java.io.PrintStream;
import java.util.Map;

import MIR.Statmemt.*;
import MIR.entity.*;
import MIR.terminalStmt.*;
import MIR.type.IRClass;
import MIR.type.IRPtr;
import MIR.type.IRType;

public class IRPrinter implements IRVisitor{
    private PrintStream out;

    public IRPrinter(PrintStream out){
        this.out = out;
    }

    public void printIR(IRBuilder IR){
        //全局变量
        for (Map.Entry<String, entity> entry : IR.gScope.entities.entrySet()){
            if (entry.getValue() instanceof globalVar) {
                globalVar it = (globalVar) entry.getValue();
                out.print(it + " = global " + it.init.printWithType() + "\n");
            }
            //out.print(entry.getValue().toString() + "\n");
        }
        out.print("\n");
        IR.classTypes.values().forEach(this::visitClass);
        //函数
        IR.functions.values().forEach(x->x.accept(this));
        //类定义
        out.close();
    }

    public void visitClass(IRClass x){
        out.print(x.name + " = type { ");
        boolean first = true;
        for (IRType t : x.memberType) {
            if (first) first = false;
            else out.print(", ");
            out.print(t);
        }
        out.print(" }\n");
    }


    public void visit(function it){
        out.print("define " + it.returnType + " @" + it.funcName + "(");
        boolean first = true;
        for (entity e : it.entities) {
            if (first) first = false;
            else out.print(", ");
            out.print(e.printWithType());
        }
        out.print(") {\n");
        it.blocks.forEach(x->x.accept(this));
        out.print("}\n\n");
    }

    public void visit(block it){
        if (it.label != 0) out.print(it + ":\n");
        it.stmtList.forEach(x->x.accept(this));
        out.print("\n");
    }

    //statement
    public void visit(alloca it){
        out.print("\t" + it.dest + " = alloca " + it.type + "\n");
    }
    public void visit(load it){
        out.print("\t" + it.dest + " = load " + it.dest.irType + ", " + it.cont.printWithType() + "\n");
    }
    public void visit(store it){
        out.print("\tstore " + it.cont.printWithType() + ", " + it.dest.printWithType() + "\n");
    }
    public void visit(binary it){
        out.print("\t" + it.lhs + " = " + it.op + " " + it.lhs.irType + " " + it.op1 + ", " + it.op2 + "\n");
    }
    public void visit(unary it){}
    public void visit(icmp it){
        out.print("\t" + it.dest + " = icmp " + it.op + " " + it.lhs.irType + " " + it.lhs + ", " + it.rhs + "\n");
    }
    public void visit(zext it){
        out.print("\t" + it.dest + " = zext " + it.cont.printWithType() + " to " + it.dest.irType + "\n");
    }
    public void visit(call it){
        out.print("\t" + it.returnReg + " = call " + it.returnReg.irType + " @" + it.functionName + "(");
        boolean first = true;
        for (entity e : it.paramList) {
            if (first) first = false;
            else out.print(", ");
            out.print(e.printWithType());
        }
        out.print(")\n");
    }
    public void visit(getelementptr it){
        out.print("\t" + it.dest + " = getelementptr " + it.ptrType + ", " + it.ptr.printWithType());
        for (entity e : it.indexList) {
            out.print(", " + e.printWithType());
        }
        out.print("\n");
    }

    public void visit(cast it) {
        out.print("\t" + it.dest + " = bitcast " + it.val.printWithType() + " to " + it.dest.irType + "\n");
    }

    //terminal
    public void visit(branch it){
        out.print("\tbr " + it.op.printWithType() + ", label %" + it.trueBranch
                + ", label %" + it.falseBranch + "\n");
    }
    public void visit(jump it){
        out.print("\tbr label %" + it.destination + "\n");
    }
    public void visit(ret it){
        out.print("\tret " + it.value.printWithType() + "\n");
    }
    public void visit(loop it){
        out.print("\tbr label %" + it.condition + "\n");
    }


    /*
    
    //statement
    public void visit(alloca it){
        out.print("\t%" + it.dest.reg_num + " = alloca " + it.dest.irType + "\n");
    }
    public void visit(load it){
        out.print("\t" + getEntity(it.dest) + " = load " + it.dest.irType + ", " + it.cont.irType + "* " + getEntity(it.cont) + "\n");
    }
    public void visit(store it){
        out.print("\tstore " + getWithType(it.dest) + ", " + it.cont.irType + "*" + getEntity(it.cont) + "\n");
    }
    public void visit(binary it){
        out.print("\t" + getEntity(it.lhs) + " = " + it.op + " " + it.lhs.irType +
                " " + getEntity(it.op1) + ", " + getEntity(it.op2) + "\n");
    }
    public void visit(unary it){
        out.print("\t" + getEntity(it.dest) + " = " + it.op + " " + it.dest.irType +
                " " + getEntity(it.cont) + ", " + getEntity(it.cons) + "\n");
    }
    public void visit(icmp it){
        out.print("\t" + getEntity(it.dest) + " = icmp" + it.op + " " + it.dest.irType +
                " " + getEntity(it.lhs) + ", " + getEntity(it.rhs) + "\n");
    }
    public void visit(call it){
        out.print("\t" + getEntity(it.returnReg) + " = call " + it.returnReg.irType + " @" + it.functionName + "\n");
    }
    public void visit(getelementptr it){
        out.print("\t" + getEntity(it.dest) + " = getelementptr " + it.dest.irType + ", "
                + getWithType(it.head) + ", "
                + getWithType(it.tail) + "\n");
    }
    public void visit(createPtr it){
        //todo

        //need modify
        //operator new[](unsigned int)
        out.print("\t" + getEntity(it.dest) + " = call operator new[](unsigned int), " + it.size.irType.toString() + "\n");
    }







     */
}
