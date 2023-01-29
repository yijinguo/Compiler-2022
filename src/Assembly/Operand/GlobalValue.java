package Assembly.Operand;

import MIR.entity.register;

public class GlobalValue extends Global{

    String value = "";

    public GlobalValue(register r){
        this.name = r.identity;
        switch (r.irType.irType) {
            case INT -> {
                type = ".word";
                value += r.irType.int_value;
            }
            case BOOL -> {
                type = ".byte";
                value += (r.irType.boolean_value) ? 1 : 0;
            }
            case STRING -> {
                type = ".word";
                value += ".L.str." + r.irType.string_value_r;
            }
            case ClassType -> {
                type = ".zero";
                value += r.irType.class_size;
            }
        }
    }

    @Override
    public String toString() {
        return name + ":\n\t\t" + type + "\t" + value + "\n";
    }
}
