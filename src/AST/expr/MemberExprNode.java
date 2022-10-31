package AST.expr;

import AST.ASTVisitor;
import Util.position;
public class MemberExprNode extends ExprNode{

    public ExprNode name;
    public String member;

    public MemberExprNode(position pos){
        super(pos);
    }

    public MemberExprNode(position pos, ExprNode name, String mem){
        super(pos);
        this.name = name;
        this.member = mem;
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
