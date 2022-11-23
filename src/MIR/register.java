package MIR;

import Util.Type;

public class register extends entity{
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
