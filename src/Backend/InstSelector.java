package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFunction;
import Assembly.ASMProgram;
import Assembly.Inst.*;
import Assembly.Operand.*;
import MIR.Statmemt.*;
import MIR.entity.constant;
import MIR.entity.entity;
import MIR.entity.register;
import MIR.terminalStmt.*;
import Middlend.IRBuilder;
import MIR.*;

import java.util.HashMap;
import java.util.Map;

public class InstSelector implements IRVisitor {

    public void visit(function it){}
    public void visit(block it){}

    //statement
    public void visit(alloca it){}
    public void visit(load it){}
    public void visit(store it){}
    public void visit(binary it){}
    public void visit(unary it){}
    public void visit(icmp it){}
    public void visit(call it){}
    public void visit(getelementptr it){}
    public void visit(createPtr it){}

    //terminal
    public void visit(branch it){}
    public void visit(jump it){}
    public void visit(ret it){}
    public void visit(loop it){}
    
}
