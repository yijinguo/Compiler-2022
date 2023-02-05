package Assembly.Operand;

import MIR.entity.*;
import MIR.type.*;


public class GlobalValue extends Global{

    String value = "";

    public GlobalValue(globalVar r){
        this.name = r.identity;
        if (r.irType instanceof IRClass) {
            type = ".zero";
            value += String.valueOf(r.irType.size);
        } else if (r.irType instanceof IRPtr) { //string
            type = ".word";
            if (r.init instanceof consString && ((consString) r.init).value.equals("")) value += "0";
            else value += r.init.toString();
        } else if (r.irType instanceof IRInt) {
            if (r.irType.size == 4) {
                type = ".word";
                value += r.init.toString();
            } else {
                type = ".byte";
                value += r.init.toString();
            }
        } else if (r.irType instanceof IRArray) {
            type = ".zero";
            value += r.irType.size;
        }

    }

    @Override
    public String toString() {
        return name + ":\n\t" + type + "\t" + value + "\n";
    }
}
