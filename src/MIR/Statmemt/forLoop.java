package MIR.Statmemt;

import AST.expr.ExprNode;
import MIR.*;

public class forLoop extends terminalStmt{

    public entity op;
    public ExprNode step;

    public block forBlock;

    public forLoop(block forBlock){
        super();
        this.forBlock = forBlock;
    }
}
