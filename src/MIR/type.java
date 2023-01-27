package MIR;

import Util.Type;

import java.util.ArrayList;
import java.util.List;

public class type {
    public enum IRType{
        INT,BOOL,STRING,NULL,ClassType
    }
    public IRType irType;
    public boolean isArray = false;
    public int dim = 0;
    public ArrayList<entity> each_num = new ArrayList<>();
    public int int_value = 0;
    public boolean boolean_value = false;
    public String string_value = "\0";
    public String class_name;

    public type(){
        this.irType = IRType.NULL;
    }

    public type(IRType irType){
        this.irType = irType;
    }

    public type(Type type){
        if (type.typeName.equals("int")) {
            this.irType = IRType.INT;
        } else if (type.typeName.equals("bool")) {
            this.irType = IRType.BOOL;
        } else if (type.typeName.equals("String")) {
            this.irType = IRType.STRING;
        } else if (type.typeName.equals("null")) {
            this.irType = IRType.NULL;
        } else {
            this.irType = IRType.ClassType;
            this.class_name = type.typeName;
        }
        this.dim = type.dim;
    }

}
