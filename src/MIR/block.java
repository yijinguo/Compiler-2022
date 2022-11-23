package MIR;

import MIR.Statmemt.*;
import MIR.terminalStmt.*;
import Util.error.*;
import Util.position;

import java.util.ArrayList;
import java.util.LinkedList;

public class block {
    public LinkedList<statement> stmtList = new LinkedList<>();
    public terminalStmt tailStmt = null;
    public block parentBlock = new block();

    public block(){}

    public block(block parentBlock){
        this.parentBlock = parentBlock;
    }

    public void push_back(statement stmt){
        stmtList.add(stmt);
        if (stmt instanceof terminalStmt) {
            if (tailStmt != null)
                throw new internalError("Multiple Tails of a Block", new position(0,0));
            tailStmt = (terminalStmt) stmt;
        }
    }

    public ArrayList<statement> stmts(){
        return new ArrayList<>(stmtList);
    }
    public ArrayList<block> successors(){
        ArrayList<block> ret = new ArrayList<>();
        if (tailStmt instanceof branch) {
            ret.add(((branch) tailStmt).trueBranch);
            ret.add(((branch) tailStmt).falseBranch);
        } else if (tailStmt instanceof forLoop) {
            ret.add(((forLoop) tailStmt).forBlock);
        } else if (tailStmt instanceof whileLoop){
            ret.add(((whileLoop) tailStmt).whileBlock);
        } else if (tailStmt instanceof jump) {
            ret.add(((jump) tailStmt).destination);
        }
        return ret;
    }

}
