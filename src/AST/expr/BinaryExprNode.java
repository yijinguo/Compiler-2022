package AST.expr;

import AST.ASTVisitor;
import Util.position;

public class BinaryExprNode extends ExprNode{

    public String op;
    public ExprNode lhs;
    public ExprNode rhs;

    public BinaryExprNode(position pos){
        super(pos);
    }

    public BinaryExprNode(position pos, String op, ExprNode lhs, ExprNode rhs){
        super(pos);
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public boolean isAssignable(){
        return false;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
