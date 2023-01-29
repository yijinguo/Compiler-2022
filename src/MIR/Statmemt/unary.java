package MIR.Statmemt;

import MIR.IRVisitor;
import MIR.entity.*;

public class unary extends statement{

    public String op;
    public entity dest;
    public entity cont;
    public constant cons;

    public unary(String op, constant cons, entity cont, entity dest){
        this.op = op;
        this.cons = cons;
        this.cont = cont;
        this.dest = dest;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
