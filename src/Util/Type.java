package Util;

import AST.ClassDefNode;

public class Type {

    public String typeName;
    public boolean isClass = false;
    public boolean isArray = false;
    public int dim = 0;

    public ClassDefNode classDecl = null;

    public Type(){

    }

    public boolean isReferenceType() {
        return dim > 0 || isClass;
    }

    public boolean equal(Type other){
        return this.typeName.equals(other.typeName);
    }

    public Type(String name){
        this.typeName = name;
    }

}
