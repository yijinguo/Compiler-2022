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

    public int get_int_value(){
        return irType.int_value;
    }
    public boolean get_boolean_value(){
        return irType.boolean_value;
    }
    public String get_string_value(){
        return irType.string_value;
    }

}
