package MIR.Statmemt;

import MIR.IRVisitor;
import MIR.entity.*;

public class createPtr extends statement{

    public entity dest;
    public entity size;

    public createPtr(entity dest, entity size){
        this.size = size;
        this.dest = dest;
    }


    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
