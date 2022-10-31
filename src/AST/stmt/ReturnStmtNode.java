package AST.stmt;

import AST.ASTVisitor;
import AST.expr.ExprListNode;
import AST.expr.ExprNode;
import Util.position;

public class ReturnStmtNode extends StmtNode{

    public ExprNode expr;

    public ReturnStmtNode(position pos){
        super(pos);
    }

    public ReturnStmtNode(position pos, ExprNode expr){
        super(pos);
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
