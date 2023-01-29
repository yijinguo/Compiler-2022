package MIR.Statmemt;

import MIR.*;
import MIR.entity.entity;
import MIR.entity.register;

import java.util.ArrayList;

public class call extends statement{
    public String functionName;
    public String className = "";
    public register returnReg;
    public ArrayList<entity> paramList = new ArrayList<>();

    public call(int num, type returnType, String functionName){
        super();
        this.returnReg = new register(num, returnType);
        this.functionName = functionName;
    }

    public call(int num, type returnType, String functionName, String className){
        super();
        this.returnReg = new register(num, returnType);
        this.functionName = className + "::" + functionName;
        this.className = className;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
