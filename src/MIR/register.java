package MIR;

import Util.Type;

public class register extends entity{

    public boolean isInClass = false;
    public String className = null;

    public register(){
        super();
    }

    public register(type irType){
        super();
        this.irType = irType;
    }

    public register(Type type){
        super();
        this.irType = new type(type);
    }

}
