package MIR.entity;

import MIR.type.IRType;
import MIR.type.IRVoid;

public class consNull extends constant{

    public IRType type = null;

    public consNull(){
        super(new IRVoid());
    }

    @Override
    public String toString(){
        return "null";
    }

    @Override
    public String printWithType(){
        return type == null ?  "null" : type + " null";
    }
}
