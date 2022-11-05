package Frontend;

import AST.*;
import AST.expr.*;
import AST.stmt.*;
import Util.Scope.*;
import Util.Type;
import Util.error.*;

public class SymbolCollector implements ASTVisitor{

    private globalScope GlobalScope;

    private boolean classCollected = false;

    private ClassDefNode classVisiting = null;

    private boolean catchClassType = false;

    public SymbolCollector(globalScope GlobalScope){
        this.GlobalScope = GlobalScope;
    }

    public void visit(RootNode it){
        for (int i = 0; i < it.DefList.size(); ++i) {
            ASTNode node = it.DefList.get(i);
            if (node instanceof ClassDefNode) {
                node.accept(this);
            }
        }
        catchClassType = true;
        it.DefList.forEach(def -> def.accept(this));
    }

    public void visit(ClassDefNode it) {
        if (!catchClassType) {
            if (GlobalScope.haveType(it.name)) throw new syntaxError("Class name exists", it.pos);
            GlobalScope.add_class(it.name, it, it.pos);
        } else {
            classVisiting = it;
            if (classVisiting.classBuilder != null)
                classVisiting.classBuilder.accept(this);
            for (int i = 0; i < classVisiting.varList.size(); ++i)
                classVisiting.varList.get(i).accept(this);
            for (int i = 0; i < classVisiting.funcList.size(); ++i)
                classVisiting.funcList.get(i).accept(this);
            classVisiting = null;
        }
    }
    public void visit(ClassBuildNode it){ }
    public void visit(FuncDefNode it){
        if (!GlobalScope.haveType(it.returnType.type.typeName)) throw new syntaxError("Invalid Type", it.pos);
        if (it.funcName.equals("main")) {
            if (it.params != null || !it.returnType.type.typeName.equals("int"))
                throw new syntaxError("Invalid Main Definition", it.pos);
        }
        if (classVisiting == null) {
            if (GlobalScope.haveType(it.funcName)) throw new syntaxError("ClassName Exits", it.pos);
        } else {
            if (classVisiting.name.equals(it.funcName)) throw new syntaxError("Constructor Exists", it.pos);
            boolean have = false;
            for (FuncDefNode func : classVisiting.funcList) {
                if (func.funcName.equals(it.funcName)) {
                    if (!have) have = true;
                    else throw new syntaxError("Renaming Class Member", it.pos);
                }
            }
        }
        if (classVisiting == null)
            GlobalScope.add_func(it.funcName, it);
    }
    public void visit(ParameterListNode it){ }
    public void visit(VarDefNode it){
        if (classVisiting != null) {
            boolean have = false;
            for (VarDefUnitNode x : it.units) {
                if (classVisiting.count(x.varName) != 1) {
                    throw new syntaxError("Renaming Class Member", it.pos);
                }
                /*
                if (classVisiting.have_var(x.varName)) {
                    if (!have) have = true;
                    else throw new syntaxError("Renaming Class Member", it.pos);
                }*/
            }
        }
        if (!GlobalScope.haveType(it.getTypeName()))
            throw new syntaxError("Invalid Type", it.pos);
    }
    public void visit(VarDefUnitNode it){ }
    public void visit(TypeNode it){ }

    //public void visit(StmtNode it){ }
    public void visit(BreakNode it){ }
    public void visit(ContinueNode it){ }
    public void visit(ExprStmtNode it){ }
    public void visit(ForStmtNode it){ }
    public void visit(IfStmtNode it){ }
    public void visit(ReturnStmtNode it){ }
    public void visit(SuiteNode it){ }
    public void visit(WhileStmtNode it){ }

    //public void visit(ExprNode it){ }
    public void visit(ArrayExprNode it){ }
    public void visit(AssignExprNode it){ }
    public void visit(AtomExprNode it){ }
    public void visit(BinaryExprNode it){ }
    public void visit(ExprListNode it){ }
    public void visit(FuncExprNode it){ }
    public void visit(LambdaExprNode it){ }
    public void visit(MemberExprNode it){ }
    public void visit(NewExprNode it){ }
    public void visit(PreAddExprNode it){ }
    public void visit(UnaryExprNode it){ }
    
}