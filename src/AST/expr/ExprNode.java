package AST.expr;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.FuncDefNode;
import Util.Type;
import Util.position;

public abstract class ExprNode extends ASTNode {

    public String str;
    public Type type;
    public FuncDefNode funcDef = null;

    public ExprNode(position pos){
        super(pos);
    }

    public ExprNode(ExprNode other){
        super(other.pos);
        this.str = other.str;
        this.type = other.type;
        this.funcDef = other.funcDef;
    }

    public abstract boolean isAssignable();
}
