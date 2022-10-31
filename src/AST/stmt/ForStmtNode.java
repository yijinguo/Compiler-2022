package AST.stmt;


import AST.ASTNode;
import AST.ASTVisitor;
import AST.VarDefNode;
import AST.expr.ExprNode;
import Util.position;

import java.util.ArrayList;

public class ForStmtNode extends StmtNode {

    public VarDefNode varDef;
    public ExprNode init, condition, step;
    public ArrayList<StmtNode> stmts = new ArrayList<>();

    public ForStmtNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
