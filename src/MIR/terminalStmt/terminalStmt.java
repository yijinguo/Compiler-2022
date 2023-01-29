package MIR.terminalStmt;

import MIR.IRVisitor;
import MIR.Statmemt.statement;

public abstract class terminalStmt extends statement {
    public abstract void accept(IRVisitor visitor);
}
