package MIR;


public class constant extends entity{

    public enum constantType{
        INT,BOOL,STRING,NULL
    }
    public constantType type;
    public int int_value;
    public boolean boolean_value;
    public String string_value;

    public constant(){
        super();
    }

    public constantType getType(){
        return type;
    }

    public int get_int_value(){
        return int_value;
    }
    public boolean get_boolean_value(){
        return boolean_value;
    }
    public String get_string_value(){
        return string_value;
    }

}
