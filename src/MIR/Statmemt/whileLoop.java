package MIR.Statmemt;

import MIR.*;

public class whileLoop extends terminalStmt{
    public entity op;
    public block whileBlock;

    public whileLoop(entity op, block whileBlock){
        super();
        this.op = op;
        this.whileBlock = whileBlock;
    }
}
