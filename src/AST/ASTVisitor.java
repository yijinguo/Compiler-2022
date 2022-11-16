package AST;

import AST.expr.*;
import AST.stmt.*;
import com.sun.tools.javac.Main;

public interface ASTVisitor {
    public void visit(RootNode it);
    public void visit(MainFnNode it);
    public void visit(ClassDefNode it);
    public void visit(ClassBuildNode it);
    public void visit(FuncDefNode it);
    public void visit(ParameterListNode it);
    public void visit(VarDefNode it);
    public void visit(VarDefUnitNode it);
    public void visit(TypeNode it);

    //public void visit(StmtNode it);
    public void visit(BreakNode it);
    public void visit(ContinueNode it);
    public void visit(ExprStmtNode it);
    public void visit(ForStmtNode it);
    public void visit(IfStmtNode it);
    public void visit(ReturnStmtNode it);
    public void visit(SuiteNode it);
    public void visit(WhileStmtNode it);

    //public void visit(ExprNode it);
    public void visit(ArrayExprNode it);
    public void visit(AssignExprNode it);
    public void visit(AtomExprNode it);
    public void visit(BinaryExprNode it);
    public void visit(ExprListNode it);
    public void visit(FuncExprNode it);
    public void visit(LambdaExprNode it);
    public void visit(MemberExprNode it);
    public void visit(NewExprNode it);
    public void visit(PreAddExprNode it);
    public void visit(UnaryExprNode it);

}
