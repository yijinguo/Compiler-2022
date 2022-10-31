package AST.expr;

import AST.ASTVisitor;
import Util.position;

public class FuncExprNode extends ExprNode{
    public ExprNode funcName;
    public ExprListNode lists;

    public FuncExprNode(position pos){
        super(pos);
    }

    public FuncExprNode(position pos, ExprNode func) {
        super(pos);
        this.funcName = func;
    }

    @Override
    public boolean isAssignable() {
        return false;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
