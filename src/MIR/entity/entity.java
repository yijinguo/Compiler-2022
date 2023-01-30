package MIR.entity;

import Assembly.Operand.Reg;
import MIR.type.*;
import Util.Type;

public abstract class entity {
    public IRType irType;
    public Reg asmReg;

    public entity(IRType type){
        this.irType = type;
    }

    public entity(Type type) {
        IRType res;
        switch (type.typeName) {
            case "int" -> res = new IRInt(32);
            case "bool" -> res = new IRInt(8);
            case "void", "null" -> res = new IRVoid();
            case "string" -> res = new IRPtr(new IRInt(8));
            default -> {
                //todo
                //应该从gScope中找
                res = new IRClass(null);
            }
        }
        if (type.isArray) {
            this.irType = new IRPtr(res, type.dim);
        } else {
            this.irType = res;
        }
    }

    public abstract String toString();

    public abstract String printWithType();

}
