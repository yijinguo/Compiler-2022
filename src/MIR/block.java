package MIR;

import MIR.Statmemt.*;
import MIR.terminalStmt.*;
import Util.error.*;
import Util.position;

import java.util.LinkedList;

public class block {
    public int label;
    public String block_name;
    public LinkedList<statement> stmtList = new LinkedList<>();

    public terminalStmt tailStmt = null;
    public block parentBlock = null;

    public block(int l){
        this.label = l;
    }

    public block(int l, block parentBlock, String block_name){
        this.label = l;
        this.parentBlock = parentBlock;
        this.block_name = block_name;
    }

    public void push_back(statement stmt){
        stmtList.add(stmt);
        if (stmt instanceof terminalStmt) {
            if (tailStmt != null)
                throw new internalError("Multiple Tails of a Block", new position(0,0));
            tailStmt = (terminalStmt) stmt;
        }
    }

    public String toString(){
        return block_name + "_" + label;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

}
