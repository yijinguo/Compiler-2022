package AST;

import Util.*;

public class TypeNode extends  ASTNode{

    public Type type;

    public TypeNode(position pos) {
        super(pos);
    }

    public TypeNode(position pos, String name){
        super(pos);
        this.type = new Type(name);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
