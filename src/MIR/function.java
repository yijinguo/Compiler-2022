package MIR;

import AST.ASTVisitor;
import MIR.entity.entity;
import MIR.entity.register;
import MIR.type.IRType;

import java.util.*;

public class function{

    public IRType returnType;
    public entity returnReg;
    public String funcName;
    public boolean internal = false;
    public ArrayList<register> paraList = new ArrayList<>();
    public LinkedList<entity> entities = new LinkedList<>();
    public block rootBlock = null;
    public ArrayList<block> blocks = new ArrayList<>();
    public int block_cnt = 0;
    public function(IRType returnType, String funcName){
        this.returnType = returnType;
        this.funcName = funcName;
        rootBlock = new block(0);
        blocks.add(rootBlock);
    }

    public function(IRType returnType, String funcName, boolean internal){
        this.returnType = returnType;
        this.funcName = funcName;
        rootBlock = new block(0);
        blocks.add(rootBlock);
        this.internal = internal;
    }

    public void push_entity(String name, entity entry){
        this.entities.add(entry);
        block_cnt++;
    }

    public entity getEntity(String name) {
        for (entity x : this.entities) {
            if (((register) x).identity.equals(name)) {
                return x;
            }
        }
        return null;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

}
