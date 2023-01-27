package MIR.Statmemt;

import MIR.*;

public class assign extends statement{

    public entity lhs;
    public entity rhs = null;

    public assign(entity lhs, entity rhs){
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

}
