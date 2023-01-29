package MIR.Statmemt;

import MIR.IRVisitor;
import MIR.entity.*;

public class alloca extends statement{

    public register dest;

    public boolean isArray = false;

    public entity array_size;

    public alloca(register d){
        this.dest = d;
    }

    public alloca(register dest, entity size) {
        this.dest = dest;
        this.isArray = true;
        this.array_size = size;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
