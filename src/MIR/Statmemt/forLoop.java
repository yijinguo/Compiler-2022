package MIR.Statmemt;

import MIR.*;

public class forLoop extends terminalStmt{
    public block forBlock;

    public forLoop(block forBlock){
        super();
        this.forBlock = forBlock;
    }
}
