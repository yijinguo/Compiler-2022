package MIR.Statmemt;

import MIR.IRVisitor;
import MIR.entity.*;

import java.util.ArrayList;

public class getelementptr extends statement{

    public entity dest; //register
    public entity head; //register
    public entity tail;

    public getelementptr(entity dest, entity head, entity tail){
        this.dest = dest;
        this.head = head;
        this.tail = tail;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
