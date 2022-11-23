package MIR.terminalStmt;

import MIR.*;
import MIR.terminalStmt.terminalStmt;

public class ret extends terminalStmt {
    public entity value;
    public ret(entity value){
        this.value = value;
    }
}
