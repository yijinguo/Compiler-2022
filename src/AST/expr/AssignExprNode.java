package AST.expr;

import AST.ASTVisitor;
import Util.position;

public class AssignExprNode extends BinaryExprNode{

    public AssignExprNode(position pos){
        super(pos);
    }

    public AssignExprNode(position pos, ExprNode lhs, ExprNode rhs){
        super(pos, "=", lhs, rhs);
    }

    @Override
    public boolean isAssignable(){
        return true;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
