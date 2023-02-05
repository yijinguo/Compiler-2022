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
import MIR.type.IRVoid;

public class IRPrinter implements IRVisitor{
    private PrintStream out;

    public IRPrinter(PrintStream out){
        this.out = out;
    }

    public void printIR(IRBuilder IR){
        //全局变量
        for (Map.Entry<String, entity> entry : IR.gScope.entities.entrySet()){
            if (entry.getValue() instanceof globalVar it) {
                out.print(it + " = global " + ((IRPtr)it.irType).pointDown() + " " + (it.init == null ? "null" : it.init) + "\n");
                //out.print(it + " = global " + it.init.printWithType() + "\n");
            } else if (entry.getValue() instanceof consString it) {
                out.print(it + " = private unnamed_addr constant " + it.printGlobal() + "\n");
            }
        }
        out.print("\n");
        IR.classTypes.values().forEach(this::visitClass);
        //函数
        IR.functions.values().forEach(x->x.accept(this));
        //out.close();
    }

    public void visitClass(IRClass x){
        out.print(x.name + " = type { ");
        boolean first = true;
        for (IRType t : x.memberType) {
            if (first) first = false;
            else out.print(", ");
            out.print(t);
        }
        out.print(" }\n\n");
    }


    public void visit(function it){
        if (it.internal) out.print("define internal " + it.returnType + " @" + it.funcName + "(");
        else out.print("define " + it.returnType + " @" + it.funcName + "(");
        boolean first = true;
        for (entity e : it.paraList) {
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
        //if (!it.isConst)
        String op = it.op;
        if (op.equals("add") || op.equals("mul") || op.equals("sub")) {
            op += " nsw";
        }
        out.print("\t" + it.lhs + " = " + op + " " + it.lhs.irType + " " + it.op1 + ", " + it.op2 + "\n");
    }

    public void visit(icmp it){
        //if (!it.isConst)
            out.print("\t" + it.dest + " = icmp " + it.op + " " + it.lhs.irType + " " + it.lhs + ", " + it.rhs + "\n");
    }
    public void visit(zext it){
        out.print("\t" + it.dest + " = zext " + it.cont.printWithType() + " to " + it.dest.irType + "\n");
    }
    public void visit(call it){
        String funcName = (it.className == null ? it.functionName : it.className + "." + it.functionName);
        if (it.returnReg == null) {
            out.print("\tcall void @" + funcName + "(");
        } else {
            out.print("\t" + it.returnReg + " = call " + it.returnReg.irType + " @" + funcName + "(");
        }
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
        if (it instanceof bitcast) {
            out.print("\t" + it.dest + " = bitcast " + it.val.printWithType() + " to " + it.dest.irType + "\n");
        } else {
            out.print("\t" + it.dest + " = trunc " + it.val.printWithType() + " to " + it.dest.irType + "\n");
        }
    }


    //terminal
    public void visit(branch it){
        out.print("\tbr " + it.op.printWithType() + ", label %" + it.trueBranch
                + ", label %" + it.falseBranch + "\n");
    }
    public void visit(jump it){
        out.print("\tbr label %" + it.destination + "\n");
    }
    public void visit(ret it) {
        if (it.value instanceof consNull && ((consNull) it.value).type == null) {
            out.print("\tret void\n");
        } else {
            out.print("\tret " + it.value.printWithType() + "\n");
        }
    }
    public void visit(loop it){
        out.print("\tbr label %" + it.condition + "\n");
    }

}
