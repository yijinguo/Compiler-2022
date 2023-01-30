package Middlend;

import MIR.*;

import java.io.PrintStream;
import java.util.Map;

import MIR.Statmemt.*;
import MIR.entity.*;
import MIR.terminalStmt.*;

public class IRPrinter implements IRVisitor{
    private PrintStream out;

    public IRPrinter(PrintStream out){
        this.out = out;
    }

    public void printIR(IRBuilder IR){
        //全局变量
        for (Map.Entry<String, entity> entry : IR.gScope.entities.entrySet()){
            out.print(entry.toString());
        }
        out.print("\n");
        //函数
        IR.functions.values().forEach(x->x.accept(this));
        //类定义
        for (classVar x : IR.classLists.values()) visitClass(x);
        out.close();
    }

    public void visitClass(classVar x){
        out.print("%class." + x.className + " = type {");
        boolean first = true;
        for (Map.Entry<String, entity> entry : x.entities.entrySet()) {
            if (first) first = false;
            else out.print(", ");
            out.print(entry.getValue().irType);
        }
        out.print(" }\n");
        for (Map.Entry<String, function> f : x.functions.entrySet()) {
            f.getValue().accept(this);
        }
    }

    public void visit(function it){
        String funcName = (!it.isInClass) ? it.funcName : it.className + "::" + it.funcName;
        out.print("define " + it.returnType + " " + funcName + "(");
        boolean first = true;
        for (entity e : it.entities) {
            if (first) first = false;
            else out.print(", ");
            out.print(getWithType(e));
        }
        out.print(") {\n");
        it.blocks.forEach(x->x.accept(this));
        out.print("}\n");
    }

    public void visit(block it){
        if (it.label != 0) out.print(it.label + ":\n");
        it.stmtList.forEach(x->x.accept(this));
        out.print("\n");
    }

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


    //terminal
    public void visit(branch it){
        out.print("\tbr " + getWithType(it.op) + ", label %" + it.trueBranch.label
                + ", label %" + it.falseBranch.label + "\n");
    }
    public void visit(jump it){
        out.print("\tbr label %" + it.destination.label + "\n");
    }
    public void visit(ret it){
        out.print("\tret " + getWithType(it.value) + "\n");
    }
    public void visit(loop it){
        out.print("\tbr label %" + it.condition.label + "\n");
    }


    private String getWithType(entity e) {
        return e.printWithType();
    }

    private String getEntity(entity e){
        return e.toString();
    }

}
