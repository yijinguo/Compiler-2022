package MIR.type;

import Util.Type;

public abstract class IRType {
    public String name;
    public int size;

    public IRType(){}

    public IRType(String name){
        this.name = name;
    }

    public IRType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String toString(){
        return name;
    }

}
