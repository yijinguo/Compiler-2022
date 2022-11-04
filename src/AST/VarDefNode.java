package AST;

import AST.stmt.StmtNode;
import Util.position;

import java.util.ArrayList;

public class VarDefNode extends StmtNode {

    public ArrayList<VarDefUnitNode> units = new ArrayList<>();

    public VarDefNode(position pos) {
        super(pos);
    }

    public String getTypeName(){
        if (units != null) return units.get(0).type.type.typeName;
        else return null;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
