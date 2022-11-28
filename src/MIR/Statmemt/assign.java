package MIR.Statmemt;

import MIR.*;

public class assign extends statement{

    public entity lhs;
    public entity rhs = null;
    public statement rhs_stmt = null;

    public assign(entity lhs, entity rhs){
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public assign(entity lhs, statement rhs_stmt){
        super();
        this.lhs = lhs;
        this.rhs_stmt = rhs_stmt;
    }
}
