package MIR.entity;


import MIR.entity.entity;
import MIR.type;
import Util.Type;

public class constant extends entity {

    public constant(){
        super();
    }

    public constant(int i) {
        super();
        this.irType.irType = type.IRType.INT;
        this.irType.int_value = i;
    }

    public constant(type irType){
        super();
        this.irType = irType;
    }

    public type getType(){
        return irType;
    }

}
