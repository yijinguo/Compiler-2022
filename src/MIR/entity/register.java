package MIR.entity;

import MIR.type.IRType;
import Util.Type;

public class register extends entity {

    public static int reg_cnt = -1;
    public int reg_num = -1;
    public String identity;

    public register(IRType type) {
        super(type);
        this.identity = "";
        reg_num = ++reg_cnt;
    }

    public register(String name, IRType type){
        super(type);
        this.identity = name;
        reg_num = ++reg_cnt;
    }

    @Override
    public String toString(){
        return "%" + reg_num;
    }

    @Override
    public String printWithType(){
        return irType.toString() + " " + this;
    }

}
