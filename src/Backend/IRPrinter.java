package Backend;

import MIR.*;

import java.io.PrintStream;
import java.util.Map;

import MIR.Statmemt.*;
import MIR.terminalStmt.*;

public class IRPrinter implements Pass{
    private PrintStream out;

    public IRPrinter(PrintStream out){
        this.out = out;
    }

    public void printIR(IRBuilder IR){
        for (Map.Entry<String, register> entry : IR.gScope.entities.entrySet()){
            printVarDef(entry.getKey(), entry.getValue());
        }
        for (classVar x : IR.classLists.values()) visitClass(x);
        for (function x : IR.functions.values()) visitFunction(x);
    }

    public void visitBlock(block b){
        for (Map.Entry<String,entity> entry : b.entities.entrySet())
            out.println("\t%" + b.getRegister(entry.getKey()) + " = alloca " + getTypeName(entry.getValue()) + "\n");
        b.alloca_reg();
        for (statement x : b.stmtList) visitStatement(b, x);
        visitTerminalStmt(b.tailStmt);
    }

    public void visitFunction(function f){
        out.println("define " + getFuncType(f.returnType) + " @" + f.funcName + "(");
        boolean first = true;
        for (Map.Entry<String, entity> entry : f.paraList.entrySet()){
            if (first) first = false;
            else out.println(",");
            out.println(getTypeName(entry.getValue()) + " %" + ((register)entry.getValue()).identity);
        }
        out.println(") {\n");
        visitBlock(f.rootBlock);
        out.println("\n}");
    }
    public void visitClass(classVar c){}

    public void visitStatement(block b, statement s){
        if (s instanceof varDef && ((varDef) s).have_init) {
            out.println("\tstore");
            if (((varDef) s).init instanceof constant) {
                if (((varDef) s).init.irType.irType == type.IRType.STRING) {

                } else if (((varDef) s).init.irType.irType == type.IRType.INT) {
                    out.println("i32 " + ((varDef) s).init.irType.int_value+", i32* %"+b.getRegister(((varDef) s).var.identity)+"\n");
                } else {

                }

            } else {

            }
        } else if (s instanceof assign) {

        } else if (s instanceof call) {

        } else if (s instanceof binary) {

        } else if (s instanceof unary) {

        } else if (s instanceof array) {

        } else if (s instanceof preAdd) {

        } else if (s instanceof newExpr) {

        }
    }
    public void visitTerminalStmt(terminalStmt tail){

    }

    public void printVarDef(String name, entity e){

    }



    //一些简单的辅助函数
    public String getFuncType(String name){
        if (name.equals("int") || name.equals("bool")) {
            return "i32";
        } else {
            return name;
        }
    }

    public String getTypeName(entity e){
        if (!((register)e).isInClass) {
            if (((register) e).identity.equals("int") || ((register) e).identity.equals("bool")) {
                return "i32";
            } else if (((register) e).identity.equals("string")) {
                return "string";
            }
        }
        return ((register) e).identity;
    }

}
