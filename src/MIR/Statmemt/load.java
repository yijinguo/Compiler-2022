package MIR.Statmemt;

import MIR.IRVisitor;
import MIR.entity.*;

public class load extends statement{

    public entity dest; //register
    public entity cont;

    public load(entity dest, entity cont){
        this.dest = dest;
        this.cont = cont;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
