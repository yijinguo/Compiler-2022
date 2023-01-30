package MIR.entity;

import MIR.type.IRInt;

public class consInt extends constant{

    public int value = 0;

    public consInt(int value){
        super(new IRInt(32));
        this.value = value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public String printWithType(){
        return "i32 " + this;
    }

}
