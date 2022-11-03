package Frontend;

import Util.*;
import Util.Scope.*;
import Util.error.*;
import AST.*;
import AST.stmt.*;
import AST.expr.*;

import java.util.ArrayList;


public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    final private globalScope GlobalScope;

    private Type BoolType, IntType, VoidType, StringType, NullType;

    public SemanticChecker(globalScope GlobalScope){
        this.currentScope = this.GlobalScope = GlobalScope;
        BoolType = GlobalScope.getType("bool", null);
        IntType = GlobalScope.getType("int", null);
        VoidType = GlobalScope.getType("void", null);
        StringType = GlobalScope.getType("string", null);
        NullType = GlobalScope.getType("null", null);
    }

    public void visit(RootNode it){
        FuncDefNode mainFunc = GlobalScope.getFunc("main");
        if (mainFunc == null || !mainFunc.returnType.type.typeName.equals("int") || mainFunc.params != null)
            throw new semanticError("Without MainFunction", it.pos);
        for (var def : it.DefList) {
            def.accept(this);
        }
    }

    public void visit(ClassDefNode it){
        currentScope = new classScope(currentScope, new Type(it.name));
        it.varList.forEach(x->x.accept(this));
        if (it.classBuilder != null) {
            if (it.name.equals(it.classBuilder.name))
                it.classBuilder.accept(this);
            else
                throw new semanticError("Lack ClassBuild", it.pos);
        }
        it.funcList.forEach(x->x.accept(this));
        currentScope = currentScope.parentScope;
    }
    public void visit(ClassBuildNode it){
        currentScope = new funcScope(currentScope);
        ((funcScope) currentScope).isConstructor = true;
        it.suites.accept(this);
        currentScope = currentScope.parentScope;
    }
    public void visit(FuncDefNode it){
        it.returnType.accept(this);
        currentScope = new funcScope(currentScope);
        ((funcScope) currentScope).returnType = it.returnType;
        if (it.params != null)
            it.params.accept(this);
        it.stmts.forEach(x->x.accept(this));
        //关于无返回值的问题
        if (!it.returnType.type.equals(VoidType) && !it.funcName.equals("main"))
            throw new semanticError("Lack Return", it.pos);
        currentScope = currentScope.parentScope;
    }
    public void visit(ParameterListNode it){
        it.varList.forEach(x->x.accept(this));
    }
    public void visit(VarDefNode it){
        it.units.forEach(x-> x.accept(this));
    }
    public void visit(VarDefUnitNode it){
        it.type.accept(this);
        if (it.init != null) {
            it.init.accept(this);
        }
        if (it.type.type.isArray) {
            if (it.init == null || it.type.type.dim != it.init.type.dim)
                throw new semanticError("Unmatched Dimension", it.pos);
        }
        if (currentScope.have_var(it.varName))
            throw new semanticError("Redefined of variable", it.pos);
        currentScope.add_var(it);
    }
    public void visit(TypeNode it) {
        switch (it.type.typeName) {
            case "int":
            case "bool":
            case "string":
            case "void":
            case "null":
            case "this":
                break;
            default:
                if (!GlobalScope.haveType(it.type.typeName))
                    throw new semanticError("Undefined Type", it.pos);
        }
    }

    //public void visit(StmtNode it){}
    public void visit(BreakNode it){
        if (! (currentScope instanceof loopScope))
            throw new syntaxError("Break Out of Loop", it.pos);
    }
    public void visit(ContinueNode it){
        if (! (currentScope instanceof loopScope))
            throw new syntaxError("Break Out of Loop", it.pos);
    }
    public void visit(ExprStmtNode it){
        it.exprNode.accept(this);
    }
    public void visit(ForStmtNode it){
        if (it.varDef != null) it.varDef.accept(this);
        else it.init.accept(this);
        if (it.condition == null) throw new syntaxError("Lack Condition", it.pos);
        it.condition.accept(this);
        if (!it.condition.type.equals(BoolType)) throw new syntaxError("Invalid Condition", it.pos);
        if (it.step != null) it.step.accept(this);
        currentScope = new loopScope(currentScope);
        it.stmts.forEach(x->x.accept(this));
        currentScope = currentScope.parentScope;
    }
    public void visit(IfStmtNode it){
        if (it.condition == null) throw new syntaxError("Lack Condition", it.pos);
        it.condition.accept(this);
        if (!it.condition.type.equals(BoolType))
            throw new semanticError("Invalid Condition", it.pos);
        currentScope = new loopScope(currentScope);
        //没有考虑if和else的大括号省略问题
        if (it.thenStmts.size() != 0)
            it.thenStmts.forEach(x->x.accept(this));
        if (it.elseStmts.size() != 0)
            it.elseStmts.forEach(x->x.accept(this));
        currentScope = currentScope.parentScope;
    }
    public void visit(ReturnStmtNode it){
        if (it.expr == null) {
            if (((funcScope) currentScope).returnType.type.equals(VoidType)) return;
            else throw new syntaxError("Lack Return", it.pos);
        }
        it.expr.accept(this);
        if (!(currentScope instanceof funcScope))
            throw new syntaxError("Invalid Return", it.pos);
        if (!it.expr.type.equals(((funcScope) currentScope).returnType.type))
            throw new semanticError("Unmatched ReturnType", it.pos);
    }
    public void visit(SuiteNode it){
        currentScope = new Scope(currentScope);
        it.stmts.forEach(x->x.accept(this));
        currentScope = currentScope.parentScope;
    }
    public void visit(WhileStmtNode it){
        if (it.condition == null) throw new syntaxError("Lack Condition", it.pos);
        it.condition.accept(this);
        if (!it.condition.type.equals(BoolType))
            throw new semanticError("Invalid Condition", it.pos);
        currentScope = new loopScope(currentScope);
        if (it.stmts != null) {
            it.stmts.forEach(x->x.accept(this));
        }
        currentScope = currentScope.parentScope;
    }

    //public void visit(ExprNode it){}
    public void visit(ArrayExprNode it){
        it.arrayName.accept(this);
        if (it.index != null) it.index.accept(this);
        /*
        VarDefUnitNode var = new VarDefUnitNode(it.pos);
        Scope s = new Scope(currentScope);
        while (s != GlobalScope) {
            if (s.getVar(it.arrayName.str) != null) {
                var = s.getVar(it.arrayName.str);
                break;
            }
            s = s.parentScope;
        }
         */
        VarDefUnitNode var = currentScope.getVar(it.arrayName.str);
        if (var.type.type.dim != it.arrayName.type.dim + 1) throw new syntaxError("Unmatched ArrayDim", it.pos);
    }
    public void visit(AssignExprNode it){
        it.lhs.accept(this);
        it.rhs.accept(this);
        if(!it.lhs.isAssignable())
            throw new syntaxError("It is Not Assignable", it.pos);
        if (!it.lhs.type.equals(it.rhs.type))
            throw new syntaxError("Unmatched AssignType", it.pos);
    }
    public void visit(AtomExprNode it){
        if (it.type == null) {
            VarDefUnitNode var = currentScope.getVar(it.str);
            FuncDefNode func = currentScope.getFunc(it.str);
            ClassDefNode classDef = GlobalScope.getClass(it.str, it.pos);
            if (var == null && func == null && classDef == null)
                throw new semanticError("Undefined AtomExpr", it.pos);
            if (var != null) {
                it.type = var.type.type;
            } else if (func != null) {
               it.funcDef = func;
            } else {
                it.type = new Type();
                it.type.isClass = true;
            }
        }
    }
    public void visit(BinaryExprNode it){
        it.lhs.accept(this);
        it.rhs.accept(this);
        Type lType = it.lhs.type;
        Type rType = it.rhs.type;
        if (lType.isArray || rType.isArray) {
            if (!it.op.equals("==") && !it.op.equals("!="))
                throw new syntaxError("Invalid BinaryExpr Type", it.pos);
            if (lType.isArray && rType != NullType || rType.isArray && lType != NullType)
                throw new syntaxError("Invalid Type", it.pos);
            it.type = BoolType;
            return;
        }
        if (it.op.equals("==") || it.op.equals("!=")) {
            if (!lType.equals(rType)) throw new semanticError("Unmatched Type", it.pos);
            if (!lType.equals(BoolType) && !lType.equals(IntType) && !lType.equals(StringType)) {
                if (GlobalScope.getClass(it.lhs.str, it.pos) == null)
                    throw new syntaxError("Invalid Type", it.pos);
            }
            it.type = BoolType;
        }  else if (it.op.equals("<") || it.op.equals("<=") || it.op.equals(">") || it.op.equals(">=")) {
            if (!lType.equals(rType)) throw new semanticError("Unmatched Type", it.pos);
            if (!lType.equals(BoolType) && !lType.equals(IntType) && !lType.equals(StringType))
                throw new syntaxError("Invalid Type", it.pos);
            it.type = BoolType;
        } else if (it.op.equals("+")) {
            if (!lType.equals(rType)) throw new semanticError("Unmatched Type", it.pos);
            if (!lType.equals(BoolType) && !lType.equals(IntType) && !lType.equals(StringType))
                throw new syntaxError("Invalid Type", it.pos);
            if (lType.equals(IntType)) it.type = IntType;
            else if (lType.equals(BoolType)) it.type = IntType;
            else if (lType.equals(StringType)) it.type = StringType;
        } else {
            if (!lType.equals(rType)) throw new semanticError("Unmatched Type", it.pos);
            if (!lType.equals(BoolType) && !lType.equals(IntType))
                throw new syntaxError("Invalid Type", it.pos);
            if (it.op.equals("||") || it.op.equals("&&")) it.type = BoolType;
            else it.type = lType;
        }
    }
    public void visit(ExprListNode it){
        it.exprs.forEach(x->x.accept(this));
    }
    public void visit(FuncExprNode it){
        it.funcName.accept(this);
        if (it.lists != null) it.lists.accept(this);
        FuncDefNode t = null;
        Scope s = new Scope(currentScope);
        while (!(s instanceof globalScope)) {
            s = s.parentScope;
            t = s.getFunc(it.funcName.str);
            if (t != null) break;
        }
        if (t == null) throw new syntaxError("Undefined Function", it.pos);
        syntaxError paramsError = new syntaxError("Unmatched ParameterList", it.pos);
        if (it.lists == null && t.params != null) throw paramsError;
        if (it.lists != null) {
            if (t.params == null || t.params.varList.size() != it.lists.exprs.size())
                throw paramsError;
            for (int i = 0; i < it.lists.exprs.size(); ++i) {
                //type均未写明，此处应该调用一次AtomExpr的accept,给type赋值
                it.lists.exprs.get(i).accept(this);
                if (!it.lists.exprs.get(i).type.equals(t.params.varList.get(i).type.type))
                    throw paramsError;
            }
        }
    }
    public void visit(LambdaExprNode it){}
    public void visit(MemberExprNode it){
        it.name.accept(this);
        if (GlobalScope.getClass(it.name.str, it.pos) == null)
            throw new syntaxError("Undefined Class", it.pos);
        ClassDefNode classNode = GlobalScope.getClass(it.name.str, it.pos);
        if (!classNode.have_var(it.name.str) && !classNode.have_func(it.name.str)) throw new syntaxError("Undefined Class Member", it.pos);
    }
    public void visit(NewExprNode it){
        if (!GlobalScope.haveType(it.typeName))
            throw new semanticError("Undefined Type", it.pos);
        for (int i = it.dim - 1; i > 0; --i) {
            if (it.sizeList.get(i) != null && it.sizeList.get(i - 1) == null)
                throw new syntaxError("Invalid New Expression", it.pos);
        }
    }
    public void visit(PreAddExprNode it){
        it.expr.accept(this);
        if (it.expr.str == null) throw new syntaxError("Not a variable", it.pos);
        if (it.expr.type.equals(IntType)) throw new semanticError("Not a Variable", it.pos);
        if (currentScope.getVar(it.expr.str) == null) throw new syntaxError("Undefined Variable", it.pos);
    }
    public void visit(UnaryExprNode it){
        it.expr.accept(this);
        if (it.expr.str == null) throw new syntaxError("Not a variable", it.pos);
        if (it.expr.type.equals(IntType)) throw new semanticError("Not a Variable", it.pos);
        if (currentScope.getVar(it.expr.str) == null) throw new syntaxError("Undefined Variable", it.pos);
    }
 
}