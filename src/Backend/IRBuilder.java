package Backend;

import AST.*;
import AST.expr.*;
import AST.stmt.*;
import Util.Scope.*;

import MIR.*;
import MIR.Statmemt.*;

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
        currentScope.entities.put(it.varName, new register());
    }
    public void visit(TypeNode it){
        //?
        //todo
    }

    //public void visit(StmtNode it){}
    public void visit(BreakNode it){
        block destination = new block();
        currentBlock.push_back(new jump(destination));
        currentBlock = destination;
        mainfn.blocks.add(destination);
    }
    public void visit(ContinueNode it){
        //todo
        //循环的结点要不要重复？还是jump out?
    }
    public void visit(ExprStmtNode it){
        it.exprNode.accept(this);
    }
    public void visit(ForStmtNode it){
        //todo
    }
    public void visit(IfStmtNode it){
        it.condition.accept(this);
        block trueBranch = new block(), falseBranch = new block();
        currentBlock.push_back(new branch(it.condition.val,trueBranch,falseBranch));

        block destination = new block();
        currentBlock = trueBranch;
        for (StmtNode x : it.thenStmts) {
            x.accept(this);
        }
        currentBlock.push_back(new jump(destination));
        currentBlock = falseBranch;
        for (StmtNode x : it.elseStmts) {
            x.accept(this);
        }
        currentBlock.push_back(new jump(destination));
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
        it.condition.accept(this);
        block whileBlock = new block();
        currentBlock.push_back(new whileLoop(it.condition.val, whileBlock));

        block destination = new block();
        currentBlock = whileBlock;
        for (StmtNode x : it.stmts) {
            x.accept(this);
        }
        currentBlock.push_back(new jump(destination));
        currentBlock = destination;

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
