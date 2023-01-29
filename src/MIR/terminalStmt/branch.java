package MIR.terminalStmt;

import MIR.*;
import MIR.entity.entity;

public class branch extends terminalStmt {
    public entity op;
    public block trueBranch;
    public block falseBranch;

    public branch(entity op, block trueBranch, block falseBranch){
        super();
        this.op = op;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

}
