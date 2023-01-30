package MIR.entity;

import MIR.type.*;

public class consBool extends constant{

    public boolean value;

    public consBool(boolean value){
        super(new IRInt(8));
        this.value = value;
    }

    @Override
    public String toString(){
        return value ? "1" : "0";
    }

    @Override
    public String printWithType(){
        return "i8 " + this;
    }
}
