package MIR.entity;

import MIR.type.IRVoid;

public class consNull extends constant{

    public consNull(){
        super(new IRVoid());
    }

    @Override
    public String toString(){
        return "null";
    }

    @Override
    public String printWithType(){
        return "null";
    }
}
