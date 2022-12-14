package AST.stmt;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.position;

public class ContinueNode extends StmtNode {

    public ContinueNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
