package Util.Scope;

import AST.ClassDefNode;
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

    public void create_class(ClassDefNode it){
        for (VarDefNode x : it.varList) {
            for (VarDefUnitNode y : x.units) {
                variableMembers.put(y.varName, y);
            }
        }
        for (FuncDefNode x : it.funcList) {
            functionMembers.put(x.funcName, x);
        }
    }

}
