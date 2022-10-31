package AST.expr;

import AST.ASTVisitor;
import Util.position;

public class VarExprNode extends AtomExprNode{

    public VarExprNode(position pos){
        super(pos);
    }

    public VarExprNode(position pos, String s){
        super(pos, s);
    }

    @Override
    public boolean isAssignable() {
        return true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
