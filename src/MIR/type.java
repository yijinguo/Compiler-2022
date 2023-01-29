package MIR;

import MIR.entity.entity;
import Util.Type;

import java.util.ArrayList;

public class type {
    public enum IRType{
        INT,BOOL,STRING,NULL,VOID,ClassType
    }
    public IRType irType;
    public boolean isArray = false;
    public int dim = 0;
    public boolean haveDef = false;
    public ArrayList<entity> each_num = new ArrayList<>();

    public int int_value = 0;
    public boolean boolean_value = false;
    public String string_value = "\0";
    //public int string_value_r = 0;
    public String class_name;
    public int class_size;

    public type(){
        this.irType = IRType.NULL;
    }

    public type(IRType irType){
        this.irType = irType;
    }

    public type(Type type){
        switch (type.typeName) {
            case "int" -> this.irType = IRType.INT;
            case "bool" -> this.irType = IRType.BOOL;
            case "String" -> this.irType = IRType.STRING;
            case "null" -> this.irType = IRType.NULL;
            case "void" -> this.irType = IRType.VOID;
            default -> {
                this.irType = IRType.ClassType;
                this.class_name = type.typeName;
            }
        }
        this.dim = type.dim;
    }

}
