package AST.stmt;

import AST.ASTVisitor;
import Util.position;

public class BreakNode extends StmtNode{

    public BreakNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
