package MIR.Statmemt;

import MIR.*;
import MIR.entity.entity;
import MIR.entity.register;
import MIR.type.IRType;

import java.util.ArrayList;

public class call extends statement{
    public String functionName;
    public String className = "";
    public register returnReg;
    public ArrayList<entity> paramList = new ArrayList<>();

    public call(register returnReg, String functionName){
        super();
        this.returnReg = returnReg;
        this.functionName = functionName;
    }

    public call(register returnReg, String functionName, entity... params){
        super();
        this.returnReg = returnReg;
        this.functionName = functionName;
        for (entity e : params) paramList.add(e);
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
