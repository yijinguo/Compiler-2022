package MIR;

import Util.Type;

public class register extends entity{

    public String identity = null;
    public boolean isInClass = false;
    public String className = null;

    public register(){
        super();
    }

    public register(type irType){
        super();
        this.irType = irType;
    }

    public register(String identity, Type type){
        super();
        this.identity = identity;
        this.irType = new type(type);
    }

}
