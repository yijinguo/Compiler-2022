package MIR.Statmemt;

import MIR.*;
import MIR.entity.*;

public class icmp extends statement{

    enum icmpInst{
        slt, sqt, sle, sge, eq, ne
    }

    public String op;
    public entity lhs;
    public entity rhs;
    public entity dest; //register

    public icmp(String op, entity lhs, entity rhs, entity dest){
        this.lhs = lhs;
        this.rhs = rhs;
        this.dest = dest;
        switch (op) {
            case "<" -> this.op = "slt";
            case ">" -> this.op = "sqt";
            case "<=" -> this.op = "sle";
            case ">=" -> this.op = "sge";
            case "==" -> this.op = "eq";
            case "!=" -> this.op = "ne";
            default -> {}
        }
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
