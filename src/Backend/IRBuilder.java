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

    public void visit(RootNode it){}
    public void visit(MainFnNode it){}
    public void visit(ClassDefNode it){}
    public void visit(ClassBuildNode it){}
    public void visit(FuncDefNode it){}
    public void visit(ParameterListNode it){}
    public void visit(VarDefNode it){}
    public void visit(VarDefUnitNode it){}
    public void visit(TypeNode it){}

    //public void visit(StmtNode it){}
    public void visit(BreakNode it){}
    public void visit(ContinueNode it){}
    public void visit(ExprStmtNode it){}
    public void visit(ForStmtNode it){}
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
    public void visit(ReturnStmtNode it){}
    public void visit(SuiteNode it){}
    public void visit(WhileStmtNode it){}

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
