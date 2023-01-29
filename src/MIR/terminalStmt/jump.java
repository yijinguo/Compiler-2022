package MIR.terminalStmt;

import MIR.*;
import MIR.terminalStmt.terminalStmt;

public class jump extends terminalStmt {
    public block destination;
    public jump(block destination){
        super();
        this.destination = destination;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

}
