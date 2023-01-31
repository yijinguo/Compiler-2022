package AST;

import MIR.type.IRType;
import Util.*;

public class TypeNode extends ASTNode{

    public Type type = new Type();
    public IRType irType = null;

    public TypeNode(position pos) {
        super(pos);
    }

    public TypeNode(position pos, String name){
        super(pos);
        this.type = new Type(name);
    }

    public TypeNode(position pos, Type type, boolean isArray){
        super(pos);
        this.type = type;
        this.type.isArray = isArray;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
