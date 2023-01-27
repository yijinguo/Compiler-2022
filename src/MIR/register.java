package MIR;

import Util.Type;

import java.util.ArrayList;

public class register extends entity{


    public int reg_num = 0;
    public String identity = null;
    public boolean isInClass = false;
    public String className = null;
    public boolean isArrayNew = false;

    public int dim = 0;
    public ArrayList<entity> each_num = new ArrayList<>();

    public register(type irType){
        super();
        this.irType = irType;
    }

    public register(Type type){
        super();
        this.irType = new type(type);
    }

    public register(String identity, Type type){
        super();
        this.identity = identity;
        this.irType = new type(type);
    }

    public register(register re){
        this.irType = re.irType;
        this.new_reg_num = re.new_reg_num;
        this.reg_num = re.reg_num;
        this.identity = re.identity;
        this.isInClass = re.isInClass;
        this.className = re.className;
        this.isArrayNew = re.isArrayNew;
    }

}
