package MIR.terminalStmt;

import MIR.IRVisitor;
import MIR.entity.entity;

public class ret extends terminalStmt {
    public entity value;
    public ret(entity value){
        this.value = value;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

}
