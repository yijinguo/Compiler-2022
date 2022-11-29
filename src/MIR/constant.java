package MIR;


public class constant extends entity{

    public constant(){
        super();
    }

    public constant(type irType){
        super();
        this.irType = irType;
    }

    public type getType(){
        return irType;
    }

}
