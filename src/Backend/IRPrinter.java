package Backend;

import MIR.*;

import java.io.PrintStream;
import java.util.Map;

import MIR.Statmemt.*;
import MIR.terminalStmt.*;

public class IRPrinter implements Pass{
    private PrintStream out;

    public IRPrinter(PrintStream out){
        this.out = out;
    }

    public void printIR(IRBuilder IR){
        //全局变量
        for (Map.Entry<String, entity> entry : IR.gScope.entities.entrySet()){
            if (entry instanceof register) {
                register r = (register) entry.getValue();
                out.print("@" + r.identity + " = global ");
                if (r.irType.irType == type.IRType.INT) {
                    out.print("i32 " + r.irType.int_value + "\n");
                } else if (r.irType.irType == type.IRType.BOOL) {
                    out.print("i8 " + r.irType.boolean_value + "\n");
                } else if (r.irType.irType == type.IRType.STRING) {
                    int l = r.irType.string_value.length();
                    out.print("i8* getelementptr ([" + l + " x i8], [" + l + " x i8]* @" + r.irType.string_value +", i32 0, i32 0)\n");
                }
            } else {
                constant c = (constant) entry.getValue();
                out.print("@" + c.irType.string_value + " = global [" + c.irType.string_value.length() + " x i8] c\"" + c.irType.string_value + "\\00\"\n");
            }
        }
        //类定义
        for (classVar x : IR.classLists.values()) visitClass(x);
        //函数
        for (function x : IR.functions.values()) visitFunction(x);
        out.close();
    }

    public void visitBlock(block b){
        out.print(b.label+":\n");
        for (statement s : b.stmtList) visitStatement(b, s);
        visitTerminalStmt(b.tailStmt);
    }

    public void visitFunction(function f){
        if (f.isInClass) {
            out.print("define " + getFuncType(f.returnType) + " @class_" + f.className + "_" + f.funcName + "(");
        } else {
            out.print("define " + getFuncType(f.returnType) + " @" + f.funcName + "(");
        }
        boolean first = true;
        for (Map.Entry<String, entity> entry : f.paraList.entrySet()){
            if (first) first = false;
            else out.print(",");
            out.print(getTypeName(entry.getValue()) + " %" + ((register)entry.getValue()).identity);
        }
        out.print(") {\n");
        for (entity x : f.entities) out.print("\t%"+((register)x).reg_num+" = alloca " + getTypeName(x)+"\n");
        visitBlock(f.rootBlock);
        out.print("}\n");
    }
    public void visitClass(classVar c){
        out.print("%class."+c.className+" = type {");
        boolean first = true;
        for (Map.Entry<String, entity> e : c.entities.entrySet()) {
            if (first) first = false;
            else out.print(", ");
            out.print(getTypeName(e.getValue()));
        }
        out.print("}\n");
        for (Map.Entry<String, function> f : c.functions.entrySet()) {
            visitFunction(f.getValue());
        }
    }

    public void visitStatement(block b, statement s){
        if (s instanceof varDef && ((varDef) s).have_init) {
            visitVarDef((varDef) s);
        } else if (s instanceof assign) {
            visitAssign((assign) s);
        } else if (s instanceof call) {
           visitCall((call) s);
        } else if (s instanceof binary) {
            visitBinary(b, (binary) s);
        } else if (s instanceof unary) {
            visitUnary((unary) s);
        } else if (s instanceof preAdd) {
            visitPreAdd((preAdd) s);
        }
    }

    public void visitEntity(entity e){
        if (e instanceof register) {
            if (e.irType.isArray) {
                //  %2 = getelementptr inbounds [5 x [6 x [7 x i32]]], [5 x [6 x [7 x i32]]]* %1, i32 0, i32 1
                //  %3 = getelementptr inbounds [6 x [7 x i32]], [6 x [7 x i32]]* %2, i32 0, i32 2
                //  %4 = getelementptr inbounds [7 x i32], [7 x i32]* %3, i32 0, i32 3
                for (int i = ((register)e).dim; i > 0; i--) {
                    int reg = e.new_reg_num - i;
                    out.print("\t%" + reg + " = getelementptr ");
                    for (int j = i; j> 0; --j) out.print("[" + e.irType.each_num.get(j-1) + " x ");
                    out.print("i32");
                    for (int j = i; j> 0; --j) out.print("], ");
                    for (int j = i; j> 0; --j) out.print("[" + e.irType.each_num.get(j-1) + " x ");
                    out.print("i32");
                    for (int j = i; j> 0; --j) out.print("]");
                    int second_reg = (i == ((register)e).dim) ? ((register)e).reg_num : reg - 1;
                    out.print("* %" + second_reg + ", i32 0, i32 " + ((register)e).each_num.get(i-1) + "\n");
                }
                int dim = e.irType.dim - ((register)e).dim, last_reg = e.new_reg_num - 1;
                out.print("\t%" + e.new_reg_num + " = load i32");
                for (int i = dim; i>0;i--) out.print("*");
                out.print(", i32");
                for (int i = dim; i>=0;i--) out.print("*");
                out.print(" %" + last_reg + "\n");
            } else if (e.irType.irType == type.IRType.STRING) {
                out.print("\t%" + e.new_reg_num + " = load i8*, i8** @" + ((register) e).identity + "\n");
            } else if (e.irType.irType == type.IRType.INT) {
                out.print("\t%" + e.new_reg_num + " = load i32, i32* %" + ((register)e).reg_num + "\n");
            } else if (e.irType.irType == type.IRType.BOOL) {
                out.print("\t%" + e.new_reg_num + " = load i8, i8* %" + ((register)e).reg_num + "\n");
            }
        } else if (e instanceof constant && e.irType.irType == type.IRType.STRING) {
            int l = e.irType.string_value.length();
            out.print("\t%" + e.new_reg_num + " = load i8*" + " getelementptr ([" + l + " x i8], [" + l + " xi8]* @" + e.irType.string_value + ", i32 0, i32 0\n");
        }
    }

    public void visitTerminalStmt(terminalStmt tail){
        if (tail instanceof jump) {
            out.print("\tbr label %" + ((jump)tail).destination.label + "\n");
            visitBlock(((jump)tail).destination);
        } else if (tail instanceof ret) {
            if (((ret)tail).value == null) {
                out.print("\tret void\n");
            } else {
                out.print("\tret "+getTypeName(((ret)tail).value)+" %"+((register)((ret)tail).value).reg_num + "\n");
            }
        } else if (tail instanceof branch) {
            out.print("\tbr i1 %"+((register)((branch)tail).op).reg_num+", label %"+((branch)tail).trueBranch.label+", %"+((branch)tail).falseBranch.label+"\n");
            visitBlock(((branch)tail).trueBranch);
            visitBlock(((branch)tail).falseBranch);
        } else if (tail instanceof forLoop) {
            out.print("\tbr label %"+((forLoop)tail).start.label+"\n");
            visitBlock(((forLoop)tail).start);
            visitBlock(((forLoop)tail).forBlock);
        } else if (tail instanceof whileLoop) {
            out.print("\tbr label %"+((whileLoop)tail).condition.label+"\n");
            visitBlock(((whileLoop)tail).condition);
            visitBlock(((whileLoop)tail).whileBlock);
        }
    }

    public String getFuncType(String name){
        if (name.equals("int") || name.equals("bool")) {
            return "i32";
        } else {
            return name;
        }
    }

    public String getTypeName(entity e) {
        if (!((register) e).isInClass) {
            return switch (((register) e).identity) {
                case "int" -> "i32";
                case "bool" -> "i8";
                case "string" -> "i8*";
                default -> "";
            };
        } else {
            return "%class."+((register) e).className;
        }
    }

    public void visitAssign(assign s){
        if (s.rhs instanceof constant) {
            if (s.rhs.irType.irType == type.IRType.INT) {
                out.print("\tstore i32 " + s.rhs.irType.int_value+", i32* %"+((register)s.lhs).reg_num+"\n");
            } else if (s.rhs.irType.irType == type.IRType.BOOL) {
                out.print("\tstore i8 " + s.rhs.irType.boolean_value+", i8* %"+((register) s.lhs).reg_num+"\n");
            }
        } else { //reg
            visitEntity(s.rhs);
            if (((register)s.lhs).dim > 0) {
                for (int i = ((register)s.lhs).dim; i > 0; i--) {
                    int reg = s.lhs.new_reg_num - i;
                    out.print("\t%" + reg + " = getelementptr ");
                    for (int j = i; j> 0; --j) out.print("[" + s.lhs.irType.each_num.get(j-1) + " x ");
                    out.print("i32");
                    for (int j = i; j> 0; --j) out.print("], ");
                    for (int j = i; j> 0; --j) out.print("[" + s.lhs.irType.each_num.get(j-1) + " x ");
                    out.print("i32");
                    for (int j = i; j> 0; --j) out.print("]");
                    int second_reg = (i == ((register)s.lhs).dim) ? ((register)s.lhs).reg_num : reg - 1;
                    out.print("* %" + second_reg + ", i32 0, i32 " + ((register)s.lhs).each_num.get(i-1) + "\n");
                }
            } else {
                s.lhs.new_reg_num = ((register)s.lhs).reg_num;
            }
            out.print("\tstore " + getTypeName(s.rhs) + " %" + s.rhs.new_reg_num +
                    ", " + getTypeName(s.lhs) + "* %" + s.lhs.new_reg_num + "\n");
        }
    }

    public void visitBinary(block b, binary s){
        visitEntity(s.op1);
        visitEntity(s.op2);
        switch (s.op) {
            case "*" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = mul nsw i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "/" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = sdiv i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "%" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = srem i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "+" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = add nsw i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "-" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = sub i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "<<" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = shl i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case ">>" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = ashr i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "<" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + (s.lhs.new_reg_num - 1) + " = icmp slt i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
                out.print("\t%" + s.lhs.new_reg_num + " = zext i1 %" + (s.lhs.new_reg_num - 1) + " to i32\n");
            }
            case ">" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + (s.lhs.new_reg_num - 1) + " = icmp sqt i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
                out.print("\t%" + s.lhs.new_reg_num + " = zext i1 %" + (s.lhs.new_reg_num - 1) + " to i32\n");
            }
            case "<=" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + (s.lhs.new_reg_num - 1) + " = icmp sle i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
                out.print("\t%" + s.lhs.new_reg_num + " = zext i1 %" + (s.lhs.new_reg_num - 1) + " to i32\n");
            }
            case ">=" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + (s.lhs.new_reg_num - 1) + " = icmp sge i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
                out.print("\t%" + s.lhs.new_reg_num + " = zext i1 %" + (s.lhs.new_reg_num - 1) + " to i32\n");
            }
            case "==" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + (s.lhs.new_reg_num - 1) + " = icmp eq i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
                out.print("\t%" + s.lhs.new_reg_num + " = zext i1 %" + (s.lhs.new_reg_num - 1) + " to i32\n");
            }
            case "!=" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + (s.lhs.new_reg_num - 1) + " = icmp ne i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
                out.print("\t%" + s.lhs.new_reg_num + " = zext i1 %" + (s.lhs.new_reg_num - 1) + " to i32\n");
            }
            case "&" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = and i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "^" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = xor i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "|" -> {
                visitEntity(s.op1);
                visitEntity(s.op2);
                out.print("\t%" + s.lhs.new_reg_num + " = or i32 %" + s.op1.new_reg_num + ", %" + s.op2.new_reg_num + "\n");
            }
            case "&&" -> {
                int last = s.lhs.new_reg_num, l1 = last - 3, l2 = last - 2;
                visitEntity(s.op1);
                out.print("\t%" + (s.op1.new_reg_num+1) + " = icmp ne i32 %" + s.op1.new_reg_num + ", 0\n");
                out.print("\tbr i1 %" + (s.op1.new_reg_num+1) + ", label %" + l2 + ", label %" + l1 + "\n");
                out.print(l1 + ":\n");
                visitEntity(s.op2);
                out.print("\t%" + (s.op2.new_reg_num+1) + " = icmp ne i32 %" + s.op2.new_reg_num + ", 0\n");
                out.print("\tbr label %" + l2 + "\n");
                out.print(l2 + ":\n");
                out.print("\t%" + (last - 1) + " = phi i1 [ false, %" + b.label + " ], [ %" + l2 + ", %" + l1 + " ]\n");
                out.print("\t%" + last + " = zext i1 %" + (last-1) + " to i32\n");
            }
            case "||" -> {
                int last = s.lhs.new_reg_num, l1 = last - 3, l2 = last - 2;
                visitEntity(s.op1);
                out.print("\t%" + (s.op1.new_reg_num+1) + " = icmp ne i32 %" + s.op1.new_reg_num + ", 0\n");
                out.print("\tbr i1 %" + (s.op1.new_reg_num+1) + ", label %" + l1 + ", label %" + l2 + "\n");
                out.print(l1 + ":\n");
                visitEntity(s.op2);
                out.print("\t%" + (s.op2.new_reg_num+1) + " = icmp ne i32 %" + s.op2.new_reg_num + ", 0\n");
                out.print("\tbr label %" + l2 + "\n");
                out.print(l2 + ":\n");
                out.print("\t%" + (last - 1) + " = phi i1 [ true, %" + b.label + " ], [ %" + l2 + ", %" + l1 + " ]\n");
                out.print("\t%" + last + " = zext i1 %" + (last-1) + " to i32\n");
            }
            default -> {
            }
        }
    }

    public void visitCall(call s) {
        for (entity e : s.paramList) visitEntity(e);
        if (s.className == null) {
            out.print("\t%"+s.returnReg.new_reg_num+" = call "+getTypeName(s.returnReg)+ " @"+s.functionName+"(");
        } else {
            out.print("\t%"+ s.returnReg.new_reg_num + " = call " + getTypeName(s.returnReg) + " @class_"+s.className+"_"+s.functionName+"(");
        }
        boolean first = true;
        for (entity e : s.paramList) {
            if (first) first = false;
            else out.print(", ");
            out.print(getTypeName(e)+" ");
            if (e instanceof constant) {
                switch (e.irType.irType) {
                    case INT -> out.print(e.irType.int_value);
                    case BOOL -> out.print(e.irType.boolean_value);
                    case STRING -> out.print("c\""+e.irType.string_value+"\"");
                    case NULL -> out.print("null");
                    default -> {}
                }
            } else {
                out.print("%"+((register)e).new_reg_num);
            }
        }
        out.print(")\n");
    }

    public void visitPreAdd(preAdd s) {
        visitEntity(s.v);
        if (s.op.equals("++")) {
            out.print("\t%" + s.re.new_reg_num + " = add nsw i32 %" + s.v.new_reg_num + ", 1\n");
        } else {
            out.print("\t%" + s.re.new_reg_num + " = add nsw i32 %" + s.v.new_reg_num + ", -1\n");
        }
        //store i32 %3, i32* %1
        out.print("\tstore i32 %" + s.re.new_reg_num + ", i32* %" + ((register)s.v).reg_num);
    }

    public void visitUnary(unary s) {
        visitEntity(s.v);
        switch (s.op) {
            case "++" -> {
                out.print("\t%" + s.re.new_reg_num + " = add nsw i32 %" + s.v.new_reg_num + ", 1\n");
                out.print("\tstore i32 %" + s.re.new_reg_num + ", i32* %" + ((register) s.v).reg_num);
            }
            case "--" -> {
                out.print("\t%" + s.re.new_reg_num + " = add nsw i32 %" + s.v.new_reg_num + ", -1\n");
                out.print("\tstore i32 %" + s.re.new_reg_num + ", i32* %" + ((register) s.v).reg_num);
            }
            case "!" -> {
                out.print("\t%" + (s.re.new_reg_num - 2) + " = icmp ne i32 %" + s.v.new_reg_num + ", 0\n");
                out.print("\t%" + (s.re.new_reg_num - 1) + " = xor i1 %" + (s.re.new_reg_num-2) + ", true\n");
                out.print("\t%" + s.re.new_reg_num + " = zext i1 %" + (s.re.new_reg_num - 1) + " to i32\n");
                //out.print("\tstore i32 %" + (s.re.new_reg_num + 2) + ", i32* %" + ((register) s.v).reg_num + "\n");
            }
            case "~" -> {
                out.print("\t%" + s.re.new_reg_num + " = xor i32 %" + s.re.new_reg_num + ", -1\n");
                //out.print("\tstore i32 %" + s.re.new_reg_num + ", i32* %" + ((register) s.v).reg_num + "\n");
            }
            case "+" -> {
                //out.print("\tstore i32 %" + s.re.new_reg_num + ", i32* %" + ((register) s.v).reg_num + "\n");
            }
            case "-" -> {
                out.print("\t%" + s.re.new_reg_num + " = sub nsw i32 0\n");
                //out.print("\tstore i32 %" + s.re.new_reg_num + ", i32* %" + ((register) s.v).reg_num + "\n");
            }
        }
    }

    public void visitVarDef(varDef s) {
        if (s.init instanceof constant) {
            if (s.init.irType.irType == type.IRType.INT) {
                out.print("\tstore i32 " + s.init.irType.int_value + ", i32* %" + s.var.reg_num + "\n");
            } else if (s.init.irType.irType == type.IRType.BOOL) {
                out.print("\tstore i8 " + s.init.irType.boolean_value + ", i8* %" + s.var.reg_num + "\n");
            } else if (s.init.irType.irType == type.IRType.STRING) {
                out.print("\tstore i8* " + "" + ", i8** %" + s.var.reg_num + "\n");
            }
        } else {
            visitEntity(s.init);
            out.print("\tstore " + getTypeName(s.var) + " %" + ((register)(s.init)).reg_num +
                    ", " + getTypeName(s.var) + "* %" + s.var.reg_num + "\n");
        }
    }

}
