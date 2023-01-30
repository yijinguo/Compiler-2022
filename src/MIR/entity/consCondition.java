package MIR.entity;

import MIR.type.IRInt;

public class consCondition extends constant{

    public boolean value;

    public consCondition(boolean value){
        super(new IRInt(1));
        this.value = value;
    }

    @Override
    public String toString(){
        return value ? "true" : "false";
    }

    @Override
    public String printWithType(){
        return "i1 " + this;
    }
}
