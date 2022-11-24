package MIR.Statmemt;

import MIR.*;
import MIR.Statmemt.*;

public class binary extends statement{

    public register lhs;
    public entity op1, op2;
    public String op;

    public binary(register lhs, entity op1, entity op2, String op){
        super();
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
        this.op = op;
        if (this.op1 instanceof constant) {
            this.op1 = op2;
            this.op2 = op1;
            if (this.op1 instanceof constant) {
                type i1 = ((constant) op1).getType(), i2 = ((constant) op2).getType(), i = new type(i1.irType);
                switch (op) {
                    case "*" -> i.int_value = i1.int_value * i2.int_value;
                    case "/" -> i.int_value = i1.int_value / i2.int_value;
                    case "%" -> i.int_value = i1.int_value % i2.int_value;
                    case "+" -> i.int_value = i1.int_value + i2.int_value;
                    case "-" -> i.int_value = i1.int_value - i2.int_value;
                    case "<<" -> i.int_value = i1.int_value << i2.int_value;
                    case ">>" -> i.int_value = i1.int_value >> i2.int_value;
                    case "<" -> i.int_value = (i1.int_value < i2.int_value) ? 1 : 0;
                    case ">" -> i.int_value = (i1.int_value > i2.int_value) ? 1 : 0;
                    case "<=" -> i.int_value = (i1.int_value <= i2.int_value) ? 1 : 0;
                    case ">=" -> i.int_value = (i1.int_value >= i2.int_value) ? 1 : 0;
                    case "==" -> i.int_value = (i1.int_value == i2.int_value) ? 1 : 0;
                    case "!=" -> i.int_value = (i1.int_value != i2.int_value) ? 1 : 0;
                    case "&" -> i.int_value = i1.int_value & i2.int_value;
                    case "^" -> i.int_value = i1.int_value ^ i2.int_value;
                    case "|" -> i.int_value = i1.int_value | i2.int_value;
                    case "&&" -> i.boolean_value = i1.boolean_value && i2.boolean_value;
                    case "||" -> i.boolean_value = i1.boolean_value || i2.boolean_value;
                    default -> {
                    }
                }
                this.op2 = new constant(i);
                type zero = new type(type.IRType.INT);
                zero.int_value = 0;
                this.op1 = new constant(zero);
                // Now, op1 is either register or zero
            }
        }

    }

}
