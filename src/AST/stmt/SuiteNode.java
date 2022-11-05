package AST.stmt;

import AST.ASTVisitor;
import java.util.ArrayList;
import Util.position;

public class SuiteNode extends StmtNode{

    public ArrayList<StmtNode> stmts = new ArrayList<>();

    public boolean builder = false;

    public SuiteNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
