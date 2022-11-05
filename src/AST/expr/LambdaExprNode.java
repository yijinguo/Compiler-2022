package AST.expr;

import AST.ASTVisitor;
import AST.ParameterListNode;
import AST.stmt.StmtNode;
import Util.position;

import java.util.ArrayList;

public class LambdaExprNode extends ExprNode{
    public boolean isGlobe;
    public ParameterListNode params;
    public ArrayList<StmtNode> stmts = new ArrayList<>();
    public ExprListNode lists;

    public LambdaExprNode(position pos){
        super(pos);
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
