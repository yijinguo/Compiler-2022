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
    public Type(String name){
        this.typeName = name;
    }

    public Type(Type other){
        this.typeName = other.typeName;
        this.isArray = other.isArray;
        this.isClass = other.isClass;
        this.dim = other.dim;
    }

    public boolean equals(Type other){
        return this.typeName.equals(other.typeName);
    }

}
