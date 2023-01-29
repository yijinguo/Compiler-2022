package Middlend;

import AST.*;
import AST.expr.*;
import AST.stmt.*;
import MIR.entity.constant;
import MIR.entity.entity;
import MIR.entity.register;
import MIR.terminalStmt.*;
import Util.Scope.*;

import MIR.*;
import MIR.Statmemt.*;

import java.util.HashMap;

public class IRBuilder implements ASTVisitor {

    //对每个函数开一个rootBlock。
    //对每一个类开一个结点。
    private block currBlk = null;
    private function currFunc = null;
    private classVar currentClass = null;
    public HashMap<String, function> functions = new HashMap<>();
    public HashMap<String, classVar> classLists =  new HashMap<>();
    public globalScope gScope;
    public int string_num = 0;

    public IRBuilder(/*mainFn mainfn, */globalScope gScope){
        this.gScope = gScope;
    }

    public void visit(RootNode it){
        for (ASTNode x : it.DefList) x.accept(this);
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
        currFunc = new function("void", it.name);
        currFunc.isInClass = true;
        currFunc.className = currentClass.className;
        it.suites.accept(this);
        currFunc = null;
    }
    public void visit(FuncDefNode it){
        currFunc = new function(it.returnType.type.typeName, it.funcName);
        currBlk = currFunc.rootBlock;
        if (it.params != null) it.params.accept(this);
        it.stmts.forEach(x -> x.accept(this));
        if (currentClass == null) {
            currFunc.isInClass = false;
            functions.put(currFunc.funcName, currFunc);
        } else {
            currFunc.isInClass = true;
            currFunc.className = currentClass.className;
            currentClass.functions.put(currFunc.funcName, currFunc);
        }
        currFunc = null;
    }

    public void visit(ParameterListNode it){
        for (VarDefUnitNode x : it.varList) {
            register reg_x = new register(++currFunc.reg_num, x.varName, x.type.type);
            currFunc.paraList.put(x.varName, reg_x);
            currFunc.push_entity(++currFunc.reg_num, x.varName, reg_x);
        }
    }

    public void visit(VarDefNode it){
        it.units.forEach(x->x.accept(this));
    }

    public void visit(VarDefUnitNode it){
        register var = new register(++currFunc.reg_num, it.varName, it.type.type);
        if (var.irType.irType == type.IRType.ClassType) {
            int s = 0;
            for (VarDefNode x : gScope.getClass(it.varName, null).varList) {
                for (VarDefUnitNode y : x.units) {
                    if (y.type.type.typeName.equals("string"))
                        s += 24;
                    else
                        s += 4;
                }
            }
            var.irType.class_size = s;
        }
        if (currentClass != null) {
            var.isInClass = true;
            var.className = currentClass.className;
        }
        if (it.type.type.dim > 0) { //数组的创建
            var.irType.isArray = true;
            var.irType.dim = it.type.type.dim;
            if (it.init instanceof NewExprNode) {
                for (ExprNode x : ((NewExprNode) it.init).sizeList) {
                    x.accept(this);
                    var.irType.each_num.add(x.val);
                }
                var.irType.haveDef = var.irType.each_num.size() == var.irType.dim;
                if (currBlk != null && var.irType.haveDef) {
                    //todo
                    entity tmp = var.irType.each_num.get(0);
                    for (int i = 1; i < var.irType.each_num.size(); i++) {
                        entity e = var.irType.each_num.get(i);
                        register dest = new register(++currFunc.reg_num, e.irType);
                        if (e instanceof register) {
                            register l = new register(++currFunc.reg_num, e.irType);
                            currBlk.push_back(new load(l, e));
                            currBlk.push_back(new binary("mul", tmp, l, dest));
                        } else {
                            currBlk.push_back(new binary("mul", tmp, e, dest));
                        }
                        tmp = dest;
                    }
                    currBlk.push_back(new alloca(new register(++currFunc.reg_num, tmp.irType), tmp));
                }

            }
        } else {
            if (it.init != null) {
                it.init.accept(this);
                if (currBlk != null) {
                    currBlk.push_back(new alloca(var));
                    currBlk.push_back(new store(var, it.init.val));
                } else {
                    switch (var.irType.irType) {
                        case INT -> var.irType.int_value = it.init.val.irType.int_value;
                        case BOOL -> var.irType.boolean_value = it.init.val.irType.boolean_value;
                        case STRING -> var.irType.string_value = it.init.val.irType.string_value;
                        default -> {}
                    }
                }
            }
        }
        if (currBlk != null) {
            currFunc.push_entity(++currFunc.reg_num, it.varName, var);
        } else if (currentClass != null) {
            currentClass.entities.put(it.varName, var);
        } else {
            gScope.entities.put(it.varName, var);
        }
    }
    public void visit(TypeNode it){}

    //public void visit(StmtNode it){}
    public void visit(BreakNode it){
        block tmp = currBlk;
        while (!(tmp.tailStmt instanceof loop)){
            tmp = tmp.parentBlock;
        }
        currBlk.push_back(new jump(((loop)tmp.tailStmt).loopBlk));
    }
    public void visit(ContinueNode it){
        block tmp = currBlk;
        while (!(tmp.tailStmt instanceof loop)){
            tmp = tmp.parentBlock;
        }
        currBlk.push_back(new jump(((loop)tmp.tailStmt).condition));
    }

    public void visit(ExprStmtNode it){
        it.exprNode.accept(this);
    }

    public void visit(ForStmtNode it){
        block start = new block(++currFunc.reg_num, currBlk), forBlock = new block(++currFunc.reg_num, currBlk);
        block destination = new block(++currFunc.reg_num, start);

        currBlk.push_back(new loop(start, forBlock));

        currBlk = start;
        if (it.varDef != null) it.varDef.accept(this);
        else if (it.init != null) it.init.accept(this);
        if (it.step != null) it.step.accept(this);
        it.condition.accept(this);
        if (currBlk.tailStmt == null) currBlk.push_back(new branch(it.condition.val, forBlock, destination));

        currBlk = forBlock;
        for (StmtNode x : it.stmts) x.accept(this);
        if (currBlk.tailStmt == null) currBlk.push_back(new jump(start));

        currBlk = destination;
        currFunc.blocks.add(start);
        currFunc.blocks.add(forBlock);
        currFunc.blocks.add(destination);
    }

    public void visit(IfStmtNode it){
        it.condition.accept(this);
        block trueBranch = new block(++currFunc.reg_num, currBlk), falseBranch = new block(++currFunc.reg_num, currBlk);
        currBlk.push_back(new branch(it.condition.val,trueBranch,falseBranch));

        block destination = new block(++currFunc.reg_num, currBlk);
        currBlk = trueBranch;
        for (StmtNode x : it.thenStmts) x.accept(this);
        if (currBlk.tailStmt == null) currBlk.push_back(new jump(destination));
        if (it.elseStmts != null) {
            currBlk = falseBranch;
            for (StmtNode x : it.elseStmts) x.accept(this);
            if (currBlk.tailStmt == null) currBlk.push_back(new jump(destination));
        }
        currBlk = destination;

        currFunc.blocks.add(trueBranch);
        currFunc.blocks.add(falseBranch);
        currFunc.blocks.add(destination);
    }

    public void visit(ReturnStmtNode it){
        entity value;
        if (it.expr != null) {
            it.expr.accept(this);
            value = it.expr.val;
        } else value = null;
        currBlk.push_back(new ret(value));
        currBlk = null;
    }

    public void visit(SuiteNode it){
        for (StmtNode x : it.stmts) x.accept(this);
    }

    public void visit(WhileStmtNode it){
        block condition = new block(++currFunc.reg_num, currBlk), whileBlock = new block(++currFunc.reg_num, currBlk);
        block destination = new block(++currFunc.reg_num, condition);

        currBlk.push_back(new loop(condition, whileBlock));

        currBlk = condition;
        it.condition.accept(this);
        if (currBlk.tailStmt == null) currBlk.push_back(new branch(it.condition.val, whileBlock, destination));

        currBlk = whileBlock;
        for (StmtNode x : it.stmts) x.accept(this);
        if (currBlk.tailStmt == null) currBlk.push_back(new jump(condition));

        currBlk = destination;
        currFunc.blocks.add(condition);
        currFunc.blocks.add(whileBlock);
        currFunc.blocks.add(destination);
    }

    //public void visit(ExprNode it){}
    public void visit(ArrayExprNode it){
        it.arrayName.accept(this);
        it.index.accept(this);
        register r = new register(++currFunc.reg_num, it.arrayName.type);
        currBlk.push_back(new load(r, it.arrayName.val));
        it.val = new register(++currFunc.reg_num, it.arrayName.type);
        it.val.irType.dim--;
        currBlk.push_back(new getelementptr(it.val, r, it.index.val));

        /*
        if (it.arrayName.val.irType.haveDef) {
            entity sim = it.arrayName.val;
            int total_dim = sim.irType.dim;
            entity tmp = sim.irType.each_num.get(total_dim);
            for (int i = total_dim - 1; i >= total_dim - it.dim; i--) {
                entity dest = new register(++currFunc.reg_num, it.type);
                currBlk.push_back(new binary("mul", sim.irType.each_num.get(i), tmp, dest));
                tmp = dest;
            }
            register tail = new register(++currFunc.reg_num, it.type);
            currBlk.push_back(new binary("mul", it.index.val, tmp, tail));
            it.val = new register(++currFunc.reg_num, it.type);
            currBlk.push_back(new getelementptr(it.val, it.arrayName.val, tail));
        } else {
            //todo
        }
        */
    }

    public void visit(AssignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        register r = new register(++currFunc.reg_num, it.lhs.type);
        currBlk.push_back(new load(r, it.rhs.val));
        currBlk.push_back(new store(it.lhs.val, r));
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
                        gScope.entities.put(it.str, it.val);
                    }
                    case "null" -> it.val.irType.irType = type.IRType.NULL;
                    default -> {

                    }
                }
            } else {
                 if (currBlk != null)
                     it.val = currFunc.getEntity(it.str);
                 if (it.val == null) {
                     if (currentClass != null)
                         it.val = currentClass.getEntity(it.str);
                     if (it.val == null)
                         it.val = gScope.getEntity(it.str);
                 }
             }
        }
        //="this" : do nothing
    }

    public void visit(BinaryExprNode it){
        it.lhs.accept(this);
        it.rhs.accept(this);
        switch (it.op) {
            case "<", ">", "<=", ">=", "==", "!=" -> {
                register l = new register(++currFunc.reg_num, it.lhs.type), r = new register(++currFunc.reg_num, it.rhs.type);
                currBlk.push_back(new load(l, it.lhs.val));
                currBlk.push_back(new load(r, it.rhs.val));
                it.val = new register(++currFunc.reg_num, new type(type.IRType.BOOL));
                currBlk.push_back(new icmp(it.op, l, r, it.val));
            }
            case "&&" -> {
                register l = new register(++currFunc.reg_num, it.lhs.type);
                currBlk.push_back(new load(l, it.lhs.val));
                block rhs = new block(++currFunc.reg_num, currBlk), dest = new block(++currFunc.reg_num, currBlk);
                currBlk.push_back(new branch(l, rhs, dest));

                register r = new register(++currFunc.reg_num, it.rhs.type);
                rhs.push_back(new load(r, it.rhs.val));
                rhs.push_back(new unary("snez", new constant(0), r, l));
                rhs.push_back(new jump(dest));

                it.val = new register(++currFunc.reg_num, new type(type.IRType.BOOL));
                dest.push_back(new store(it.val, l));
                currBlk = dest;
            }
            case "||" -> {
                register l = new register(++currFunc.reg_num, it.lhs.type);
                currBlk.push_back(new load(l, it.lhs.val));
                block rhs = new block(++currFunc.reg_num, currBlk), dest = new block(++currFunc.reg_num, currBlk);
                currBlk.push_back(new branch(l, dest, rhs));

                register r = new register(++currFunc.reg_num, it.rhs.type);
                rhs.push_back(new load(r, it.rhs.val));
                rhs.push_back(new unary("snez", new constant(0), r, l));
                rhs.push_back(new jump(dest));

                it.val = new register(++currFunc.reg_num, new type(type.IRType.BOOL));
                dest.push_back(new store(it.val, l));
                currBlk = dest;
            }
            default -> {
                it.val = new register(++currFunc.reg_num, it.type);
                currBlk.push_back(new binary(it.op, it.lhs.val, it.rhs.val, it.val));
            }
        }
    }

    public void visit(ExprListNode it){
        for (ExprNode x : it.exprs) x.accept(this);
    }

    public void visit(FuncExprNode it){
        call callFunc;
        if (it.funcName instanceof MemberExprNode) {
            callFunc = new call(++currFunc.reg_num, new type(it.type), ((MemberExprNode)it.funcName).member, ((MemberExprNode)it.funcName).name.str);
        } else {
            callFunc = new call(++currFunc.reg_num, new type(it.type), it.funcName.str);
        }
        for (ExprNode x : it.lists.exprs) {
            x.accept(this);
            callFunc.paramList.add(x.val);
        }
        if (it.type.typeName.equals("void")) {
            callFunc.returnReg = null;
        } else {
            callFunc.returnReg = new register(++currFunc.reg_num, it.type);
        }
        currBlk.push_back(callFunc);
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

    public void visit(NewExprNode it){ //只允许1层new
        //todo
        it.val = new register(++currFunc.reg_num, it.type);
        ExprNode tmp = it.sizeList.get(0);
        tmp.accept(this);
        if (tmp.val instanceof constant) {
            currBlk.push_back(new createPtr(it.val, tmp.val));
        } else {
            register r = new register(++currFunc.reg_num, tmp.val.irType);
            currBlk.push_back(new load(r, tmp.val));
            currBlk.push_back(new createPtr(it.val, r));
        }
    }

    public void visit(PreAddExprNode it){
        it.expr.accept(this);
        register l = new register(++currFunc.reg_num, it.expr.type);
        currBlk.push_back(new load(l, it.expr.val));
        it.val = new register(++currFunc.reg_num, it.type);
        currBlk.push_back(new unary("addi" , new constant(it.op.equals("++") ? 1 : -1), l, it.val));
        currBlk.push_back(new store(it.expr.val, it.val));
    }

    public void visit(UnaryExprNode it){ //!,~,+,-,++,--
        it.expr.accept(this);
        register d = new register(++currFunc.reg_num, it.expr.type);
        currBlk.push_back(new load(d, it.expr.val));
        switch (it.op) {
            case "!" -> {
                it.val = new register(++currFunc.reg_num, it.type);
                currBlk.push_back(new unary("seqz", new constant(0), d, it.val));
            }
            case "~" -> {
                it.val = new register(++currFunc.reg_num, it.type);
                currBlk.push_back(new unary("xori", new constant(-1), d, it.val));
            }
            case "-" -> {
                it.val = new register(++currFunc.reg_num, it.type);
                currBlk.push_back(new binary("-", new constant(0), d, it.val));
            }
            case "++" -> {
                register dest = new register(++currFunc.reg_num, it.expr.type);
                currBlk.push_back(new unary("addi", new constant(1), d, dest));
                currBlk.push_back(new store(it.expr.val, dest));
                it.val = d;
            }
            case "--" -> {
                register dest = new register(++currFunc.reg_num, it.expr.type);
                currBlk.push_back(new unary("addi", new constant(-1), d, dest));
                currBlk.push_back(new store(it.expr.val, dest));
                it.val = d;
            }
            case "+" -> it.val = d;
            default -> {}
        }

    }
}
