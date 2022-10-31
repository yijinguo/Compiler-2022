package AST;

import Util.position;

import java.util.ArrayList;

public class ParameterListNode extends ASTNode{

    public ArrayList<VarDefUnitNode> varList = new ArrayList<>();

    public ParameterListNode(position pos){
        super(pos);
    }


    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
