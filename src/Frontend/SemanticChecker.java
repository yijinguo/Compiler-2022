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

    final private Type BoolType, IntType, VoidType, StringType, NullType;

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
        it.mainFn.stmts = mainFunc.stmts;
        it.mainFn.pos = mainFunc.pos;
    }

    public void visit(MainFnNode it) {}

    public void visit(ClassDefNode it){
        currentScope = new classScope(currentScope, it.name);
        ((classScope) currentScope).create_class(it);
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
        ((funcScope) currentScope).returnType = new TypeNode(it.pos, it.name);
        ((funcScope) currentScope).isConstructor = true;
        ((funcScope) currentScope).builder = true;
        it.suites.accept(this);
        currentScope = currentScope.parentScope;
    }
    public void visit(FuncDefNode it){
        it.returnType.accept(this);
        currentScope = new funcScope(currentScope);
        ((funcScope) currentScope).returnType = it.returnType;
        if (it.params != null)
            it.params.accept(this);
        for (int i = 0; i < it.stmts.size(); ++i) {
            (it.stmts.get(i)).accept(this);
        }
        //关于无返回值的问题
        if (!it.funcName.equals("main") && !it.returnType.type.equals(VoidType) && !currentScope.hasReturn)
            throw new semanticError("Lack Return", it.pos);
        currentScope = currentScope.parentScope;
        /*
        if (currentScope instanceof classScope) {
            ((classScope) currentScope).add_func(it.funcName, it);
        }*/
    }
    public void visit(ParameterListNode it){
        it.varList.forEach(x->x.accept(this));
    }
    public void visit(VarDefNode it){
        it.units.forEach(x-> x.accept(this));
    }
    public void visit(VarDefUnitNode it){
        it.type.accept(this);
        if (!(currentScope instanceof classScope) && currentScope.have_var(it.varName))
            throw new semanticError("Redefined of variable", it.pos);
        if (it.init != null) {
            it.init.accept(this);
            if (it.init instanceof LambdaExprNode) {
                if (!it.init.type.equals(it.type.type))
                    throw new semanticError("Unmatched Type", it.pos);
            }
        }
        if (it.type.type.isArray) {
            if (it.init != null && !it.init.type.equals(NullType)){
                if (it.type.type.dim != it.init.type.dim)
                    throw new semanticError("Unmatched Dimension", it.pos);
            }
        }
        if (!(currentScope instanceof classScope)) currentScope.add_var(it);
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
                if (GlobalScope.classList.containsKey(it.type.typeName))
                    it.type.isClass = true;
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
    public void visit(ExprStmtNode it) {
        if (it.exprNode != null) it.exprNode.accept(this);
    }
    public void visit(ForStmtNode it){
        if (it.varDef != null) it.varDef.accept(this);
        else if (it.init != null) it.init.accept(this);
        if (it.condition != null) {
            it.condition.accept(this);
            if (!it.condition.type.equals(BoolType)) throw new syntaxError("Invalid Condition", it.pos);
        }
        if (it.step != null) it.step.accept(this);
        currentScope = new loopScope(currentScope);
        if (it.stmts != null) it.stmts.forEach(x->x.accept(this));
        currentScope = currentScope.parentScope;
    }
    public void visit(IfStmtNode it){
        if (it.condition == null) throw new syntaxError("Lack Condition", it.pos);
        it.condition.accept(this);
        if (!it.condition.type.equals(BoolType))
            throw new semanticError("Invalid Condition", it.pos);
        //没有考虑if和else的大括号省略问题
        if (it.thenStmts.size() != 0) {
            currentScope = new loopScope(currentScope);
            it.thenStmts.forEach(x -> x.accept(this));
            currentScope = currentScope.parentScope;
        }
        if (it.elseStmts.size() != 0) {
            currentScope = new loopScope(currentScope);
            it.elseStmts.forEach(x->x.accept(this));
            currentScope = currentScope.parentScope;
        }
    }
    public void visit(ReturnStmtNode it){
        if (it.expr != null) it.expr.accept(this);
        Scope s = new Scope(currentScope);
        while (s != GlobalScope) {
            s = s.parentScope;
            if (s instanceof funcScope) break;
        }
        if (!(s instanceof funcScope))
            throw new syntaxError("Invalid Return", it.pos);
        if (((funcScope) s).builder) {
            if (it.expr != null) throw new semanticError("cannot return any value in a constructor", it.pos);
            currentScope.put_return();
            return;
        }
        if (it.expr == null) {
            if (((funcScope) s).returnType.type.equals(VoidType)) {
                currentScope.put_return();
                return;
            } else throw new syntaxError("Lack Return", it.pos);
        }
        if (!it.expr.type.equals(((funcScope) s).returnType.type)) {
            if (it.expr.type.typeName.equals("this")) {
                Type classType = currentScope.catch_class();
                if (classType != null) {
                    it.expr.type = classType;
                } else {
                    throw new semanticError("Invalid Use of This", it.pos);
                }
            } else {
                if (!(((funcScope) s).returnType.type.isClass && it.expr.type.equals(NullType)))
                    throw new semanticError("Unmatched ReturnType", it.pos);
            }
        }
        currentScope.put_return();
    }
    public void visit(SuiteNode it){
        if ((currentScope instanceof funcScope) && ((funcScope) currentScope).builder) {
            currentScope = new funcScope(currentScope);
            ((funcScope) currentScope).returnType = ((funcScope)currentScope.parentScope).returnType;
            ((funcScope) currentScope).builder = true;
        } else {
            currentScope = new Scope(currentScope);
        }
        if (it.stmts != null)
            it.stmts.forEach(x -> x.accept(this));
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
    public void visit(ArrayExprNode it) {
        it.index.accept(this);
        it.type = new Type();
        if (!(it.index.type.equals(IntType)) || it.index.type.dim != 0)
            throw new semanticError("Invalid Index", it.pos);
        it.arrayName.accept(this);
        it.type.typeName = it.arrayName.type.typeName;
        if (it.arrayName instanceof ArrayExprNode) {
            it.dim = ((ArrayExprNode) it.arrayName).dim - 1;
        } else {
            if (!it.arrayName.type.isArray)
                throw new semanticError("Not An Array", it.pos);
            it.dim = it.arrayName.type.dim - 1;
        }
        it.type.dim = it.dim;
        it.str = it.arrayName.str;
        if (it.dim > 0) it.type.isArray = true;
        else if (it.dim == 0) it.type.isArray = false;
        else throw new semanticError("Out of ArrayDimension", it.pos);
    }
    public void visit(AssignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.isAssignable())
            throw new syntaxError("It is Not Assignable", it.pos);
        if (!it.rhs.type.equals(NullType)) {
            Type lType = it.lhs.type;
            Type rType = it.rhs.type;
            if (!lType.isArray && !rType.isArray) {
                if (!lType.equals(rType))
                    throw new syntaxError("Unmatched AssignType", it.pos);
            } else {
                if (lType.isArray != rType.isArray)
                    throw new syntaxError("Unmatched AssignType", it.pos);
                if (lType.dim != rType.dim)
                    throw new syntaxError("Unmatched AssignType", it.pos);
            }
        } else {
            if (!it.lhs.type.isArray && !it.lhs.type.isClass) {
                throw new syntaxError("Unmatched AssignType", it.pos);
            }
        }
    }
    public void visit(AtomExprNode it) {
        /*if (it.type == null) {*/
        if (!it.str.equals("this")) {
            VarDefUnitNode var = currentScope.getVar(it.str);
            FuncDefNode func = currentScope.getFunc(it.str);
            ClassDefNode classDef = GlobalScope.getClass(it.str, it.pos);
            if (var == null && func == null && classDef == null) {
                if (it.type == null)
                    throw new semanticError("Undefined AtomExpr", it.pos);
                it.type.isConst = true;
            }
            if (var != null) {
                it.type = var.type.type;
                it.str = var.varName;
            } else if (func != null) {
                it.type = func.returnType.type;
                it.str = func.funcName;
                it.funcDef = func;
            } else if (classDef != null) {
                it.type = new Type();
                it.str = classDef.name;
                it.type.isClass = true;
            }
        } else {
            Type type = currentScope.catch_class();
            if (type == null) {
                throw new semanticError("Invalid This", it.pos);
            } else {
                it.type = type;
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
            if (lType.isArray && !rType.equals(NullType) || rType.isArray && lType.equals(NullType))
                throw new syntaxError("Invalid Type", it.pos);
            it.type = BoolType;
            return;
        }
        if (it.op.equals("==") || it.op.equals("!=")) {
            if (!lType.equals(rType)) {
                if (rType.equals(NullType) && lType.isClass) {
                    it.type = BoolType;
                    return;
                }
                throw new semanticError("Unmatched Type", it.pos);
            }
            if (!lType.equals(BoolType) && !lType.equals(IntType) && !lType.equals(StringType) && !lType.equals(NullType)) {
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
        it.str = it.funcName.str;
        if (it.lists != null) it.lists.accept(this);
        FuncDefNode t = new FuncDefNode(it.pos);
        if (it.funcName instanceof MemberExprNode){
            t = it.funcName.funcDef;
        } else {
            t = currentScope.getFunc(it.funcName.str);
            if (t == null)
                throw new syntaxError("Undefined Function", it.pos);
        }
        //syntaxError paramsError = new syntaxError("Unmatched ParameterList", it.pos);
        if (it.lists == null && t.params != null) {
            throw new syntaxError("Unmatched ParameterList", it.pos);
        }
        if (it.lists != null) {
            if (t.params == null || t.params.varList.size() != it.lists.exprs.size())
                throw new syntaxError("Unmatched ParameterList", it.pos);
            for (int i = 0; i < it.lists.exprs.size(); ++i) {
                //type均未写明，此处应该调用一次AtomExpr的accept,给type赋值
                it.lists.exprs.get(i).accept(this);
                if (!it.lists.exprs.get(i).type.equals(NullType) &&
                        !it.lists.exprs.get(i).type.equals(t.params.varList.get(i).type.type))
                    throw new syntaxError("Unmatched ParameterList", it.pos);
            }
        }
        it.type = t.returnType.type;
    }
    public void visit(LambdaExprNode it){
        if (it.params != null)
            it.params.accept(this);
        Scope tmp = currentScope;
        if (!it.isGlobe) {
            currentScope = new globalScope(null);
            ((globalScope) currentScope).create_lambda();
            if (it.params != null) {
                for (VarDefUnitNode y : it.params.varList)
                    currentScope.add_var(y);
            }
        } else {
            currentScope = new Scope(currentScope);
        }
        boolean flag = false;
        for (StmtNode x : it.stmts) {
            x.accept(this);
            if (x instanceof ReturnStmtNode) {
                it.type = ((ReturnStmtNode) x).expr.type;
                flag = true;
            }
        }
        if (!flag)
            it.type = VoidType;
        if (it.lists != null)
            it.lists.accept(this);
        if (it.params != null) {
            if (it.lists == null || it.params.varList.size() != it.lists.exprs.size())
                throw new semanticError("Unmatched parameterList", it.pos);
            for (int i = 0; i < it.params.varList.size(); ++i) {
                if (!it.params.varList.get(i).type.type.equals(it.lists.exprs.get(i).type))
                    throw new semanticError("Unmatched parameterList", it.pos);
            }
        }
        currentScope = tmp;
    }
    public void visit(MemberExprNode it){
        it.str = it.member;
        it.type = new Type();
        it.name.accept(this);
        if (it.name.type.equals(StringType)) {
            it.type = StringType;
            ClassDefNode stringClass = GlobalScope.getClass("string", it.pos);
            it.funcDef = stringClass.get_func(it.member);
        } else if (it.name.type.isArray) {
            //todo
            if (!it.member.equals("size"))
                throw new semanticError("Undefined Function", it.pos);
            it.type = IntType;
            it.funcDef = GlobalScope.getFunc("size");
        } else if (it.name.str != null || it.name instanceof NewExprNode) {
            if (it.name.type.equals(StringType)) {
                it.name.str = "string";
            } else if (it.name.type.typeName.equals("this")) {
                Scope s = new Scope(currentScope);
                while (s != GlobalScope) {
                    if (s instanceof classScope) break;
                    s = s.parentScope;
                }
                if (!(s instanceof classScope))
                    throw new semanticError("Not in Class for This", it.pos);
                if (!s.have_var(it.member) && !s.have_func(it.member))
                    throw new semanticError("Undefined member", it.pos);
                if (s.getFunc(it.member) != null) {
                    it.funcDef = s.getFunc(it.member);
                    it.type = it.funcDef.returnType.type;
                } else {
                    it.type = s.getVar(it.member).type.type;
                }
            } else {
                if (GlobalScope.getClass(it.name.type.typeName, it.pos) == null)
                    throw new syntaxError("Undefined Class", it.pos);
                ClassDefNode classNode = GlobalScope.getClass(it.name.type.typeName, it.pos);
                if (!classNode.have_var(it.member) && !classNode.have_func(it.member))
                    throw new syntaxError("Undefined Class Member", it.pos);
                ClassDefNode classDef = GlobalScope.getClass(it.name.type.typeName,it.pos);
                if (classDef.get_func(it.str) != null) {
                    it.funcDef = classDef.get_func(it.str);
                    it.type = it.funcDef.returnType.type;
                } else {
                    it.type = classDef.get_var(it.str).type.type;
                }
            }
            it.type.isClass = true;
        }
    }
    public void visit(NewExprNode it){
        if (!GlobalScope.haveType(it.typeName))
            throw new semanticError("Undefined Type", it.pos);
        for (int i = it.sizeList.size() - 1; i >= 0; --i) {
            it.sizeList.get(i).accept(this);
            if (!it.sizeList.get(i).type.equals(IntType))
                throw new syntaxError("Invalid New Expression", it.pos);
            if (i != 0)
                if (it.sizeList.get(i) != null && it.sizeList.get(i - 1) == null)
                    throw new syntaxError("Invalid New Expression", it.pos);
        }
        it.type = GlobalScope.getType(it.typeName, null);
        it.type.dim = it.dim;
        if (it.dim > 0) it.type.isArray = true;
    }
    public void visit(PreAddExprNode it){
        it.expr.accept(this);
        if (!it.expr.type.equals(IntType)) throw new semanticError("Not a Variable", it.pos);
        if (!it.expr.isAssignable()) throw new semanticError("Not Assignable", it.pos);
        it.type = IntType;
    }
    public void visit(UnaryExprNode it){
        it.expr.accept(this);
        //maybe need some change
        if (it.op.equals("++") || it.op.equals("--"))
            if (!it.expr.isAssignable())
                throw new syntaxError("LeftValue is expected", it.pos);
        if (!it.expr.type.equals(IntType) && !it.expr.type.equals(BoolType))
            throw new semanticError("Invalid Variable Type", it.pos);
        it.type = it.expr.type;
    }
 
}