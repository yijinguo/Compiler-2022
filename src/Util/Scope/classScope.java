package Util.Scope;

import AST.FuncDefNode;
import AST.VarDefNode;
import AST.VarDefUnitNode;
import Util.*;

public class classScope extends Scope{
    public Type ClassType;

    public classScope(Scope parentScope, Type classType) {
        super(parentScope);
        this.ClassType=classType;
        for (FuncDefNode func : ClassType.classDecl.funcList) {
            this.add_func(func.funcName, func);
        }
        for (VarDefNode var : ClassType.classDecl.varList) {
            for (VarDefUnitNode varUnit : var.units) this.add_var(varUnit);
        }
    }
}
