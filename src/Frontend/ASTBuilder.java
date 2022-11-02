package Frontend;


import AST.*;
import AST.expr.*;
import AST.stmt.*;
import Util.Type;
import Util.error.*;
import Util.Scope.*;
import Util.*;
import grammar.*;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {

    public globalScope GlobalScope;

    public ASTBuilder(globalScope Global){
        this.GlobalScope = Global;
    }

    @Override public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode root = new RootNode(new position(ctx));
        for (var x : ctx.children)
            if (x instanceof MxParser.ClassDefContext) {
                root.DefList.add((ClassDefNode) visit(x));
            } else if (x instanceof MxParser.FuncDefContext) {
                root.DefList.add((FuncDefNode) visit(x));
            } else if (x instanceof MxParser.VarDefContext) {
                root.DefList.add((VarDefNode) visit(x));
            }
        return root;
    }

    @Override public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        FuncDefNode funcDef = new FuncDefNode(new position(ctx), ctx.Identifier().getText());
        funcDef.returnType = (TypeNode) visit(ctx.returnType());
        if (ctx.parameterList() != null)
            funcDef.params = (ParameterListNode) visit(ctx.parameterList());
        funcDef.stmts = ((SuiteNode) visit(ctx.suite())).stmts;
        return funcDef;
    }

    @Override public ASTNode visitReturnType(MxParser.ReturnTypeContext ctx) {
        if (ctx.Void() != null) return new TypeNode(new position(ctx), ctx.getText());
        else return (TypeNode) visit(ctx.type());
    }

    @Override public ASTNode visitParameterList(MxParser.ParameterListContext ctx) {
        ParameterListNode params = new ParameterListNode(new position(ctx));
        for (int i = 0; i < ctx.type().size(); ++i) {
            VarDefUnitNode it = new VarDefUnitNode(new position(ctx));
            it.type = (TypeNode) visit(ctx.type(i));
            it.varName = ctx.Identifier(i).getText();
            params.varList.add(it);
        }
        return params;
    }

    @Override public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        SuiteNode suite = new SuiteNode(new position(ctx));
        ctx.statement().forEach(x->suite.stmts.add((StmtNode) visit(x)));
        return suite;
    }

    @Override public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        ClassDefNode classDef = new ClassDefNode(new position(ctx), ctx.Identifier().getText());
        boolean constructor = false;
        for (var x : ctx.children) {
            if (x instanceof MxParser.ClassBuildContext) {
                if (constructor) throw new syntaxError("Multiple Constructors", new position(ctx));
                constructor = true;
                classDef.classBuilder = (ClassBuildNode) visit(x);
            } else if (x instanceof MxParser.FuncDefContext) {
                classDef.funcList.add((FuncDefNode) visit(x));
            } else if (x instanceof MxParser.VarDefContext) {
                classDef.varList.add((VarDefNode) visit(x));
            }
        }
        return classDef;
    }

    @Override public ASTNode visitClassBuild(MxParser.ClassBuildContext ctx) {
        ClassBuildNode constructor = new ClassBuildNode(new position(ctx));
        constructor.name = ctx.Identifier().getText();
        constructor.suites = (SuiteNode) visit(ctx.suite());
        return constructor;
    }

    @Override public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        VarDefNode varDef = new VarDefNode(new position(ctx));
        TypeNode type = (TypeNode) visit(ctx.type());
        for (var x : ctx.varDefUnit()) {
            VarDefUnitNode it = new VarDefUnitNode(new position(x), type, x.Identifier().getText());
            if (x.expr() != null) it.init = (ExprNode) visit(x.expr());
            varDef.units.add(it);
        }
        return varDef;
     }

    //@Override public T visitVarDefUnit(MxParser.VarDefUnitContext ctx)
    @Override public ASTNode visitType(MxParser.TypeContext ctx) {
        TypeNode typeNode = new TypeNode(new position(ctx));
        typeNode.type = new Type(ctx.typeName().getText());
        typeNode.type.dim = ctx.LBracket().size();
        return typeNode;
    }

    //@Override public T visitTypeName(MxParser.TypeNameContext ctx)

    //@Override public T visitBaseType(MxParser.BaseTypeContext ctx)

    @Override public ASTNode visitStatement(MxParser.StatementContext ctx) {
        if (ctx.breakStmt() != null)
            return visit(ctx.breakStmt());
        else if (ctx.continueStmt() != null)
            return visit(ctx.continueStmt());
        else if (ctx.exprStmt() != null)
            return visit(ctx.exprStmt());
        else if (ctx.forStmt() != null)
            return visit(ctx.forStmt());
        else if (ctx.ifStmt() != null)
            return visit(ctx.ifStmt());
        else if (ctx.returnStmt() != null)
            return visit(ctx.returnStmt());
        else if (ctx.suite() != null)
            return visit(ctx.suite());
        else if (ctx.varDef() != null)
            return visit(ctx.varDef());
        else if (ctx.whileStmt() != null)
            return visit(ctx.whileStmt());
        else
            return visitChildren(ctx);
    }

    @Override public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        IfStmtNode ifStmt = new IfStmtNode(new position(ctx), (ExprNode) visit(ctx.expr()));
        if (ctx.statement(0).suite() != null)
            ifStmt.thenStmts = ((SuiteNode) visit(ctx.statement(0).suite())).stmts;
        else
            ifStmt.thenStmts.add((StmtNode) visit(ctx.statement(0)));
        if (ctx.Else() != null) {
            if (ctx.statement(1).suite() != null)
                ifStmt.elseStmts = ((SuiteNode) visit(ctx.statement(1).suite())).stmts;
            else
                ifStmt.elseStmts.add((StmtNode) visit(ctx.statement(1)));
        }
        return ifStmt;
    }

    @Override public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        WhileStmtNode whileStmt = new WhileStmtNode(new position(ctx), (ExprNode) visit(ctx.expr()));
        if (ctx.statement().suite() != null)
            whileStmt.stmts = ((SuiteNode) visit(ctx.statement().suite())).stmts;
        else
            whileStmt.stmts.add((StmtNode) visit(ctx.statement()));
        return whileStmt;
    }

    @Override public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        ForStmtNode forStmt = new ForStmtNode(new position(ctx));
        if (ctx.forInit().varDef() != null)
            forStmt.varDef = (VarDefNode) visit(ctx.forInit().varDef());
        else
            forStmt.init = ((ExprStmtNode) visit(ctx.forInit().exprStmt())).exprNode;
        forStmt.condition = ((ExprStmtNode) visit(ctx.forInit().exprStmt())).exprNode;
        if (ctx.expr() != null)
            forStmt.step = (ExprNode) visit(ctx.expr());
        if (ctx.statement().suite() != null)
            forStmt.stmts = ((SuiteNode) visit(ctx.statement().suite())).stmts;
        else
            forStmt.stmts.add((StmtNode) visit(ctx.statement()));
        return forStmt;
    }

    //@Override public T visitForInit(MxParser.ForInitContext ctx)

    @Override public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        return new BreakNode(new position(ctx));
    }

    @Override public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        return new ContinueNode(new position(ctx));
    }

    @Override public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ReturnStmtNode ret = new ReturnStmtNode(new position(ctx));
        if (ctx.expr() != null) ret.expr = (ExprNode) visit(ctx.expr());
        return ret;
    }

    @Override public ASTNode visitExprStmt(MxParser.ExprStmtContext ctx) {
        ExprStmtNode exprS = new ExprStmtNode(new position(ctx));
        if (ctx.expr() != null) exprS.exprNode = (ExprNode) visit(ctx.expr());
        return exprS;
    }

    @Override public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        NewExprNode newExpr = new NewExprNode(new position(ctx), ctx.typeName().getText());
        newExpr.dim = ctx.newArrayUnit().size();
        boolean isEmpty = false;
        for (var x : ctx.newArrayUnit()) {
            if (x.expr() == null) {
                isEmpty = true;
            } else {
                if (isEmpty)
                    throw new syntaxError("Array dimension Empty", new position(ctx));
                else
                    newExpr.sizeList.add((ExprNode) visit(x.expr()));
            }
        }
        return newExpr;
    }

    @Override public ASTNode visitUnaryExpr(MxParser.UnaryExprContext ctx) {
        return new UnaryExprNode(new position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expr()));
    }

    @Override public ASTNode visitFuncExpr(MxParser.FuncExprContext ctx) {
        FuncExprNode funcExpr = new FuncExprNode(new position(ctx), (ExprNode) visit(ctx.expr()));
        if (ctx.exprList() != null) funcExpr.lists = (ExprListNode) visit(ctx.exprList());
        return funcExpr;
    }

    @Override public ASTNode visitArrayExpr(MxParser.ArrayExprContext ctx) {
        return new ArrayExprNode(new position(ctx), (ExprNode) visit(ctx.expr(0)), (ExprNode) visit(ctx.expr(1)));
    }
    @Override public ASTNode visitLambdaExpr(MxParser.LambdaExprContext ctx) {
        LambdaExprNode lambdaExpr = new LambdaExprNode(new position(ctx));
        lambdaExpr.isGlobe = (ctx.BAnd() != null);
        if (ctx.parameterList() != null)
            lambdaExpr.params = (ParameterListNode) visit(ctx.parameterList());
        lambdaExpr.stmts = ((SuiteNode) visit(ctx.suite())).stmts;
        if (ctx.exprList() != null)
            lambdaExpr.lists = (ExprListNode) visit(ctx.exprList());
        return lambdaExpr;
    }
    @Override public ASTNode visitMemberExpr(MxParser.MemberExprContext ctx) {
        MemberExprNode mem = new MemberExprNode(new position(ctx));
        mem.name = (ExprNode) visit(ctx.expr());
        mem.member = ctx.Identifier().getText();
        return mem;
    }
    @Override public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
        return visitChildren(ctx);
    }
    @Override public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        return new BinaryExprNode(new position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expr(0)), (ExprNode) visit(ctx.expr(1)));
    }
    @Override public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        return new AssignExprNode(new position(ctx), (ExprNode) visit(ctx.expr(0)), (ExprNode) visit(ctx.expr(1)));
    }

    @Override public ASTNode visitParenExpr(MxParser.ParenExprContext ctx) {
        return (ExprNode) visit(ctx.expr());
    }
    @Override public ASTNode visitPreAddExpr(MxParser.PreAddExprContext ctx) {
        return new PreAddExprNode(new position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expr()));
    }
    //@Override public ASTNode visitNewArrayUnit(MxParser.NewArrayUnitContext ctx)
    @Override public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.Identifier() != null) {
            return new VarExprNode(new position(ctx), ctx.getText());
        } else {
            AtomExprNode atom = new AtomExprNode(new position(ctx), ctx.getText());
            if (ctx.IntConst() != null) {
                atom.type = new Type("int");
            } else if (ctx.StringConst() != null) {
                atom.type = new Type("string");
            } else if (ctx.True() != null || ctx.False() != null) {
                atom.type = new Type("bool");
            } else if (ctx.Null() != null) {
                atom.type = new Type("null");
            } else if (ctx.This() != null) {
                atom.type = new Type("this");
            }
            return atom;
        }
    }

    @Override public ASTNode visitExprList(MxParser.ExprListContext ctx) {
        ExprListNode list = new ExprListNode(new position(ctx));
        ctx.expr().forEach(x -> list.exprs.add((ExprNode) visit(x)));
        return list;
    }

}