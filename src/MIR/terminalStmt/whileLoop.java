package MIR.terminalStmt;

import MIR.*;
import MIR.Statmemt.*;

public class whileLoop extends terminalStmt {
    public block condition;
    public block whileBlock;

    public whileLoop(block condition, block whileBlock){
        super();
        this.condition = condition;
        this.whileBlock = whileBlock;
    }
}
