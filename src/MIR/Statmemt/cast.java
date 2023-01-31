package MIR.Statmemt;

import MIR.*;
import MIR.entity.*;

public class cast extends statement{

    public entity dest;
    public entity val;

    public cast(entity dest, entity val){
        this.dest = dest;
        this.val = val;
    }


    @Override
    public final void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

}
