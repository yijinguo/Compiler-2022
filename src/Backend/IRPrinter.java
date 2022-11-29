package Backend;

import MIR.*;

import java.io.PrintStream;
import java.util.HashMap;
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
        for (function x : IR.functions.values()) visitFunction(x);
        for (classVar x : IR.classLists.values()) visitClass(x);
    }

    public void visitBlock(block b){

        visitTerminalStmt(b.tailStmt);
    }

    public void visitFunction(function f){
        out.println("define " + getFuncType(f.returnType) + " @" + f.funcName + "(");
        boolean first = true;
        for (Map.Entry<String, entity> entry : f.paraList.entrySet()){
            if (first) first = false;
            else out.println(",");
            out.println(getTypeName(entry.getKey()) + " %" + ((register)entry.getValue()).identity);
        }
        out.println("){");
        visitBlock(f.rootBlock);
        out.println("}");
    }
    public void visitClass(classVar c){}

    public void visitStatement(statement s){

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

    public String getTypeName(String typename){
        if (typename.equals("int") || typename.equals("bool")) {
            return "i32";
        } else if (typename.equals("string")) {
            return "string";
        } else {
            return typename;
        }
    }

}
