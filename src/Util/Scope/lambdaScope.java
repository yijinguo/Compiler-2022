package Util.Scope;

import Util.Type;

public class lambdaScope extends Scope{
    public boolean isGlob = false;

    public Type returnType;
    public lambdaScope(Scope parent){
        super(parent);
    }

    public lambdaScope(Scope parent, boolean isGlob){
        super(parent);
        this.isGlob = isGlob;
    }
}
