package Middlend;

import MIR.*;

import java.io.PrintStream;
import java.util.Map;

import MIR.Statmemt.*;
import MIR.entity.constant;
import MIR.entity.entity;
import MIR.entity.register;
import MIR.terminalStmt.*;

public class IRPrinter implements IRVisitor{
    private PrintStream out;

    public IRPrinter(PrintStream out){
        this.out = out;
    }

    public void printIR(IRBuilder IR){
        //全局变量
        int string_num = 0;
        for (Map.Entry<String, entity> entry : IR.gScope.entities.entrySet()){
            if (entry instanceof register) {
                register r = (register) entry.getValue();
                out.print("@" + r.identity + " = global ");
                if (r.irType.irType == type.IRType.INT) {
                    out.print("i32 " + r.irType.int_value + "\n");
                } else if (r.irType.irType == type.IRType.BOOL) {
                    out.print("i8 " + r.irType.boolean_value + "\n");
                } else if (r.irType.irType == type.IRType.STRING) {
                    int l = r.irType.string_value.length();
                    out.print("@" + r.identity + " = global [" + l + " x i8] c\"" + r.irType.string_value + "\\00\"\n");
                    //out.print("i8* getelementptr ([" + l + " x i8], [" + l + " x i8]* @.L.str." + r.irType.string_value_r +", i32 0, i32 0)\n");
                }
            } else {
                constant c = (constant) entry.getValue();
                out.print("@" + c.irType.string_value + " = global [" + c.irType.string_value.length() + " x i8] c\"" + c.irType.string_value + "\\00\"\n");
            }
        }
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
            out.print(getType(entry.getValue()));
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
            out.print(getType(e) + " " + getEntity(e));
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
        out.print("\t%" + it.dest.reg_num + " = alloca " + getType(it.dest) + "\n");
    }
    public void visit(load it){
        out.print("\t" + getEntity(it.dest) + " = load " + getType(it.dest) + ", " + getType(it.cont) + "* " + getEntity(it.cont) + "\n");
    }
    public void visit(store it){
        out.print("\tstore " + getType(it.dest) + " " + getEntity(it.dest) + ", " + getType(it.cont) + "*" + getEntity(it.cont) + "\n");
    }
    public void visit(binary it){
        out.print("\t" + getEntity(it.lhs) + " = " + it.op + " " + getType(it.lhs) +
                " " + getEntity(it.op1) + ", " + getEntity(it.op2) + "\n");
    }
    public void visit(unary it){
        out.print("\t" + getEntity(it.dest) + " = " + it.op + " " + getType(it.dest) +
                " " + getEntity(it.cont) + ", " + getEntity(it.cons) + "\n");
    }
    public void visit(icmp it){
        out.print("\t" + getEntity(it.dest) + " = icmp" + it.op + " " + getType(it.dest) +
                " " + getEntity(it.lhs) + ", " + getEntity(it.rhs) + "\n");
    }
    public void visit(call it){
        out.print("\t" + getEntity(it.returnReg) + " = call " + getType(it.returnReg) + " @" + it.functionName + "\n");
    }
    public void visit(getelementptr it){
        out.print("\t" + getEntity(it.dest) + " = getelementptr " + getType(it.dest) + ", "
                + getType(it.head) + " " + getEntity(it.head) + ", "
                + getType(it.tail) + " " + getEntity(it.tail) + "\n");
    }
    public void visit(createPtr it){
        //operator new[](unsigned int)
        out.print("\t" + getEntity(it.dest) + " = call operator new[](unsigned int), " + getType(it.size) + getType(it.size) + "\n");
    }


    //terminal
    public void visit(branch it){
        out.print("\tbr " + getType(it.op) + " %" + ((register)it.op).reg_num + ", label %" + it.trueBranch.label
                + ", label %" + it.falseBranch.label + "\n");
    }
    public void visit(jump it){
        out.print("\tbr label %" + it.destination.label + "\n");
    }
    public void visit(ret it){
        out.print("\tret " + getType(it.value) + getEntity(it.value) + "\n");
    }
    public void visit(loop it){
        out.print("\tbr label %" + it.condition.label + "\n");
    }


    private String getType(entity e) {
        String ret = "";
        switch (e.irType.irType) {
            // INT,BOOL,STRING,NULL,VOID,ClassType
            case INT -> ret += "i32";
            case BOOL -> ret += "i8";
            case NULL -> ret += "null";
            case STRING -> ret += "i8*";
            case ClassType -> ret += "%class." + e.irType.class_name;
            default -> {
            }
        }
        for (int i = 0; i < e.irType.dim; i++) ret += "*";
        return ret;
    }

    private String getEntity(entity e){
        String ret = "";
        if (e instanceof register) {
            ret += "%" + ((register)e).reg_num;
        } else {
            switch (e.irType.irType) {
                // INT,BOOL,STRING,NULL,VOID,ClassType
                case INT -> ret += e.irType.int_value;
                case BOOL -> ret += e.irType.boolean_value;
                case NULL -> ret += "null";
                case STRING -> ret += "@" + e.irType.string_value;
                default -> {}
            }
        }
        return ret;
    }

}
