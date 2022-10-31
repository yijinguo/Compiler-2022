package AST;

import AST.stmt.StmtNode;
import Util.position;

import java.util.ArrayList;

public class FuncDefNode extends ASTNode{

    public TypeNode returnType;
    public String funcName;
    public ParameterListNode params;
    public ArrayList<StmtNode> stmts = new ArrayList<>();

    public FuncDefNode(position pos) {
        super(pos);
    }

    public FuncDefNode(position pos, String name){
        super(pos);
        this.funcName = name;
    }

    public FuncDefNode(position pos, TypeNode type, String name, ParameterListNode params){
        super(pos);
        this.returnType = type;
        this.funcName = name;
        this.params = params;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
