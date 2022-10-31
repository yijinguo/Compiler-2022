package Util.Scope;

import AST.ParameterListNode;
import AST.TypeNode;
import AST.VarDefUnitNode;

public class funcScope extends Scope{
    public TypeNode returnType;
    public ParameterListNode parameter;

    public boolean isConstructor = false;

    public funcScope(Scope parentScope, TypeNode returnType, ParameterListNode parameter) {
        super(parentScope);
        this.parameter = parameter;
        this.returnType = returnType;
        if(this.parameter != null){
            for(VarDefUnitNode var: this.parameter.varList){
                super.add_var(var);
            }
        }
    }
}
