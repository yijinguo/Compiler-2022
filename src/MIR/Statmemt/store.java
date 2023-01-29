package MIR.Statmemt;

import MIR.*;
import MIR.entity.*;

public class store extends statement{

    public entity dest; //register
    public entity cont;

    public store(entity dest, entity cont){
        this.dest = dest;
        this.cont = cont;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
