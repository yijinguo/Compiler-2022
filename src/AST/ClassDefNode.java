package AST;

import Util.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassDefNode extends ASTNode{

    public String name;
    public ClassBuildNode classBuilder;
    public ArrayList<VarDefNode> varList = new ArrayList<>();
    public ArrayList<FuncDefNode> funcList = new ArrayList<>();
    public HashMap<String, FuncDefNode> funcMem = new HashMap<>();
    public HashMap<String, VarDefUnitNode> varMem = new HashMap<>();

    public ClassDefNode(position pos){
        super(pos);
    }

    public boolean have_var(String name){
        return varMem.containsKey(name);
    }

    public int count(String name){
        int num = 0;
        for (VarDefNode x : varList) {
            for (VarDefUnitNode y : x.units) {
                if (y.varName.equals(name)) num++;
            }
        }
        for (FuncDefNode x : funcList) {
            if (x.funcName.equals(name)) num++;
        }
        return num;
    }

    public boolean have_func(String name){
        return funcMem.containsKey(name);
    }

    public FuncDefNode get_func(String name){
        return funcMem.get(name);
    }

    public VarDefUnitNode get_var(String name) {
        return varMem.get(name);
    }

    public ClassDefNode(position pos, String name){
        super(pos);
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
