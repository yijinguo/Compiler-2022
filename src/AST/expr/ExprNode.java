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

    public abstract boolean isAssignable();
}
