package AST.expr;

import AST.ASTVisitor;
import Util.position;

public class ArrayExprNode extends ExprNode{

    public ExprNode arrayName;
    public ExprNode index;
    public int dim = 0;

    public ArrayExprNode(position pos){
        super(pos);
    }

    public ArrayExprNode(position pos, ExprNode arrayName, ExprNode index){
        super(pos);
        this.arrayName = arrayName;
        this.index = index;
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
