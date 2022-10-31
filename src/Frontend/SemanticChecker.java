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
        FuncDefNode mainFunc = GlobalScope.get_func("main");
        if (mainFunc == null || !mainFunc.returnType.type.typeName.equals("int") || mainFunc.params != null)
            throw new syntaxError("Incorrect Main Function", it.pos);
        for (var def : it.DefList) {
            def.accept(this);
        }
    }

    public void visit(ClassDefNode it){}
    public void visit(ClassBuildNode it){}
    public void visit(FuncDefNode it){
        if (currentScope != GlobalScope && !(currentScope instanceof classScope))
            throw new syntaxError("Can't Define Function Here", it.pos);
        //it.accept(this);

        currentScope = new funcScope(currentScope, it.returnType, it.params);
        if (currentScope.parentScope instanceof classScope &&
                it.funcName.equals(((classScope) currentScope.parentScope).ClassType.typeName))
            ((funcScope) currentScope).isConstructor = true;
        for (StmtNode x : it.stmts) {
            if (x != null) x.accept(this);
        }
        currentScope = currentScope.parentScope;
    }
    public void visit(ParameterListNode it){
        if (it.varList != null)
            for (VarDefUnitNode x : it.varList)
                x.accept(this);
    }
    public void visit(VarDefNode it){
        /*
        if (it.units != null) {
            for (VarDefUnitNode x : it.units) x.accept(this);
        }
        if (!GlobalScope.haveType(it.getTypeName()))
            throw new semanticError("Undefined TypeName", it.pos);
        */
        for (VarDefUnitNode x : it.units) {
            VarDefUnitNode t = currentScope.getVar(x.varName);
            if (t != null) throw new semanticError("Variable Exists", it.pos);
            //if (x.init != null) if (it.type != t.exprs.type) throw new semanticError("Wrong InitValue Type", it.pos);
        }
    }
    public void visit(VarDefUnitNode it){}
    public void visit(TypeNode it){}

    //public void visit(StmtNode it){}
    public void visit(BreakNode it){}
    public void visit(ContinueNode it){}
    public void visit(ExprStmtNode it){
        if (it.exprNode != null) it.exprNode.accept(this);
    }
    public void visit(ForStmtNode it){
        currentScope = new Scope(currentScope);
        //if (it.varL != null) it.var.accept(this);
        if (it.init != null) it.init.accept(this);
        if (it.condition != null) {
            it.condition.accept(this);
            if (it.condition.type != BoolType /*|| arrayType*/ )
                throw new semanticError("Incorrext Condition",it.pos);
        }
        if (it.step != null) it.step.accept(this);
        if (it.stmts != null)
            for (StmtNode x : it.stmts) x.accept(this);
        currentScope = currentScope.parentScope;
    }
    public void visit(IfStmtNode it){
        it.condition.accept(this);
        if (it.condition.funcDef == null) {
            if (it.condition.type != BoolType) throw new semanticError("Not Condition Stmt", it.pos);
        } else {
            if (!it.condition.funcDef.returnType.type.typeName.equals("bool")) throw new semanticError("Not Condition Stmt", it.pos);
        }
        if (it.thenStmts != null) {
            currentScope = new blockScope(currentScope);
            for (StmtNode x : it.thenStmts) x.accept(this);
            currentScope = currentScope.parentScope;
        }
        if (it.elseStmts != null) {
            currentScope = new blockScope(currentScope);
            for (StmtNode x : it.elseStmts) x.accept(this);
            currentScope = currentScope.parentScope;
        }
    }
    public void visit(ReturnStmtNode it){
        Scope fScope = currentScope;
        if (it.expr == null) {
            if (!((funcScope) fScope).returnType.type.typeName.equals("void"))
                throw new semanticError("ReturnType not Match", it.pos);
            return;
        }
        it.expr.accept(this);
        if (fScope == null) throw new syntaxError("Only Return in Functions", it.pos);
        if (fScope instanceof funcScope) {
            if (((funcScope) fScope).isConstructor) { //构造函数
                if (it.expr.type != ((funcScope) fScope).returnType.type) throw new semanticError("Constructor Has Incorrect Return", it.pos);
            } else { //普通函数
                if (it.expr.type == null) {
                    TypeNode funcType = ((funcScope) fScope).returnType;
                    if (/*!funcType.isArray &&*/ (funcType.type == IntType || funcType.type == BoolType))
                        throw new semanticError("ReturnType not Match", it.pos);
                } else if (!(it.expr.type == ((funcScope) fScope).returnType.type)) {
                    throw new semanticError("ReturnType not Match", it.pos);
                }
            }
        } else {
            if (((lambdaScope) fScope).returnType == null)
                ((lambdaScope) fScope).returnType = it.expr.type;
            else if (!(((lambdaScope) fScope).returnType.equals(it.expr.type)) && it.expr.type != NullType)
                throw new semanticError("ReturnType Not Match", it.pos);
            else if (it.expr.type == NullType) {
                TypeNode funcType = new TypeNode(it.pos, ((lambdaScope) fScope).returnType.typeName);
                if (/*!funcType.isArray &&*/ (funcType.type == IntType || funcType.type == BoolType))
                    throw new semanticError("returnType not match", it.pos);
            }
        }
    }
    public void visit(SuiteNode it){
        if (it.stmts != null)
            it.stmts.forEach(x->x.accept(this));
    }
    public void visit(WhileStmtNode it){
        it.condition.accept(this);
        if (it.condition.type != BoolType) throw new semanticError("Not Condition Stmt", it.pos);
        if (it.stmts != null) {
            currentScope = new loopScope(currentScope);
            for (StmtNode x : it.stmts) x.accept(this);
            currentScope = currentScope.parentScope;
        }
    }

    //public void visit(ExprNode it){}
    public void visit(ArrayExprNode it){
        it.arrayName.accept(this);
        it.index.accept(this);
        if (it.index.type != IntType) throw new semanticError("Expression in [] can only be IntType", it.pos);
        it.type = it.arrayName.type;
        ExprNode dim = it.arrayName;
        int dimNum = 1;
        while (dim instanceof ArrayExprNode) {
            if (((ArrayExprNode) dim).index.type != IntType) throw new semanticError("Expression in [] can only be IntType", it.pos);
            dim = ((ArrayExprNode) dim).arrayName;
            dimNum++;
        }
        if (dimNum != it.type.dim) throw new semanticError("Dimansion not Matches", it.pos);
    }
    public void visit(AssignExprNode it){
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.isAssignable()) throw new semanticError("Not Assignable", it.pos);
        Type lType = it.lhs.type;
        Type rType = it.rhs.type;
        if (rType != null && lType != rType) throw new semanticError("Different Type", it.pos);
        if (rType == null && !it.lhs.type.isArray &&(lType == BoolType || lType == IntType || lType == VoidType || lType == NullType))
            throw new semanticError("Different Type", it.pos);
        it.type = it.lhs.type;
    }
    public void visit(AtomExprNode it) {
        String typeName = it.type.typeName;
        if (typeName.equals("int") || typeName.equals("bool") || typeName.equals("string") || typeName.equals("null") || typeName.equals("this"))
            return;
        Scope s = new Scope(currentScope);
        while (s != GlobalScope) {
            s = s.parentScope;
            if (s.getVar(it.str) != null) {
                it.type.typeName = s.getVar(it.str).type.type.typeName;
                return;
            }
            if (s.getFunc(it.str) != null) {
                it.type.typeName = s.getFunc(it.str).returnType.type.typeName;
                return;
            }
        }
        throw new semanticError("Undefined Identify", it.pos);
    }
    public void visit(BinaryExprNode it){
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type == null || it.rhs.type == null)
            throw new semanticError("invalid expression", it.pos);

        if (NullType.equals(it.lhs.type) || NullType.equals(it.rhs.type)) {
            // maybe an object compare with null
            if ((it.op.equals("==") || it.op.equals("!="))
                    && (it.lhs.type.isReferenceType() || it.rhs.type.isReferenceType())) {
                it.type = BoolType;
                return;
            } else if (!it.lhs.type.equals(it.rhs.type)) {
                throw new semanticError("invalid expression", it.pos);
            }
        }
        if (VoidType.equals(it.lhs.type) || VoidType.equals(it.rhs.type))
            throw new semanticError("invalid expression", it.pos);
        if (!it.lhs.type.equals(it.rhs.type))
            throw new semanticError("Type mismatch", it.pos);
        switch (it.op) {
            case "+": case "<=": case ">=": case "<": case ">":
                if (!it.lhs.type.equals(IntType) && !it.lhs.type.equals(StringType))
                    throw new semanticError("Type mismatch", it.pos);
                it.type = it.op.equals("+") ? it.lhs.type : BoolType;
                break;
            case "*": case "/": case "%": case "-": case ">>":
            case "<<": case "&": case "^": case "|":
                if (!it.lhs.type.equals(IntType))
                    throw new semanticError("Type mismatch", it.pos);
                it.type = IntType;
                break;
            case "&&": case "||":
                if (!it.lhs.type.equals(BoolType))
                    throw new semanticError("Type mismatch", it.pos);
                it.type = BoolType;
                break;
            default:
                it.type = BoolType;
        }
    
    }
    public void visit(ExprListNode it){
        if (it.exprs != null) {
            for (ExprNode x : it.exprs)
                x.accept(this);
        }
    }
    public void visit(FuncExprNode it){
        it.funcName.accept(this);
        it.funcDef = GlobalScope.get_func(it.funcName.str);
        it.type = it.funcDef.returnType.type;
        if (!(it.funcName instanceof AtomExprNode) && !(it.funcName instanceof MemberExprNode))
            throw new semanticError("Incorrect Function Name",it.pos);
        if (it.funcName instanceof  AtomExprNode) {
            String typeName = ((AtomExprNode) it.funcName).str;
            if (typeName.equals("true") || typeName.equals("false") || typeName.equals("int") || typeName.equals("string") || typeName.equals("null"))
                throw new semanticError("Incorrect Function Name",it.pos);
            it.funcName.type = new Type(typeName);
            FuncDefNode func = currentScope.getFunc(typeName);
            if (func == null) throw new semanticError("Undefined Function", it.pos);
        }
        if (it.lists != null) it.lists.accept(this);
    }
    public void visit(LambdaExprNode it){
        currentScope = new lambdaScope(currentScope, it.isGlobe);
        if (it.params != null) it.params.accept(this);
        if (it.lists != null) it.lists.accept(this);
        if (it.params != null && it.lists != null) {
            ArrayList<VarDefUnitNode> params = it.params.varList;
            ArrayList<ExprNode> exprs = it.lists.exprs;
            if (exprs.size() != params.size())
                throw new semanticError("ParameterList Not Match", it.pos);
            for (int i = 0; i < exprs.size(); i++) {
                if (!(params.get(i).type.type == exprs.get(i).type))
                    throw new semanticError("ParameterList Not Match", it.pos);
            }
            for (StmtNode x : it.stmts) x.accept(this);
        } else if (it.lists == null && it.params == null) {
            for (StmtNode x : it.stmts) x.accept(this);
        } else throw new semanticError("ParameterList Not Match", it.pos);
        if (((lambdaScope) currentScope).returnType == null) {
            it.type = GlobalScope.getType("void", it.pos);
        } else {
            it.type = ((lambdaScope) currentScope).returnType;
        }
        currentScope = currentScope.parentScope;
    }
    public void visit(MemberExprNode it){
        it.name.accept(this);
        //it.member.accept(this);
        Type classType = it.name.type;
        //Type funcType = it.expr2.type.type;
        if (!GlobalScope.haveType(classType.typeName)) throw new syntaxError("Undefined Class", it.pos);
        ClassDefNode classDecl = classType.classDecl;
        if (!classDecl.have_func(it.member))
            throw new syntaxError("Undefined Function In the Class", it.pos);
    }
    public void visit(NewExprNode it){
        int dim = 0;
        if (it.sizeList != null) {
            for (ExprNode x : it.sizeList) {
                x.accept(this);
                dim++;
            }
        }
    }
    public void visit(PreAddExprNode it){}
    public void visit(UnaryExprNode it){}
}