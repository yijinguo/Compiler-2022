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

}
