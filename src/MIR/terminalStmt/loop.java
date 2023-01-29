package MIR.terminalStmt;

import MIR.*;

public class loop extends terminalStmt{

    public block condition;
    public block loopBlk;

    public loop(block condition, block loopBlk){
        this.condition = condition;
        this.loopBlk = loopBlk;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
