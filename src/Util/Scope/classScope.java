package Util.Scope;

import AST.FuncDefNode;
import AST.VarDefNode;
import AST.VarDefUnitNode;
import Util.*;

public class classScope extends Scope{

    public Type ClassType;

    public classScope(Scope parentScope, String name) {
        super(parentScope);
        ClassType = new Type(name);
    }
}
