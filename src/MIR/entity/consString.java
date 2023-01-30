package MIR.entity;

import MIR.type.IRArray;
import MIR.type.IRInt;
import MIR.type.IRPtr;

public class consString extends constant{

    public String value;
    public int id;
    public static int cnt = 0;

    public consString(String value){
        super(new IRPtr(new IRArray(new IRInt(8), value.length() + 1)));
        this.value = value;
        this.id = cnt++;
    }

    @Override
    public String toString(){
        return "@str_" + id;
    }

    @Override
    public String printWithType(){
        return "[" + (value.length() + 1) + " x i8]* " + this;
    }
}
