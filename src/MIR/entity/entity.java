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

    public abstract String toString();

    public abstract String printWithType();

}
