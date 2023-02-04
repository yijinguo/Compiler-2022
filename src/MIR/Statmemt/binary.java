package MIR.Statmemt;

import MIR.*;
import MIR.entity.*;
import MIR.type.IRInt;
import MIR.type.IRPtr;
import MIR.type.IRType;

public class binary extends statement{

    enum BinaryInst{
        add, sub, mul, sdiv, srem, shl, ashr, and, xor, or
    }

    public String op;
    public entity lhs; //register
    public entity op1, op2;

    public binary(String op, entity op1, entity op2, entity lhs) {
        super();
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
        switch (op) {
            case "*" -> this.op = "mul";
            case "/" -> this.op = "sdiv";
            case "%" -> this.op = "srem";
            case "+", "add" -> this.op = "add";
            case "-", "sub" -> this.op = "sub";
            case "<<" -> this.op = "shl";
            case ">>" -> this.op = "ashr";
            case "&" -> this.op = "and";
            case "^", "xor" -> this.op = "xor";
            case "|" -> this.op = "or";
            default -> { //&&, ||
                this.op = op;
            }
        }
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

}
