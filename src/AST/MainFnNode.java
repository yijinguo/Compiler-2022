package AST;

import AST.stmt.StmtNode;
import Util.*;

import java.util.ArrayList;

public class MainFnNode extends ASTNode{
    public ArrayList<StmtNode> stmts = new ArrayList<>();

    public MainFnNode(position pos){
        super(pos);
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
