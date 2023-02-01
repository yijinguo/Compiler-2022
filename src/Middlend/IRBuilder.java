package Middlend;

import AST.*;
import AST.expr.*;
import AST.stmt.*;
import Assembly.Inst.Store;
import MIR.entity.*;
import MIR.terminalStmt.*;
import MIR.type.IRInt;
import Util.Scope.*;

import MIR.*;
import MIR.Statmemt.*;
import MIR.type.*;
import Util.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class IRBuilder implements ASTVisitor {

    //对每个函数开一个rootBlock。
    //对每一个类开一个结点。
    private block currBlk = null;
    private function currFunc = null;
    private IRClass currClass = null;

    public function mainFn = null;
    public boolean isMain = false;
    public HashMap<String, function> functions = new HashMap<>();
    public HashMap<String, IRClass> classTypes = new HashMap<>();
    public Scope currScope;
    public globalScope gScope;

    public IRBuilder(/*mainFn mainfn, */globalScope gScope){
        this.gScope = gScope;
        this.currScope = gScope;
    }

    public void visit(RootNode it){
        it.DefList.forEach(x->{
            if (x instanceof ClassDefNode) {
                classTypes.put(((ClassDefNode) x).name, new IRClass(((ClassDefNode) x).name, ((ClassDefNode) x).varList.size() << 2));
            }
        });
        it.DefList.forEach(x->{
            if (x instanceof ClassDefNode) x.accept(this);
        });
        it.DefList.forEach(x->{
            if (x instanceof VarDefNode) x.accept(this);
        });
        it.DefList.forEach(x->{
            if (x instanceof FuncDefNode) x.accept(this);
        });
    }

    public void visit(MainFnNode it){
        it.stmts.forEach(s -> s.accept(this));
    }

    private IRType transType(Type type){
        IRType res;
        switch (type.typeName) {
            case "int" -> res = new IRInt(32);
            case "bool" -> res = new IRInt(8);
            case "void", "null" -> res = new IRVoid();
            case "string" -> res = new IRPtr(new IRInt(8));
            default -> res = classTypes.get(type.typeName);
        }
        if (type.dim > 0)
            res = new IRPtr(res, type.dim);
        return res;
    }

    private register createReg(String name, Type type){
        return new register(name, transType(type));
    }

    public void visit(ClassDefNode it){
        currScope = new Scope(currScope, it);
        currClass = classTypes.get(it.name);
        it.varList.forEach(x->x.accept(this));
        currClass.calc_size();
        if (it.classBuilder != null) {
            currClass.have_build = true;
            it.classBuilder.accept(this);
        }
        it.funcList.forEach(x->x.accept(this));
        currScope = currScope.parentScope;
        currClass = null;
    }

    public void visit(ClassBuildNode it){
        //todo
        //需要进一步修改
        currFunc = new function(new IRVoid(), currClass.name + "_" + it.name);
        it.suites.accept(this);
        currClass.build = currFunc;
        currFunc = null;
    }

    public void visit(FuncDefNode it){
        register.reg_cnt = 0;
        it.returnType.accept(this);
        if (currClass != null) {
            currFunc = new function(it.returnType.irType, currClass.name + "." + it.funcName);
        } else {
            currFunc = new function(it.returnType.irType, it.funcName);
        }
        currBlk = currFunc.rootBlock;

        if (currClass != null) {
            IRPtr classPtr = new IRPtr(currClass);
            register thisVar = new register("this", classPtr), thisAddr = new register("this.addr", new IRPtr(classPtr));
            currFunc.paraList.put("this", thisVar);
            currBlk.push_back(new alloca(classPtr, thisAddr));
            currBlk.push_back(new store(thisVar, thisAddr));
            currScope.entities.put("this", thisAddr);
        }
        if (it.params != null) it.params.accept(this);

        functions.put(currFunc.funcName, currFunc);
        if (currFunc.funcName.equals("main")) {
            isMain = true;
            mainFn = currFunc;
        }

        currScope = new Scope(currScope);
        it.stmts.forEach(x -> x.accept(this));
        if (currFunc.returnReg == null) {
            currFunc.returnReg = isMain ? new consInt(0) : new consNull();
        }
        currBlk.push_back(new ret(currFunc.returnReg));
        currScope = currScope.parentScope;
        isMain = false;
        currFunc = null;
        currBlk = null;
    }

    public void visit(ParameterListNode it){
        for (VarDefUnitNode x : it.varList) {
            x.accept(this);
            register reg = createReg(x.varName, x.type.type);
            currFunc.paraList.put(x.varName, reg);
            //currBlk.push_back(new store(reg, currScope.getEntity(x.varName)));
        }
    }

    public void visit(VarDefNode it){
        it.units.forEach(x->x.accept(this));
    }

    public void visit(VarDefUnitNode it){
        it.type.accept(this);
        if (currFunc != null) {
            register ptr = new register(it.varName+".addr", new IRPtr(it.type.irType));
            currBlk.push_back(new alloca(transType(it.type.type), ptr));
            currScope.entities.put(it.varName, ptr);
            if (it.init != null) {
                it.init.accept(this);
                currBlk.push_back(new store(ptr, it.init.val));
            }
        } else if (currClass != null) {
            currClass.add_member(it.varName, it.type.irType);
        } else { //全局变量
            globalVar var = new globalVar(it.varName, it.type.irType);
            if (it.init != null) {
                it.init.accept(this);
                var.init = it.init.val;
            } else {
                var.initial();
                if (var.init == null) {
                    //todo
                    //不确定
                    var.ifClass = true;
                    var.build = classTypes.get(it.type.irType.name).build;
                }
            }
            gScope.entities.put(it.varName, var);
        }
    }
    public void visit(TypeNode it){
        it.irType = transType(it.type);
    }


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
        currScope = new Scope(currScope);
        block start = new block(++currFunc.block_cnt, currBlk, "_for_cond"), forBlock = new block(++currFunc.block_cnt, currBlk, "_for_loop");
        block destination = new block(++currFunc.block_cnt, start, "_for_next");

        if (it.varDef != null) it.varDef.accept(this);
        else if (it.init != null) it.init.accept(this);
        currBlk.push_back(new loop(start, forBlock));

        currBlk = start;
        if (it.condition != null) {
            it.condition.accept(this);
            if (currBlk.tailStmt == null) currBlk.push_back(new branch(it.condition.val, forBlock, destination));
        } else {
            if (currBlk.tailStmt == null) currBlk.push_back(new branch(new consBool(false), forBlock, destination));
        }

        currBlk = forBlock;
        if (it.step != null) it.step.accept(this);
        for (StmtNode x : it.stmts) x.accept(this);
        if (currBlk.tailStmt == null) currBlk.push_back(new jump(start));

        currBlk = destination;
        currFunc.blocks.add(start);
        currFunc.blocks.add(forBlock);
        currFunc.blocks.add(destination);
        currScope = currScope.parentScope;
    }

    public void visit(IfStmtNode it){
        it.condition.accept(this);
        block trueBranch = new block(++currFunc.block_cnt, currBlk, "_if_then");
        block destination;

        if (it.elseStmts == null) {
            destination = new block(++currFunc.block_cnt, currBlk, "_if_next");
            currBlk.push_back(new branch(it.condition.val,trueBranch,destination));
            currBlk = trueBranch;
            currScope = new Scope(currScope);
            for (StmtNode x : it.thenStmts) x.accept(this);
            currScope = currScope.parentScope;
            if (currBlk.tailStmt == null) currBlk.push_back(new jump(destination));
            currFunc.blocks.add(trueBranch);
            currFunc.blocks.add(destination);
        } else {
            block falseBranch = new block(++currFunc.block_cnt, currBlk, "_if_else");
            destination = new block(++currFunc.block_cnt, currBlk, "_if_next");
            currBlk.push_back(new branch(it.condition.val,trueBranch,falseBranch));
            currBlk = trueBranch;
            currScope = new Scope(currScope);
            for (StmtNode x : it.thenStmts) x.accept(this);
            currScope = currScope.parentScope;
            if (currBlk.tailStmt == null) currBlk.push_back(new jump(destination));

            currBlk = falseBranch;
            currScope = new Scope(currScope);
            for (StmtNode x : it.elseStmts) x.accept(this);
            currScope = currScope.parentScope;
            if (currBlk.tailStmt == null) currBlk.push_back(new jump(destination));
            currFunc.blocks.add(trueBranch);
            currFunc.blocks.add(falseBranch);
            currFunc.blocks.add(destination);
        }
        currBlk = destination;
    }

    public void visit(ReturnStmtNode it){
        if (it.expr == null) {
            if (isMain)
                currFunc.returnReg = new consInt(0);
            else
                currFunc.returnReg = new consNull();
        } else {
            it.expr.accept(this);
            if (it.expr.val instanceof constant) {
                currFunc.returnReg = it.expr.val;
            } else {
                currFunc.returnReg = new register(currFunc.returnType);
                currBlk.push_back(new store(currFunc.returnReg, it.expr.val));
            }
        }


    }

    public void visit(SuiteNode it){
        for (StmtNode x : it.stmts) x.accept(this);
    }

    public void visit(WhileStmtNode it){
        block condition = new block(++currFunc.block_cnt, currBlk, "_while_cond"), whileBlock = new block(++currFunc.block_cnt, currBlk, "while_loop");
        block destination = new block(++currFunc.block_cnt, condition, "_while_next");
        currBlk.push_back(new loop(condition, whileBlock));
        currScope = new Scope(currScope);

        currBlk = condition;
        it.condition.accept(this);
        if (currBlk.tailStmt == null) currBlk.push_back(new branch(it.condition.val, whileBlock, destination));

        currBlk = whileBlock;
        for (StmtNode x : it.stmts) x.accept(this);
        if (currBlk.tailStmt == null) currBlk.push_back(new jump(condition));

        currScope = currScope.parentScope;
        currBlk = destination;
        currFunc.blocks.add(condition);
        currFunc.blocks.add(whileBlock);
        currFunc.blocks.add(destination);
    }

    //public void visit(ExprNode it){}
    public void visit(ArrayExprNode it){
        it.arrayName.accept(this);
        it.index.accept(this);
        it.val = createReg("", it.arrayName.type);
        currBlk.push_back(new getelementptr(it.val, it.arrayName.val, it.index.val));
    }

    public void visit(AssignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        entity r;
        if (it.rhs.val instanceof constant || !(it.rhs.val.irType instanceof IRPtr)) {
            r = it.rhs.val;
        } else {
            r = createReg("", it.lhs.type);
            currBlk.push_back(new load(r, it.rhs.val));
        }
        currBlk.push_back(new store(it.lhs.val, r));
    }

    public void visit(AtomExprNode it) {
        if (it.str.equals("this")) {
            it.val = currScope.getEntity("this");
            return;
        }
        if (it.type.isConst) { //处理常数
            switch (it.type.typeName) {
                case "int" -> it.val = new consInt(Integer.parseInt(it.str));
                case "bool" -> it.val = new consBool(it.str.equals("true"));
                case "string" -> {
                    it.val = new consString(it.str);
                    gScope.entities.put(it.str, it.val);
                }
                case "null" -> it.val = new consNull();
                default -> {

                }
            }
        } else {
            //todo
            //不确定
            it.val = currScope.getEntity(it.str);
        }

    }

    public void visit(BinaryExprNode it){
        //还可以是string型
        //考虑bool型
        //todo
        it.lhs.accept(this);
        it.rhs.accept(this);
        switch (it.op) {
            case "<", ">", "<=", ">=", "==", "!=" -> {
                entity l, r;
                if (it.lhs.val instanceof constant || !(it.lhs.val.irType instanceof IRPtr)) {
                    l = it.lhs.val;
                } else {
                    l = createReg("", it.lhs.type);
                    currBlk.push_back(new load(l, it.lhs.val));
                }
                if (it.rhs.val instanceof constant || !(it.rhs.val.irType instanceof IRPtr)) {
                    r = it.rhs.val;
                } else {
                    r = createReg("", it.rhs.type);
                    currBlk.push_back(new load(r, it.rhs.val));
                }
                register tmp = new register(new IRInt(1));
                currBlk.push_back(new icmp(it.op, l, r, tmp));
                it.val = tmp;
                //it.val = new register(new IRInt(32));
                //currBlk.push_back(new zext(it.val, tmp));
            }
            case "&&" -> {
                entity l;
                if (it.lhs.val instanceof constant || !(it.lhs.val.irType instanceof IRPtr)) {
                    l = it.lhs.val;
                } else {
                    l = createReg("", it.lhs.type);
                    currBlk.push_back(new load(l, it.lhs.val));
                }
                register d = new register(new IRInt(1));
                currBlk.push_back(new icmp("ne", l, new consInt(0), d));

                block rhs = new block(++currFunc.block_cnt, currBlk, "_land_rhs"), dest = new block(++currFunc.block_cnt, currBlk, "_land_next");
                currBlk.push_back(new branch(d, rhs, dest));
                entity r;
                if (it.rhs.val instanceof constant || !(it.rhs.val.irType instanceof IRPtr)) {
                    r = it.rhs.val;
                } else {
                    r = createReg("", it.rhs.type);
                    rhs.push_back(new load(r, it.rhs.val));
                }
                rhs.push_back(new icmp("ne", r, new consInt(0), d));
                rhs.push_back(new jump(dest));
                it.val = d;
                //it.val = new register(new IRInt(32));
                //dest.push_back(new zext(it.val, d));
                currBlk = dest;
                currFunc.blocks.add(rhs);
                currFunc.blocks.add(dest);
            }
            case "||" -> {
                entity l;
                if (it.lhs.val instanceof constant || !(it.lhs.val.irType instanceof IRPtr)) {
                    l = it.lhs.val;
                } else{
                    l = createReg("", it.lhs.type);
                    currBlk.push_back(new load(l, it.lhs.val));
                }
                register d = new register(new IRInt(1));
                currBlk.push_back(new icmp("ne", l, new consInt(0), d));

                block rhs = new block(++currFunc.block_cnt, currBlk, "_lor_rhs"), dest = new block(++currFunc.block_cnt, currBlk, "_lor_next");
                currBlk.push_back(new branch(d, dest, rhs));
                entity r;
                if (it.rhs.val instanceof constant || !(it.rhs.val.irType instanceof IRPtr)) {
                    r = it.rhs.val;
                } else {
                    r = createReg("", it.rhs.type);
                    rhs.push_back(new load(r, it.rhs.val));
                }
                rhs.push_back(new icmp("ne", r, new consInt(0), d));
                rhs.push_back(new jump(dest));
                it.val = d;
                //it.val = new register(new IRInt(32));
                //dest.push_back(new zext(it.val, d));
                currBlk = dest;
                currFunc.blocks.add(rhs);
                currFunc.blocks.add(dest);
            }
            default -> {
                it.val = createReg("", it.type);
                currBlk.push_back(new binary(it.op, it.lhs.val, it.rhs.val, it.val));
            }
        }
    }

    public void visit(ExprListNode it){
        for (ExprNode x : it.exprs) x.accept(this);
    }

    public void visit(FuncExprNode it){
        String funcName;
        if (it.funcName instanceof MemberExprNode)
            funcName = ((MemberExprNode) it.funcName).name.str + "." + ((MemberExprNode) it.funcName).member;
        else
            funcName = it.funcName.str;
        it.val = createReg("", it.type);
        call callFunc = new call((register) it.val, funcName);
        //todo
        //check builtin
        if (it.lists != null) {
            for (ExprNode x : it.lists.exprs) {
                x.accept(this);
                if (x.val instanceof constant || !(x.val.irType instanceof IRPtr)) {
                    callFunc.paramList.add(x.val);
                } else {
                    register new_x = createReg("", x.type);
                    currBlk.push_back(new load(new_x, x.val));
                    callFunc.paramList.add(new_x);
                }
            }
        }

        currBlk.push_back(callFunc);
        it.val = callFunc.returnReg;
    }

    public void visit(LambdaExprNode it){}
    public void visit(MemberExprNode it){ //函数调用不跑这个函数，只涉及变量调用
        it.name.accept(this);
        IRType realType = ((IRPtr) it.name.val.irType).pointDown(); //给name的type降级
        if (realType instanceof IRClass) {
            IRType memType = ((IRClass) realType).get_member(it.member);
            if (memType != null) {
                it.val = new register(new IRPtr(memType));
                currBlk.push_back(new getelementptr(it.val, it.name.val, new consInt(0),
                            new consInt(((IRClass) realType).memberMap.get(it.member))));
            }
        }
    }

    private entity fetchNewArray(IRType type, int loc, ArrayList<ExprNode> sizeList){
        register callReturn = new register(new IRPtr(new IRInt(8)));
        sizeList.get(loc).accept(this);
        entity cnt = sizeList.get(loc).val, size;
        int typeSize = ((IRPtr) type).pointDown().size;
        if (cnt instanceof consInt) {
            size = new consInt(((consInt) cnt).value * typeSize + 4);
        } else {
            consInt sizeOfType = new consInt(typeSize);
            register tmp = new register(new IRInt(32));
            currBlk.push_back(new binary("*", cnt, sizeOfType, tmp));
            size = new register(new IRInt(4));
            currBlk.push_back(new binary("+", tmp, new consInt(4), size));
        }
        currBlk.push_back(new call(callReturn, "malloc", size));
        //完成空间的申请
        register ptr, tmp1 = new register(new IRPtr(new IRInt(32))), tmp2 = new register(new IRPtr(new IRInt(32)));
        currBlk.push_back(new bitcast(tmp1, callReturn));
        currBlk.push_back(new store(tmp1, cnt));
        currBlk.push_back(new getelementptr(tmp2, tmp1, new consInt(1)));
        if (((IRPtr) type).base instanceof IRInt && ((IRPtr) type).base.size == 4) { //i32*
            ptr = tmp2;
        } else {
            ptr = new register(type);
            currBlk.push_back(new bitcast(ptr, tmp2));
        }
        //进入下一层
        if (loc + 1 < sizeList.size()) {
            //手捏一个for循环
            block condition = new block(++currFunc.block_cnt, currBlk, "_for_cond");
            block forLoop = new block(++currFunc.block_cnt, currBlk, "_for_loop");
            block destination = new block(++currFunc.block_cnt, currBlk, "_for_next");


            register idx = new register(new IRPtr(new IRInt(32)));
            currBlk.push_back(new alloca(new IRInt(32), idx));
            currBlk.push_back(new store(idx, new consInt(1)));
            currBlk.push_back(new loop(condition, forLoop));

            currBlk = condition;
            register i = new register(new IRInt(32));
            register cond = new register(new IRInt(1));
            currBlk.push_back(new load(i, idx));
            currBlk.push_back(new icmp("<", i, cnt, cond));
            currBlk.push_back(new branch(cond, forLoop, destination));

            currBlk = forLoop;
            entity nextPtr = fetchNewArray(((IRPtr) type).pointDown(), loc+1, sizeList);
            register var = new register(type);
            currBlk.push_back(new getelementptr(var, ptr, i));
            currBlk.push_back(new store(var, nextPtr));
            //step
            register tmp = new register(new IRInt(32));
            currBlk.push_back(new binary("+", i, new consInt(1), tmp));
            currBlk.push_back(new store(idx, tmp));

            currBlk = destination;
            currFunc.blocks.add(condition);
            currFunc.blocks.add(forLoop);
            currFunc.blocks.add(destination);
        }
        return ptr;
    }

    public void visit(NewExprNode it){ //只允许1层new
        IRType type = transType(it.type);
        if (it.dim > 0) {
            it.val = it.sizeList.size() > 0 ? fetchNewArray(type, 0, it.sizeList) : new consNull();
        } else {
            IRType irStringType = new IRPtr(new IRInt(8));
            IRClass classType = (IRClass) ((IRPtr) type).pointDown();
            register callReg = new register(irStringType);
            currBlk.push_back(new call(callReg, "malloc", new consInt(classType.size)));
            it.val = new register(type);
            currBlk.push_back(new bitcast(it.val, callReg));
            if (classType.have_build)
                currBlk.push_back(new call(new register(new IRVoid()), classType.name + "." + classType.name));
                //currBlk.push_back(new call(new register(new IRVoid()), classType.name + "." + classType.name, it.val));
        }
    }

    public void visit(PreAddExprNode it){
        it.expr.accept(this);
        register l = createReg("", it.expr.type);
        currBlk.push_back(new load(l, it.expr.val));
        it.val = createReg("", it.expr.type);
        currBlk.push_back(new binary("add" , l, new consInt(it.op.equals("++") ? 1 : -1), it.val));
        currBlk.push_back(new store(it.expr.val, it.val));
    }

    public void visit(UnaryExprNode it){
        //!还可以是bool型//!,~,+,-,++,--
        //考虑常数直接计算？？
        //todo
        it.expr.accept(this);
        register d = createReg("", it.expr.type);
        currBlk.push_back(new load(d, it.expr.val));
        switch (it.op) {
            case "!" -> {
                register t1 = new register(new IRInt(1));
                register t2 = new register(new IRInt(1));
                it.val = createReg("", it.type);
                currBlk.push_back(new icmp("ne", d, new consInt(0), t1));
                currBlk.push_back(new binary("^", t1, new consBool(true), t2));
                currBlk.push_back(new zext(it.val, t2));
                //currBlk.push_back(new unary("seqz", new consInt(0), d, it.val));
            }
            case "~" -> {
                it.val = createReg("", it.type);
                currBlk.push_back(new binary("^", d, new consInt(-1), it.val));
            }
            case "-" -> {
                it.val = createReg("", it.type);
                currBlk.push_back(new binary("-", new consInt(0), d, it.val));
            }
            case "++" -> {
                register dest = createReg("", it.expr.type);
                currBlk.push_back(new binary("+", d, new consInt(1), dest));
                currBlk.push_back(new store(it.expr.val, dest));
                it.val = d;
            }
            case "--" -> {
                register dest = createReg("", it.expr.type);
                currBlk.push_back(new binary("+", d, new consInt(-1), dest));
                currBlk.push_back(new store(it.expr.val, dest));
                it.val = d;
            }
            case "+" -> it.val = d;
            default -> {}
        }

    }
}
