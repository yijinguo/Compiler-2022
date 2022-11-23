package Backend;

import AST.*;
import AST.expr.*;
import AST.stmt.*;
import MIR.terminalStmt.*;
import Util.Scope.*;

import MIR.*;

public class IRBuilder implements ASTVisitor {

    public block currentBlock;
    public mainFn mainfn;
    public Scope currentScope;
    public globalScope gScope;

    public IRBuilder(mainFn mainfn, globalScope gScope){
        this.mainfn = mainfn;
        this.gScope = gScope;
        this.currentBlock = mainfn.rootBlock;
        this.currentScope = gScope;
    }

    public void visit(RootNode it){
        it.mainFn.accept(this);
    }
    public void visit(MainFnNode it){
        currentScope = new Scope(currentScope);
        it.stmts.forEach(s -> s.accept(this));
        currentScope = currentScope.parentScope;
    }
    public void visit(ClassDefNode it){}
    public void visit(ClassBuildNode it){}
    public void visit(FuncDefNode it){}
    public void visit(ParameterListNode it){}
    public void visit(VarDefNode it){
        for (VarDefUnitNode x : it.units) x.accept(this);
    }
    public void visit(VarDefUnitNode it){
        currentScope.entities.put(it.varName, new register(it.type.type));
    }
    public void visit(TypeNode it){
        //?
        //todo
    }

    //public void visit(StmtNode it){}
    public void visit(BreakNode it){
        block destination = new block(currentBlock);
        currentBlock.push_back(new jump(destination));
    }
    public void visit(ContinueNode it){
        block tmp = currentBlock;
        while (!(tmp.tailStmt instanceof whileLoop)){
            tmp = tmp.parentBlock;
        }
        currentBlock.push_back(tmp.tailStmt);
    }
    public void visit(ExprStmtNode it){
        it.exprNode.accept(this);
    }
    public void visit(ForStmtNode it){
        //todo
        //注意break，continue
        block start = new block(currentBlock), forBlock = new block(currentBlock);
        currentBlock.push_back(new forLoop(start, forBlock));

        currentBlock = start;
        if (it.varDef != null) it.varDef.accept(this);
        else if (it.init != null) it.init.accept(this);
        if (it.condition != null) it.condition.accept(this);
        if (it.step != null) it.step.accept(this);

        currentBlock = forBlock;
        for (StmtNode x : it.stmts) {
            x.accept(this);
        }

        block destination;
        if (currentBlock.tailStmt instanceof jump) {
            //have breakStmt
            destination = ((jump) currentBlock.tailStmt).destination;
        } else {
            destination = new block(currentBlock);
            currentBlock.push_back(new jump(destination));
        }

        currentBlock = destination;
        mainfn.blocks.add(start);
        mainfn.blocks.add(forBlock);
        mainfn.blocks.add(destination);
    }
    public void visit(IfStmtNode it){
        it.condition.accept(this);
        block trueBranch = new block(currentBlock), falseBranch = new block(currentBlock);
        currentBlock.push_back(new branch(it.condition.val,trueBranch,falseBranch));

        block destination = new block(currentBlock);
        currentBlock = trueBranch;
        for (StmtNode x : it.thenStmts) {
            x.accept(this);
        }
        if (currentBlock.tailStmt == null) currentBlock.push_back(new jump(destination));
        currentBlock = falseBranch;
        for (StmtNode x : it.elseStmts) {
            x.accept(this);
        }
        if (currentBlock.tailStmt == null) currentBlock.push_back(new jump(destination));
        currentBlock = destination;

        mainfn.blocks.add(trueBranch);
        mainfn.blocks.add(falseBranch);
        mainfn.blocks.add(destination);
    }
    public void visit(ReturnStmtNode it){
        entity value;
        if (it.expr != null) {
            it.expr.accept(this);
            value = it.expr.val;
        } else value = null;
        currentBlock.push_back(new ret(value));
    }
    public void visit(SuiteNode it){
        for (StmtNode x : it.stmts)
            x.accept(this);
    }
    public void visit(WhileStmtNode it){
        block condition = new block(currentBlock), whileBlock = new block(currentBlock);
        currentBlock.push_back(new whileLoop(condition, whileBlock));

        currentBlock = condition;
        it.condition.accept(this);
        currentBlock.push_back(new jump(whileBlock));

        currentBlock = whileBlock;
        for (StmtNode x : it.stmts) {
            x.accept(this);
        }

        block destination;
        if (currentBlock.tailStmt instanceof jump) {
            //have breakStmt
            destination = ((jump) currentBlock.tailStmt).destination;
        } else {
            destination = new block(currentBlock);
            currentBlock.push_back(new jump(destination));
        }
        currentBlock = destination;

        mainfn.blocks.add(condition);
        mainfn.blocks.add(whileBlock);
        mainfn.blocks.add(destination);
    }

    //public void visit(ExprNode it){}
    public void visit(ArrayExprNode it){}
    public void visit(AssignExprNode it){}
    public void visit(AtomExprNode it){}
    public void visit(BinaryExprNode it){}
    public void visit(ExprListNode it){}
    public void visit(FuncExprNode it){}
    public void visit(LambdaExprNode it){}
    public void visit(MemberExprNode it){}
    public void visit(NewExprNode it){}
    public void visit(PreAddExprNode it){}
    public void visit(UnaryExprNode it){}
}
