package MIR;

import MIR.Statmemt.*;
import MIR.terminalStmt.*;

public interface IRVisitor {

    public void visit(function it);
    public void visit(block it);

    //statement
    public void visit(alloca it);
    public void visit(load it);
    public void visit(store it);
    public void visit(binary it);
    public void visit(icmp it);
    public void visit(zext it);
    public void visit(call it);
    public void visit(getelementptr it);
    public void visit(cast it);

    //terminal
    public void visit(branch it);
    public void visit(jump it);
    public void visit(ret it);
    public void visit(loop it);
}
