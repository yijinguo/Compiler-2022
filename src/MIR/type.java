package MIR;

import Util.Type;

public class type {
    public enum IRType{
        INT,BOOL,STRING,NULL,ClassType
    }
    public IRType irType;
    public int dim = 0;
    public int int_value;
    public boolean boolean_value;
    public String string_value;
    public String class_name;

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
