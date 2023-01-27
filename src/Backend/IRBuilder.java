package Backend;

import AST.*;
import AST.expr.*;
import AST.stmt.*;
import MIR.terminalStmt.*;
import Util.Scope.*;

import MIR.*;
import MIR.Statmemt.*;
import Util.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class IRBuilder implements ASTVisitor {

    //对每个函数开一个rootBlock。
    //对每一个类开一个结点。
    private block currentBlock = null;
    private function currentFunc = null;
    private classVar currentClass = null;
    public HashMap<String, function> functions = new HashMap<>();
    public HashMap<String, classVar> classLists =  new HashMap<>();
    public globalScope gScope;

    public IRBuilder(/*mainFn mainfn, */globalScope gScope){
        this.gScope = gScope;
    }

    public void visit(RootNode it){
        //todo
        //pay attention to inline functions
        for (ASTNode x : it.DefList) {
            x.accept(this);
        }
    }
    public void visit(MainFnNode it){
        it.stmts.forEach(s -> s.accept(this));
    }
    public void visit(ClassDefNode it){
        currentClass = new classVar(it.name);
        if (it.classBuilder != null) it.classBuilder.accept(this);
        it.varList.forEach(x->x.accept(this));
        it.funcList.forEach(x->x.accept(this));
        classLists.put(currentClass.className, currentClass);
        currentClass = null;
    }
    public void visit(ClassBuildNode it){
        currentFunc = new function("void", it.name);
        currentFunc.isInClass = true;
        currentFunc.className = currentClass.className;
        it.suites.accept(this);
        currentFunc = null;
    }
    public void visit(FuncDefNode it){
        currentFunc = new function(it.returnType.type.typeName, it.funcName);
        currentBlock = currentFunc.rootBlock;
        if (it.params != null) it.params.accept(this);
        it.stmts.forEach(x -> x.accept(this));
        if (currentClass == null) {
            currentFunc.isInClass = false;
            functions.put(currentFunc.funcName, currentFunc);
        } else {
            currentFunc.isInClass = true;
            currentFunc.className = currentClass.className;
            currentClass.functions.put(currentFunc.funcName, currentFunc);
        }
        currentFunc = null;
    }
    public void visit(ParameterListNode it){
        for (VarDefUnitNode x : it.varList) {
            register reg_x = new register(x.varName, x.type.type);
            currentFunc.paraList.put(x.varName, reg_x);
            currentFunc.push_entity(++currentFunc.reg_num, x.varName, reg_x);
        }
    }
    public void visit(VarDefNode it){
        it.units.forEach(x->x.accept(this));
    }
    public void visit(VarDefUnitNode it){
        varDef varDefUnit;
        register var = new register(it.varName, it.type.type);
        if (currentClass != null) {
            var.isInClass = true;
            var.className = currentClass.className;
        }
        if (it.type.type.dim > 0) { //数组的创建
            var.irType.isArray = true;
            var.irType.dim = it.type.type.dim;
            if (it.init instanceof NewExprNode) {
                it.init.accept(this);
                for (int i = 0; i < var.irType.dim; i = i + 1) {
                    if (i < ((NewExprNode) it.init).sizeList.size()) {
                        var.irType.each_num.add(((NewExprNode) it.init).sizeList.get(i).val);
                    } else {
                        var.irType.each_num.add(null);
                    }
                }
            }
        }
        if (it.init != null) {
            it.init.accept(this);
            varDefUnit = new varDef(var, it.init.val);
        } else {
            varDefUnit = new varDef(var);
        }
        if (currentBlock != null) {
            currentFunc.push_entity(++currentFunc.reg_num, it.varName, var);
            currentBlock.push_back(varDefUnit);
        } else if (currentClass != null) {
            currentClass.entities.put(it.varName, var);
        } else {
            gScope.entities.put(it.varName, var);
        }
    }
    public void visit(TypeNode it){}

    //public void visit(StmtNode it){}
    public void visit(BreakNode it){
        block tmp = currentBlock;
        while (!(tmp.tailStmt instanceof whileLoop || tmp.tailStmt instanceof forLoop)){
            tmp = tmp.parentBlock;
        }
        currentBlock.push_back(tmp.tailStmt);
    }
    public void visit(ContinueNode it){
        block tmp = currentBlock;
        while (!(tmp.tailStmt instanceof whileLoop || tmp.tailStmt instanceof forLoop)){
            tmp = tmp.parentBlock;
        }
        currentBlock.push_back(new jump(tmp));
    }
    public void visit(ExprStmtNode it){
        it.exprNode.accept(this);
    }
    public void visit(ForStmtNode it){
        block start = new block(++currentFunc.reg_num, currentBlock), forBlock = new block(++currentFunc.reg_num, currentBlock);
        currentBlock.push_back(new forLoop(start, forBlock));
        currentBlock = start;
        if (it.varDef != null) it.varDef.accept(this);
        else if (it.init != null) it.init.accept(this);
        if (it.condition != null) it.condition.accept(this);
        if (it.step != null) it.step.accept(this);

        currentBlock = forBlock;
        block destination;
        if (currentBlock.tailStmt instanceof jump) {
            destination = ((jump) currentBlock.tailStmt).destination;
        } else {
            destination = new block(++currentFunc.reg_num, currentBlock);
            currentBlock.push_back(new jump(destination));
        }
        for (StmtNode x : it.stmts) {
            x.accept(this);
        }
        currentBlock = destination;
        currentFunc.blocks.add(start);
        currentFunc.blocks.add(forBlock);
        currentFunc.blocks.add(destination);
    }
    public void visit(IfStmtNode it){
        it.condition.accept(this);
        block trueBranch = new block(++currentFunc.reg_num, currentBlock), falseBranch = new block(++currentFunc.reg_num, currentBlock);
        currentBlock.push_back(new branch(it.condition.val,trueBranch,falseBranch));

        block destination = new block(++currentFunc.reg_num, currentBlock);
        currentBlock = trueBranch;
        for (StmtNode x : it.thenStmts) x.accept(this);
        if (currentBlock.tailStmt == null) currentBlock.push_back(new jump(destination));
        if (it.elseStmts != null) {
            currentBlock = falseBranch;
            for (StmtNode x : it.elseStmts) {
                x.accept(this);
            }
            if (currentBlock.tailStmt == null) currentBlock.push_back(new jump(destination));
        }
        currentBlock = destination;

        currentFunc.blocks.add(trueBranch);
        currentFunc.blocks.add(falseBranch);
        currentFunc.blocks.add(destination);
    }
    public void visit(ReturnStmtNode it){
        entity value;
        if (it.expr != null) {
            it.expr.accept(this);
            value = it.expr.val;
        } else value = null;
        currentBlock.push_back(new ret(value));
        currentBlock = null;
    }
    public void visit(SuiteNode it){
        for (StmtNode x : it.stmts)
            x.accept(this);
    }
    public void visit(WhileStmtNode it){
        block condition = new block(++currentFunc.reg_num, currentBlock), whileBlock = new block(++currentFunc.reg_num, currentBlock);
        currentBlock.push_back(new whileLoop(condition, whileBlock));
        currentBlock = condition;
        if (it.condition != null) it.condition.accept(this);
        currentBlock.push_back(new jump(whileBlock));
        currentBlock = whileBlock;
        block destination;
        if (currentBlock.tailStmt instanceof jump) {
            destination = ((jump) currentBlock.tailStmt).destination;
        } else {
            destination = new block(++currentFunc.reg_num, currentBlock);
            currentBlock.push_back(new jump(destination));
        }
        for (StmtNode x : it.stmts) {
            x.accept(this);
        }
        currentBlock = destination;
        currentFunc.blocks.add(condition);
        currentFunc.blocks.add(whileBlock);
        currentFunc.blocks.add(destination);
    }

    //public void visit(ExprNode it){}
    public void visit(ArrayExprNode it){
        it.arrayName.accept(this);
        it.index.accept(this);
        it.val = it.arrayName.val;
        it.val.new_reg_num = ++currentFunc.reg_num;
        if (!(it.arrayName instanceof ArrayExprNode)) it.val.new_reg_num++;
        ((register)it.val).dim++;
        ((register)it.val).each_num.add(it.index.val);
    }
    public void visit(AssignExprNode it){
        it.lhs.accept(this);
        it.rhs.accept(this);
        currentBlock.push_back(new assign(it.lhs.val, it.rhs.val));
    }
    public void visit(AtomExprNode it){
        if (!it.str.equals("this")) {
             if (it.type.isConst) { //处理常数
                it.val = new constant();
                switch (it.type.typeName) {
                    case "int" -> {
                        it.val.irType.irType = type.IRType.INT;
                        it.val.irType.int_value = Integer.parseInt(it.str);
                    }
                    case "bool" -> {
                        it.val.irType.irType = type.IRType.BOOL;
                        it.val.irType.boolean_value = it.str.equals("true");
                    }
                    case "string" -> {
                        it.val.irType.irType = type.IRType.STRING;
                        it.val.irType.string_value = it.str;
                        it.val.new_reg_num = ++currentFunc.reg_num;
                        gScope.entities.put(it.val.irType.string_value, it.val);
                    }
                    case "null" -> it.val.irType.irType = type.IRType.NULL;
                    default -> {

                    }
                }
            } else {
                 if (currentBlock != null)
                     it.val = currentFunc.getEntity(it.str);
                 if (it.val == null) {
                     if (currentClass != null)
                         it.val = currentClass.getEntity(it.str);
                     if (it.val == null)
                         it.val = gScope.getEntity(it.str);
                 }
                 it.val.irType.dim = it.type.dim;
                 it.val.new_reg_num = ++currentFunc.reg_num;
             }
        } else {
            it.val = new register("this", it.type);
            it.val.new_reg_num = ++currentFunc.reg_num;
        }
    }
    public void visit(BinaryExprNode it){
        it.lhs.accept(this);
        currentFunc.reg_num++;
        it.rhs.accept(this);
        currentFunc.reg_num++;
        register value;
        if (it.val != null) {
            value = (register)it.val;
        } else {
            if (it.op.equals("&&") || it.op.equals("||")) {
                currentFunc.reg_num += 2;
            }
            value = new register(it.lhs.val.irType);
            currentFunc.reg_num += 1;
            value.new_reg_num = ++currentFunc.reg_num;
            it.val = value;
        }
        currentBlock.push_back(new binary(value, it.lhs.val, it.rhs.val, it.op));
    }
    public void visit(ExprListNode it){
        for (ExprNode x : it.exprs) {
            x.accept(this);
        }
    }
    public void visit(FuncExprNode it){
        call callFunc;
        if (it.funcName instanceof MemberExprNode) {
            callFunc = new call(new type(it.type), ((MemberExprNode)it.funcName).member, ((MemberExprNode)it.funcName).name.str);
        } else {
            callFunc = new call(new type(it.type), it.funcName.str);
        }
        for (ExprNode x : it.lists.exprs) {
            x.accept(this);
            callFunc.paramList.add(x.val);
        }
        callFunc.returnReg = new register(it.type);
        callFunc.returnReg.new_reg_num = ++currentFunc.reg_num;
        currentBlock.push_back(callFunc);
        it.val = callFunc.returnReg;
    }
    public void visit(LambdaExprNode it){}
    public void visit(MemberExprNode it){ //函数调用不跑这个函数，只涉及变量调用
        it.name.accept(this);
        if (it.name.str.equals("this")) {
            it.val = currentClass.getEntity(it.member);
        } else {
            it.val = classLists.get(it.name.str).getEntity(it.member);
        }
    }
    public void visit(NewExprNode it){
        it.val = new register(it.type);
        it.val.new_reg_num = ++currentFunc.reg_num;
        ((register)it.val).isArrayNew = true;
        it.val.irType.dim = it.dim;
        for (ExprNode x : it.sizeList){
            x.accept(this);
            it.val.irType.each_num.add(x.val);
        }
    }
    public void visit(PreAddExprNode it){
        it.expr.accept(this);
        register value;
        if (it.val != null) {
            value = (register) it.val;
        } else {
            value = (register) it.expr.val;
            it.val = value;
        }
        register tmp = new register((register) it.expr.val);
        tmp.new_reg_num = ++currentFunc.reg_num;
        currentBlock.push_back(new preAdd(value, it.op, tmp));
    }
    public void visit(UnaryExprNode it){
        it.expr.accept(this);
        register value;
        register tmp = new register((register) it.expr.val);
        tmp.new_reg_num = ++currentFunc.reg_num;
        if (it.op.equals("++") || it.op.equals("--")) {
            value = (register) it.expr.val;
            it.val = value;
        } else {
            value = new register(it.type);
            currentFunc.reg_num += 2;
            value.new_reg_num = ++currentFunc.reg_num;
            it.val = value;
        }
        currentBlock.push_back(new unary(value, tmp, it.op));
    }
}
