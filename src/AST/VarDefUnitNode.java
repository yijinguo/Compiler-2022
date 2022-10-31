package AST;

import AST.expr.ExprNode;
import AST.stmt.StmtNode;
import Util.Type;
import Util.position;

import java.util.ArrayList;

public class VarDefUnitNode extends ASTNode {

    public TypeNode type;
    public String varName;
    public ExprNode init;

    public VarDefUnitNode(position pos){
        super(pos);
    }

    public VarDefUnitNode(position pos, TypeNode type, String name){
        super(pos);
        this.varName = name;
        this.type = type;
    }

    public VarDefUnitNode(position pos, String name, ExprNode init){
        super(pos);
        this.varName = name;
        this.init = init;
    }

    public VarDefUnitNode(position pos, TypeNode type, String name, ExprNode init){
        super(pos);
        this.varName = name;
        this.type = type;
        this.init = init;
    }

    public String getVarName(){
        return this.varName;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
