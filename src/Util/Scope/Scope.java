package Util.Scope;


import AST.FuncDefNode;
import AST.VarDefUnitNode;
import Util.error.semanticError;

import java.util.HashMap;

public class Scope {

    public HashMap<String, VarDefUnitNode> variableMembers = new HashMap<>();
    public HashMap<String, FuncDefNode> functionMembers = new HashMap<>();

    public Scope parentScope;

    public Scope(Scope parentScope){
        this.parentScope = parentScope;
    }

    public void add_var(String varName, VarDefUnitNode variable){
        if (variableMembers.containsKey(varName) || functionMembers.containsKey(varName))
            throw new semanticError("Variable Name Exits", variable.pos);
        else
            variableMembers.put(varName, variable);
    }

    public void add_func(String funcName, FuncDefNode function){
        if (functionMembers.containsKey(funcName) || variableMembers.containsKey(funcName))
            throw new semanticError("Function Name Exits", function.pos);
        else
            functionMembers.put(funcName, function);
    }

    public VarDefUnitNode getVar(String name){
        Scope current = this;
        while (current != null) {
            if (current.variableMembers.containsKey(name)) return current.variableMembers.get(name);
            current = current.parentScope;
        }
        return null;
    }

    public FuncDefNode getFunc(String name){
        Scope current = this;
        while (current != null) {
            if (current.functionMembers.containsKey(name)) return current.functionMembers.get(name);
            current = current.parentScope;
        }
        return null;
    }

}
