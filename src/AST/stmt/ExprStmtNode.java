package AST.stmt;

import AST.ASTVisitor;
import AST.expr.ExprNode;
import Util.position;

public class ExprStmtNode extends StmtNode{

    public ExprNode exprNode;

    public ExprStmtNode(position pos){
        super(pos);
    }

    public ExprStmtNode(position pos, ExprNode expr){
        super(pos);
        this.exprNode = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
