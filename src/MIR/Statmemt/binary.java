package MIR.Statmemt;

import MIR.*;
import MIR.entity.constant;
import MIR.entity.entity;

public class binary extends statement{

    enum BinaryInst{
        add, sub, mul, sdiv, srem, shl, ashr, and, xor, or
    }

    public String op;
    public entity lhs; //register
    public entity op1, op2;

    public binary(String op, entity op1, entity op2, entity lhs){
        super();
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
        switch (op) {
            case "*" -> this.op = "mul nsw" ;
            case "/" -> this.op = "sdiv";
            case "%" -> this.op = "srem";
            case "+", "add" -> this.op = "add nsw";
            case "-", "sub" -> this.op = "sub nsw";
            case "<<" -> this.op = "shl";
            case ">>" -> this.op = "ashr";
            case "&" -> this.op = "and";
            case "^", "xor" -> this.op = "xor";
            case "|" -> this.op = "or";
            default -> { //&&, ||
                this.op = op;
            }
        }
        /*
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
                    //case "<" -> i.int_value = (i1.int_value < i2.int_value) ? 1 : 0;
                    //case ">" -> i.int_value = (i1.int_value > i2.int_value) ? 1 : 0;
                    //case "<=" -> i.int_value = (i1.int_value <= i2.int_value) ? 1 : 0;
                    //case ">=" -> i.int_value = (i1.int_value >= i2.int_value) ? 1 : 0;
                    //case "==" -> i.int_value = (i1.int_value == i2.int_value) ? 1 : 0;
                    //case "!=" -> i.int_value = (i1.int_value != i2.int_value) ? 1 : 0;
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
        */
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

}
