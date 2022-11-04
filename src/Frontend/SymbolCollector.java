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

    public SymbolCollector(globalScope GlobalScope){
        this.GlobalScope = GlobalScope;
    }

    public void visit(RootNode it){
        it.DefList.forEach(def -> def.accept(this));
    }

    public void visit(ClassDefNode it) {
        if (GlobalScope.haveType(it.name)) throw new syntaxError("Class name exists", it.pos);
        //Type newType = new Type(it.name);
        //newType.classDecl = it;
        //GlobalScope.add_type(newType, it.pos);
        GlobalScope.add_class(it.name, it, it.pos);
        /*
        for (VarDefNode x : it.varList)
            for (VarDefUnitNode y : x.units) GlobalScope.add_var(y);
        for (FuncDefNode x : it.funcList)
            GlobalScope.add_func(x.funcName, x);*/
        classVisiting = it;
        //if (classVisiting.classBuilder == null)
        //    throw new syntaxError("Lack Constructor", it.pos);
        //if (classVisiting.constructor.size() > 1) throw new syntaxError("Multiple Constructor Definitions", it.pos);
        //visit(classVisiting.constructor.get(0));
        visit(classVisiting.classBuilder);
        //classVisiting.varList.forEach(x->x.accept(this));
        //classVisiting.funcList.forEach(x->x.accept(this));
        for (VarDefNode vars : classVisiting.varList) visit(vars);
        for (FuncDefNode functions : classVisiting.funcList) visit(functions);
        classVisiting = null;
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
        if (classVisiting != null) {
            classVisiting.funcList.add(it);
        } else {
            GlobalScope.add_func(it.funcName, it);
        }
    }
    public void visit(ParameterListNode it){ }
    public void visit(VarDefNode it){
        //TypeNode varType = new TypeNode(it.pos,it.getTypeName());
        //Type type = GlobalScope.getType(it.getTypeName(), it.pos);
        if (classVisiting != null) {
            for (VarDefUnitNode x : it.units)
                if (classVisiting.have_var(x.varName)) throw new syntaxError("Renaming Class Member", it.pos);
            classVisiting.varList.add(it);
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