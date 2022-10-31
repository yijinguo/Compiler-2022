package AST.stmt;

import AST.ASTVisitor;
import AST.expr.ExprNode;
import Util.position;

import java.util.ArrayList;

public class IfStmtNode extends StmtNode{

    public ExprNode condition;
    public ArrayList<StmtNode> thenStmts = new ArrayList<>();
    public ArrayList<StmtNode> elseStmts = new ArrayList<>();

    public IfStmtNode(position pos){
        super(pos);
    }

    public IfStmtNode(position pos, ExprNode condition) {
        super(pos);
        this.condition = condition;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
