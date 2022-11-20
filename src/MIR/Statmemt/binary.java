package MIR.Statmemt;

import MIR.*;
import MIR.Statmemt.*;

public class binary extends statement{
    public enum opType{
        mul, div, mod, add, sub, lShift, rShift, lThan, gThan, lEqual, gEqual, eEqual, nEqual, bAnd, bOr, bXor, lAnd, lOr
    }

    public register lhs;
    public entity op1, op2;
    public opType op;

    public binary(register lhs, entity op1, entity op2, opType op){
        super();
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
        this.op = op;
        if (this.op1 instanceof constant) {
            this.op1 = op2;
            this.op2 = op1;
            if (this.op1 instanceof constant) {
                int i1 = ((constant) op1).int_value, i2 = ((constant) op2).int_value, i;
                switch (op) {
                    case mul : i = i1 * i2; break;
                    case div : i = i1 / i2; break;
                    case mod : i = i1 % i2; break;
                    case add : i = i1 + i2; break;
                    case sub : i = i1 - i2; break;
                    case lShift: i = i1 << i2; break;
                    case rShift: i = i1 >> i2; break;
                    case lThan: i = (i1 < i2) ? 1 : 0; break;
                    case gThan: i = (i1 > i2) ? 1 : 0; break;
                    case lEqual: i = (i1 <= i2) ? 1 : 0; break;
                    case gEqual: i = (i1 >= i2) ? 1 : 0; break;
                    case eEqual: i = (i1 == i2) ? 1 : 0; break;
                    case nEqual: i = (i1 != i2) ? 1 : 0; break;
                    case bAnd: 
                }

            }
        }

    }

}
