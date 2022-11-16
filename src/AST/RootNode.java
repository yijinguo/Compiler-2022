package AST;

import Util.*;

import java.util.ArrayList;

public class RootNode extends ASTNode{

    public ArrayList<ASTNode> DefList = new ArrayList<>();
    public MainFnNode mainFn = new MainFnNode(new position(0,0));

    public RootNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
