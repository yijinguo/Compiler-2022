package MIR.Statmemt;

import MIR.*;

import java.util.ArrayList;

public class call extends statement{
    public String functionName;
    public String className = null;
    public entity returnReg;
    public ArrayList<entity> paramList = new ArrayList<>();

    public call(type returnType, String functionName){
        super();
        this.returnReg = new register(returnType);
        this.functionName = functionName;
    }

    public call(type returnType, String functionName, String className){
        super();
        this.returnReg = new register(returnType);
        this.functionName = functionName;
        this.className = className;
    }
}
