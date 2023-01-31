package MIR.Statmemt;

import MIR.IRVisitor;
import MIR.entity.*;

public class zext extends statement{

    public entity dest; //i32
    public entity cont; //i1

    public zext(entity dest, entity cont){
        this.dest = dest;
        this.cont = cont;
    }

    //%dest = zext i1 %cont to i32;

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

}
