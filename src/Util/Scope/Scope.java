package Util.Scope;


import AST.ClassDefNode;
import AST.FuncDefNode;
import AST.VarDefUnitNode;
import MIR.entity.*;
import Util.Type;
import Util.error.semanticError;

import java.util.HashMap;

public class Scope {

    public HashMap<String, VarDefUnitNode> variableMembers = new HashMap<>();
    public HashMap<String, FuncDefNode> functionMembers = new HashMap<>();
    public HashMap<String, entity> entities = new HashMap<>();
    public Scope parentScope;
    public boolean hasReturn = false;

    public ClassDefNode InClass = null;

    public Scope(Scope parentScope){
        this.parentScope = parentScope;
    }

    public Scope(Scope parentScope, ClassDefNode InClass){
        this.parentScope = parentScope;
        this.InClass = InClass;
    }

    public void add_var(VarDefUnitNode unit){
        if (variableMembers.containsKey(unit.varName) || functionMembers.containsKey(unit.varName))
            throw new semanticError("Variable Name Exits", unit.pos);
        else
            variableMembers.put(unit.varName, unit);
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

    public boolean have_var(String name){
        return variableMembers.containsKey(name);
    }

    public boolean have_func(String name){
        return functionMembers.containsKey(name);
    }

    public Scope find_func(){
        Scope t = this;
        while (t != null) {
            if (t instanceof funcScope) return t;
            t = t.parentScope;
        }
        return null;
    }

    public void put_return(){
        hasReturn = true;
        Scope tmp = this;
        while (tmp != null) {
            tmp.hasReturn = true;
            tmp = tmp.parentScope;
        }
    }

    public Type catch_class(){
        Scope tmp = this;
        while (tmp != null) {
            if (tmp instanceof classScope) {
                return ((classScope) tmp).ClassType;
            }
            tmp = tmp.parentScope;
        }
        return null;
    }

    public entity getEntity(String name) {
        if (entities.containsKey(name))
            return entities.get(name);
        else
            return parentScope == null ? null : parentScope.getEntity(name);
    }
}
