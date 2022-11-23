package MIR.terminalStmt;

import MIR.*;
import MIR.Statmemt.*;
import MIR.terminalStmt.terminalStmt;

import java.util.ArrayList;

public class forLoop extends terminalStmt {

    public block start;
    public block forBlock;

    public forLoop(block start, block forBlock){
        super();
        this.start = start;
        this.forBlock = forBlock;
    }
}
