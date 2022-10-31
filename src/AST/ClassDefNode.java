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

    public boolean have_func(String name){
        return funcMem.containsKey(name);
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
