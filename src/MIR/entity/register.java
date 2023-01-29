package MIR.entity;

import MIR.type;
import Util.Type;

public class register extends entity {

    public int reg_num = 0;
    public String identity = null;
    public boolean isInClass = false;
    public String className = null;

    public register(int num, type irType){
        super();
        this.reg_num = num;
        this.irType = irType;
    }

    public register(int num, Type type){
        super();
        this.reg_num = num;
        this.irType = new type(type);
    }

    public register(int num, String identity, Type type){
        super();
        this.reg_num = num;
        this.identity = identity;
        this.irType = new type(type);
    }

    public register(register re){
        this.irType = re.irType;
        this.reg_num = re.reg_num;
        this.identity = re.identity;
        this.isInClass = re.isInClass;
        this.className = re.className;
    }

}
