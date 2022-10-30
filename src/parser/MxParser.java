package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Add=1, Sub=2, Mul=3, Div=4, Mod=5, GThan=6, LThan=7, GEqual=8, LEqual=9, 
		NEqual=10, EEqual=11, LAnd=12, LOr=13, LNot=14, RShift=15, LShift=16, 
		BAnd=17, BOr=18, BXor=19, BNot=20, Assign=21, SelfAdd=22, SelfSub=23, 
		Member=24, LBracket=25, RBracket=26, LParen=27, RParen=28, Semi=29, Comma=30, 
		LBrace=31, RBrace=32, Quote=33, Arrow=34, Void=35, Bool=36, Int=37, String=38, 
		New=39, Class=40, Null=41, True=42, False=43, This=44, If=45, Else=46, 
		For=47, While=48, Break=49, Continue=50, Return=51, Identifier=52, IntConst=53, 
		StringConst=54, WhiteSpace=55, CommentLine=56, CommentPara=57;
	public static final int
		RULE_program = 0, RULE_funcDef = 1, RULE_returnType = 2, RULE_parameterList = 3, 
		RULE_suite = 4, RULE_classDef = 5, RULE_classBuild = 6, RULE_varDef = 7, 
		RULE_varDefUnit = 8, RULE_type = 9, RULE_typeName = 10, RULE_baseType = 11, 
		RULE_statement = 12, RULE_ifStmt = 13, RULE_whileStmt = 14, RULE_forStmt = 15, 
		RULE_forInit = 16, RULE_breakStmt = 17, RULE_continueStmt = 18, RULE_returnStmt = 19, 
		RULE_exprStmt = 20, RULE_expr = 21, RULE_newArrayUnit = 22, RULE_primary = 23, 
		RULE_exprList = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "funcDef", "returnType", "parameterList", "suite", "classDef", 
			"classBuild", "varDef", "varDefUnit", "type", "typeName", "baseType", 
			"statement", "ifStmt", "whileStmt", "forStmt", "forInit", "breakStmt", 
			"continueStmt", "returnStmt", "exprStmt", "expr", "newArrayUnit", "primary", 
			"exprList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'>='", "'<='", 
			"'!='", "'=='", "'&&'", "'||'", "'!'", "'>>'", "'<<'", "'&'", "'|'", 
			"'^'", "'~'", "'='", "'++'", "'--'", "'.'", "'['", "']'", "'('", "')'", 
			"';'", "','", "'{'", "'}'", "'\"'", "'->'", "'void'", "'bool'", "'int'", 
			"'string'", "'new'", "'class'", "'null'", "'true'", "'false'", "'this'", 
			"'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Add", "Sub", "Mul", "Div", "Mod", "GThan", "LThan", "GEqual", 
			"LEqual", "NEqual", "EEqual", "LAnd", "LOr", "LNot", "RShift", "LShift", 
			"BAnd", "BOr", "BXor", "BNot", "Assign", "SelfAdd", "SelfSub", "Member", 
			"LBracket", "RBracket", "LParen", "RParen", "Semi", "Comma", "LBrace", 
			"RBrace", "Quote", "Arrow", "Void", "Bool", "Int", "String", "New", "Class", 
			"Null", "True", "False", "This", "If", "Else", "For", "While", "Break", 
			"Continue", "Return", "Identifier", "IntConst", "StringConst", "WhiteSpace", 
			"CommentLine", "CommentPara"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Void) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(53);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(50);
					funcDef();
					}
					break;
				case 2:
					{
					setState(51);
					classDef();
					}
					break;
				case 3:
					{
					setState(52);
					varDef();
					}
					break;
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(58);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_funcDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			returnType();
			setState(61);
			match(Identifier);
			setState(62);
			match(LParen);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(63);
				parameterList();
				}
			}

			setState(66);
			match(RParen);
			setState(67);
			match(LBrace);
			setState(68);
			suite();
			setState(69);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Void() { return getToken(MxParser.Void, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_returnType);
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bool:
			case Int:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				type();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(Void);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(75);
			type();
			setState(76);
			match(Identifier);
			}
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(78);
				match(Comma);
				setState(79);
				type();
				setState(80);
				match(Identifier);
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LNot) | (1L << BNot) | (1L << SelfAdd) | (1L << SelfSub) | (1L << LBracket) | (1L << LParen) | (1L << Semi) | (1L << LBrace) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << Identifier) | (1L << IntConst) | (1L << StringConst))) != 0)) {
				{
				{
				setState(87);
				statement();
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<ClassBuildContext> classBuild() {
			return getRuleContexts(ClassBuildContext.class);
		}
		public ClassBuildContext classBuild(int i) {
			return getRuleContext(ClassBuildContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(Class);
			setState(94);
			match(Identifier);
			setState(95);
			match(LBrace);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Void) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(99);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(96);
					varDef();
					}
					break;
				case 2:
					{
					setState(97);
					classBuild();
					}
					break;
				case 3:
					{
					setState(98);
					funcDef();
					}
					break;
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
			match(RBrace);
			setState(105);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBuildContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public ClassBuildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBuild; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassBuild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassBuild(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassBuild(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBuildContext classBuild() throws RecognitionException {
		ClassBuildContext _localctx = new ClassBuildContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_classBuild);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(Identifier);
			setState(108);
			match(LParen);
			setState(109);
			match(RParen);
			setState(110);
			match(LBrace);
			setState(111);
			suite();
			setState(112);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<VarDefUnitContext> varDefUnit() {
			return getRuleContexts(VarDefUnitContext.class);
		}
		public VarDefUnitContext varDefUnit(int i) {
			return getRuleContext(VarDefUnitContext.class,i);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			type();
			setState(115);
			varDefUnit();
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(116);
				match(Comma);
				setState(117);
				varDefUnit();
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefUnitContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDefUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDefUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDefUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDefUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefUnitContext varDefUnit() throws RecognitionException {
		VarDefUnitContext _localctx = new VarDefUnitContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varDefUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(Identifier);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(126);
				match(Assign);
				setState(127);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<TerminalNode> LBracket() { return getTokens(MxParser.LBracket); }
		public TerminalNode LBracket(int i) {
			return getToken(MxParser.LBracket, i);
		}
		public List<TerminalNode> RBracket() { return getTokens(MxParser.RBracket); }
		public TerminalNode RBracket(int i) {
			return getToken(MxParser.RBracket, i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			typeName();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBracket) {
				{
				{
				setState(131);
				match(LBracket);
				setState(132);
				match(RBracket);
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNameContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeName);
		try {
			setState(140);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bool:
			case Int:
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				baseType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxParser.Bool, 0); }
		public TerminalNode String() { return getToken(MxParser.String, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBaseType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBaseType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public ContinueStmtContext continueStmt() {
			return getRuleContext(ContinueStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_statement);
		try {
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				match(LBrace);
				setState(145);
				suite();
				setState(146);
				match(RBrace);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				varDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(149);
				ifStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(150);
				whileStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(151);
				forStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(152);
				breakStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(153);
				continueStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(154);
				returnStmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(155);
				exprStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(If);
			setState(159);
			match(LParen);
			setState(160);
			expr(0);
			setState(161);
			match(RParen);
			setState(162);
			statement();
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(163);
				match(Else);
				setState(164);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStmtContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(While);
			setState(168);
			match(LParen);
			setState(169);
			expr(0);
			setState(170);
			match(RParen);
			setState(171);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStmtContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_forStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(For);
			setState(174);
			match(LParen);
			setState(175);
			forInit();
			setState(176);
			exprStmt();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LNot) | (1L << BNot) | (1L << SelfAdd) | (1L << SelfSub) | (1L << LBracket) | (1L << LParen) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntConst) | (1L << StringConst))) != 0)) {
				{
				setState(177);
				expr(0);
				}
			}

			setState(180);
			match(RParen);
			setState(181);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitContext extends ParserRuleContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_forInit);
		try {
			setState(185);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				varDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				exprStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStmtContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public BreakStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(Break);
			setState(188);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStmtContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ContinueStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(Continue);
			setState(191);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_returnStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(Return);
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LNot) | (1L << BNot) | (1L << SelfAdd) | (1L << SelfSub) | (1L << LBracket) | (1L << LParen) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntConst) | (1L << StringConst))) != 0)) {
				{
				setState(194);
				expr(0);
				}
			}

			setState(197);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprStmtContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprStmtContext exprStmt() throws RecognitionException {
		ExprStmtContext _localctx = new ExprStmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_exprStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LNot) | (1L << BNot) | (1L << SelfAdd) | (1L << SelfSub) | (1L << LBracket) | (1L << LParen) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntConst) | (1L << StringConst))) != 0)) {
				{
				setState(199);
				expr(0);
				}
			}

			setState(202);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExprContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<NewArrayUnitContext> newArrayUnit() {
			return getRuleContexts(NewArrayUnitContext.class);
		}
		public NewArrayUnitContext newArrayUnit(int i) {
			return getRuleContext(NewArrayUnitContext.class,i);
		}
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LNot() { return getToken(MxParser.LNot, 0); }
		public TerminalNode BNot() { return getToken(MxParser.BNot, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode SelfAdd() { return getToken(MxParser.SelfAdd, 0); }
		public TerminalNode SelfSub() { return getToken(MxParser.SelfSub, 0); }
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FuncExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LBracket() { return getToken(MxParser.LBracket, 0); }
		public TerminalNode RBracket() { return getToken(MxParser.RBracket, 0); }
		public ArrayExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArrayExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaExprContext extends ExprContext {
		public TerminalNode LBracket() { return getToken(MxParser.LBracket, 0); }
		public TerminalNode RBracket() { return getToken(MxParser.RBracket, 0); }
		public List<TerminalNode> LParen() { return getTokens(MxParser.LParen); }
		public TerminalNode LParen(int i) {
			return getToken(MxParser.LParen, i);
		}
		public List<TerminalNode> RParen() { return getTokens(MxParser.RParen); }
		public TerminalNode RParen(int i) {
			return getToken(MxParser.RParen, i);
		}
		public TerminalNode Arrow() { return getToken(MxParser.Arrow, 0); }
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public TerminalNode BAnd() { return getToken(MxParser.BAnd, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public LambdaExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLambdaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLambdaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLambdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Member() { return getToken(MxParser.Member, 0); }
		public MemberExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMemberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomExprContext extends ExprContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AtomExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxParser.Mod, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode LShift() { return getToken(MxParser.LShift, 0); }
		public TerminalNode RShift() { return getToken(MxParser.RShift, 0); }
		public TerminalNode LThan() { return getToken(MxParser.LThan, 0); }
		public TerminalNode GThan() { return getToken(MxParser.GThan, 0); }
		public TerminalNode LEqual() { return getToken(MxParser.LEqual, 0); }
		public TerminalNode GEqual() { return getToken(MxParser.GEqual, 0); }
		public TerminalNode EEqual() { return getToken(MxParser.EEqual, 0); }
		public TerminalNode NEqual() { return getToken(MxParser.NEqual, 0); }
		public TerminalNode BAnd() { return getToken(MxParser.BAnd, 0); }
		public TerminalNode BXor() { return getToken(MxParser.BXor, 0); }
		public TerminalNode BOr() { return getToken(MxParser.BOr, 0); }
		public TerminalNode LAnd() { return getToken(MxParser.LAnd, 0); }
		public TerminalNode LOr() { return getToken(MxParser.LOr, 0); }
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreAddExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SelfAdd() { return getToken(MxParser.SelfAdd, 0); }
		public TerminalNode SelfSub() { return getToken(MxParser.SelfSub, 0); }
		public PreAddExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPreAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPreAddExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPreAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LParen:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(205);
				match(LParen);
				setState(206);
				expr(0);
				setState(207);
				match(RParen);
				}
				break;
			case LBracket:
				{
				_localctx = new LambdaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(209);
				match(LBracket);
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BAnd) {
					{
					setState(210);
					match(BAnd);
					}
				}

				setState(213);
				match(RBracket);
				setState(214);
				match(LParen);
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Identifier))) != 0)) {
					{
					setState(215);
					parameterList();
					}
				}

				setState(218);
				match(RParen);
				setState(219);
				match(Arrow);
				setState(220);
				match(LBrace);
				setState(221);
				suite();
				setState(222);
				match(RBrace);
				setState(223);
				match(LParen);
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LNot) | (1L << BNot) | (1L << SelfAdd) | (1L << SelfSub) | (1L << LBracket) | (1L << LParen) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntConst) | (1L << StringConst))) != 0)) {
					{
					setState(224);
					exprList();
					}
				}

				setState(227);
				match(RParen);
				}
				break;
			case New:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(229);
				match(New);
				setState(230);
				typeName();
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(231);
						newArrayUnit();
						}
						} 
					}
					setState(236);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				setState(239);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(237);
					match(LParen);
					setState(238);
					match(RParen);
					}
					break;
				}
				}
				break;
			case SelfAdd:
			case SelfSub:
				{
				_localctx = new PreAddExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(241);
				((PreAddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SelfAdd || _la==SelfSub) ) {
					((PreAddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(242);
				expr(15);
				}
				break;
			case Add:
			case Sub:
			case LNot:
			case BNot:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(243);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LNot) | (1L << BNot))) != 0)) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(244);
				expr(13);
				}
				break;
			case Null:
			case True:
			case False:
			case This:
			case Identifier:
			case IntConst:
			case StringConst:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(245);
				primary();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(299);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(297);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(248);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(249);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(250);
						expr(13);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(251);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(252);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(253);
						expr(12);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(254);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(255);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==RShift || _la==LShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(256);
						expr(11);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(257);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(258);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GThan) | (1L << LThan) | (1L << GEqual) | (1L << LEqual))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(259);
						expr(10);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(260);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(261);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==NEqual || _la==EEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(262);
						expr(9);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(263);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(264);
						((BinaryExprContext)_localctx).op = match(BAnd);
						setState(265);
						expr(8);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(266);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(267);
						((BinaryExprContext)_localctx).op = match(BXor);
						setState(268);
						expr(7);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(269);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(270);
						((BinaryExprContext)_localctx).op = match(BOr);
						setState(271);
						expr(6);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(272);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(273);
						((BinaryExprContext)_localctx).op = match(LAnd);
						setState(274);
						expr(5);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(275);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(276);
						((BinaryExprContext)_localctx).op = match(LOr);
						setState(277);
						expr(4);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(278);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(279);
						((AssignExprContext)_localctx).op = match(Assign);
						setState(280);
						expr(2);
						}
						break;
					case 12:
						{
						_localctx = new MemberExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(281);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(282);
						((MemberExprContext)_localctx).op = match(Member);
						setState(283);
						match(Identifier);
						}
						break;
					case 13:
						{
						_localctx = new ArrayExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(284);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(285);
						match(LBracket);
						setState(286);
						expr(0);
						setState(287);
						match(RBracket);
						}
						break;
					case 14:
						{
						_localctx = new FuncExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(289);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(290);
						match(LParen);
						setState(292);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LNot) | (1L << BNot) | (1L << SelfAdd) | (1L << SelfSub) | (1L << LBracket) | (1L << LParen) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntConst) | (1L << StringConst))) != 0)) {
							{
							setState(291);
							exprList();
							}
						}

						setState(294);
						match(RParen);
						}
						break;
					case 15:
						{
						_localctx = new UnaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(295);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(296);
						((UnaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SelfAdd || _la==SelfSub) ) {
							((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(301);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NewArrayUnitContext extends ParserRuleContext {
		public TerminalNode LBracket() { return getToken(MxParser.LBracket, 0); }
		public TerminalNode RBracket() { return getToken(MxParser.RBracket, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NewArrayUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newArrayUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewArrayUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewArrayUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewArrayUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewArrayUnitContext newArrayUnit() throws RecognitionException {
		NewArrayUnitContext _localctx = new NewArrayUnitContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_newArrayUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(LBracket);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LNot) | (1L << BNot) | (1L << SelfAdd) | (1L << SelfSub) | (1L << LBracket) | (1L << LParen) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntConst) | (1L << StringConst))) != 0)) {
				{
				setState(303);
				expr(0);
				}
			}

			setState(306);
			match(RBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode IntConst() { return getToken(MxParser.IntConst, 0); }
		public TerminalNode StringConst() { return getToken(MxParser.StringConst, 0); }
		public TerminalNode True() { return getToken(MxParser.True, 0); }
		public TerminalNode False() { return getToken(MxParser.False, 0); }
		public TerminalNode Null() { return getToken(MxParser.Null, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_primary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntConst) | (1L << StringConst))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			expr(0);
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(311);
				match(Comma);
				setState(312);
				expr(0);
				}
				}
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 21:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 18);
		case 12:
			return precpred(_ctx, 17);
		case 13:
			return precpred(_ctx, 16);
		case 14:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00019\u013f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u00006\b\u0000\n\u0000\f\u0000"+
		"9\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001A\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003\u0002J\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003S\b\u0003\n\u0003\f\u0003V\t\u0003\u0001\u0004"+
		"\u0005\u0004Y\b\u0004\n\u0004\f\u0004\\\t\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005d\b\u0005"+
		"\n\u0005\f\u0005g\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007w\b\u0007"+
		"\n\u0007\f\u0007z\t\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u0081\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u0086\b\t\n\t\f\t"+
		"\u0089\t\t\u0001\n\u0001\n\u0003\n\u008d\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0003\f\u009d\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0003\r\u00a6\b\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u00b3\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u00ba\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u00c4\b\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0003\u0014\u00c9\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u00d4\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00d9\b"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0003\u0015\u00e2\b\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u00e9\b\u0015\n\u0015\f\u0015"+
		"\u00ec\t\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00f0\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00f7"+
		"\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0125\b\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0005\u0015\u012a\b\u0015\n\u0015\f\u0015\u012d\t\u0015"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u0131\b\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018"+
		"\u013a\b\u0018\n\u0018\f\u0018\u013d\t\u0018\u0001\u0018\u0000\u0001*"+
		"\u0019\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.0\u0000\t\u0001\u0000$&\u0001\u0000\u0016\u0017"+
		"\u0003\u0000\u0001\u0002\u000e\u000e\u0014\u0014\u0001\u0000\u0003\u0005"+
		"\u0001\u0000\u0001\u0002\u0001\u0000\u000f\u0010\u0001\u0000\u0006\t\u0001"+
		"\u0000\n\u000b\u0002\u0000),46\u015c\u00007\u0001\u0000\u0000\u0000\u0002"+
		"<\u0001\u0000\u0000\u0000\u0004I\u0001\u0000\u0000\u0000\u0006K\u0001"+
		"\u0000\u0000\u0000\bZ\u0001\u0000\u0000\u0000\n]\u0001\u0000\u0000\u0000"+
		"\fk\u0001\u0000\u0000\u0000\u000er\u0001\u0000\u0000\u0000\u0010}\u0001"+
		"\u0000\u0000\u0000\u0012\u0082\u0001\u0000\u0000\u0000\u0014\u008c\u0001"+
		"\u0000\u0000\u0000\u0016\u008e\u0001\u0000\u0000\u0000\u0018\u009c\u0001"+
		"\u0000\u0000\u0000\u001a\u009e\u0001\u0000\u0000\u0000\u001c\u00a7\u0001"+
		"\u0000\u0000\u0000\u001e\u00ad\u0001\u0000\u0000\u0000 \u00b9\u0001\u0000"+
		"\u0000\u0000\"\u00bb\u0001\u0000\u0000\u0000$\u00be\u0001\u0000\u0000"+
		"\u0000&\u00c1\u0001\u0000\u0000\u0000(\u00c8\u0001\u0000\u0000\u0000*"+
		"\u00f6\u0001\u0000\u0000\u0000,\u012e\u0001\u0000\u0000\u0000.\u0134\u0001"+
		"\u0000\u0000\u00000\u0136\u0001\u0000\u0000\u000026\u0003\u0002\u0001"+
		"\u000036\u0003\n\u0005\u000046\u0003\u000e\u0007\u000052\u0001\u0000\u0000"+
		"\u000053\u0001\u0000\u0000\u000054\u0001\u0000\u0000\u000069\u0001\u0000"+
		"\u0000\u000075\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u00008:\u0001"+
		"\u0000\u0000\u000097\u0001\u0000\u0000\u0000:;\u0005\u0000\u0000\u0001"+
		";\u0001\u0001\u0000\u0000\u0000<=\u0003\u0004\u0002\u0000=>\u00054\u0000"+
		"\u0000>@\u0005\u001b\u0000\u0000?A\u0003\u0006\u0003\u0000@?\u0001\u0000"+
		"\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0005"+
		"\u001c\u0000\u0000CD\u0005\u001f\u0000\u0000DE\u0003\b\u0004\u0000EF\u0005"+
		" \u0000\u0000F\u0003\u0001\u0000\u0000\u0000GJ\u0003\u0012\t\u0000HJ\u0005"+
		"#\u0000\u0000IG\u0001\u0000\u0000\u0000IH\u0001\u0000\u0000\u0000J\u0005"+
		"\u0001\u0000\u0000\u0000KL\u0003\u0012\t\u0000LM\u00054\u0000\u0000MT"+
		"\u0001\u0000\u0000\u0000NO\u0005\u001e\u0000\u0000OP\u0003\u0012\t\u0000"+
		"PQ\u00054\u0000\u0000QS\u0001\u0000\u0000\u0000RN\u0001\u0000\u0000\u0000"+
		"SV\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000"+
		"\u0000U\u0007\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000WY\u0003"+
		"\u0018\f\u0000XW\u0001\u0000\u0000\u0000Y\\\u0001\u0000\u0000\u0000ZX"+
		"\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\t\u0001\u0000\u0000"+
		"\u0000\\Z\u0001\u0000\u0000\u0000]^\u0005(\u0000\u0000^_\u00054\u0000"+
		"\u0000_e\u0005\u001f\u0000\u0000`d\u0003\u000e\u0007\u0000ad\u0003\f\u0006"+
		"\u0000bd\u0003\u0002\u0001\u0000c`\u0001\u0000\u0000\u0000ca\u0001\u0000"+
		"\u0000\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001"+
		"\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fh\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000hi\u0005 \u0000\u0000ij\u0005\u001d\u0000\u0000"+
		"j\u000b\u0001\u0000\u0000\u0000kl\u00054\u0000\u0000lm\u0005\u001b\u0000"+
		"\u0000mn\u0005\u001c\u0000\u0000no\u0005\u001f\u0000\u0000op\u0003\b\u0004"+
		"\u0000pq\u0005 \u0000\u0000q\r\u0001\u0000\u0000\u0000rs\u0003\u0012\t"+
		"\u0000sx\u0003\u0010\b\u0000tu\u0005\u001e\u0000\u0000uw\u0003\u0010\b"+
		"\u0000vt\u0001\u0000\u0000\u0000wz\u0001\u0000\u0000\u0000xv\u0001\u0000"+
		"\u0000\u0000xy\u0001\u0000\u0000\u0000y{\u0001\u0000\u0000\u0000zx\u0001"+
		"\u0000\u0000\u0000{|\u0005\u001d\u0000\u0000|\u000f\u0001\u0000\u0000"+
		"\u0000}\u0080\u00054\u0000\u0000~\u007f\u0005\u0015\u0000\u0000\u007f"+
		"\u0081\u0003*\u0015\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001"+
		"\u0000\u0000\u0000\u0081\u0011\u0001\u0000\u0000\u0000\u0082\u0087\u0003"+
		"\u0014\n\u0000\u0083\u0084\u0005\u0019\u0000\u0000\u0084\u0086\u0005\u001a"+
		"\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0086\u0089\u0001\u0000"+
		"\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000"+
		"\u0000\u0000\u0088\u0013\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000"+
		"\u0000\u0000\u008a\u008d\u0003\u0016\u000b\u0000\u008b\u008d\u00054\u0000"+
		"\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c\u008b\u0001\u0000\u0000"+
		"\u0000\u008d\u0015\u0001\u0000\u0000\u0000\u008e\u008f\u0007\u0000\u0000"+
		"\u0000\u008f\u0017\u0001\u0000\u0000\u0000\u0090\u0091\u0005\u001f\u0000"+
		"\u0000\u0091\u0092\u0003\b\u0004\u0000\u0092\u0093\u0005 \u0000\u0000"+
		"\u0093\u009d\u0001\u0000\u0000\u0000\u0094\u009d\u0003\u000e\u0007\u0000"+
		"\u0095\u009d\u0003\u001a\r\u0000\u0096\u009d\u0003\u001c\u000e\u0000\u0097"+
		"\u009d\u0003\u001e\u000f\u0000\u0098\u009d\u0003\"\u0011\u0000\u0099\u009d"+
		"\u0003$\u0012\u0000\u009a\u009d\u0003&\u0013\u0000\u009b\u009d\u0003("+
		"\u0014\u0000\u009c\u0090\u0001\u0000\u0000\u0000\u009c\u0094\u0001\u0000"+
		"\u0000\u0000\u009c\u0095\u0001\u0000\u0000\u0000\u009c\u0096\u0001\u0000"+
		"\u0000\u0000\u009c\u0097\u0001\u0000\u0000\u0000\u009c\u0098\u0001\u0000"+
		"\u0000\u0000\u009c\u0099\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000"+
		"\u0000\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u0019\u0001\u0000"+
		"\u0000\u0000\u009e\u009f\u0005-\u0000\u0000\u009f\u00a0\u0005\u001b\u0000"+
		"\u0000\u00a0\u00a1\u0003*\u0015\u0000\u00a1\u00a2\u0005\u001c\u0000\u0000"+
		"\u00a2\u00a5\u0003\u0018\f\u0000\u00a3\u00a4\u0005.\u0000\u0000\u00a4"+
		"\u00a6\u0003\u0018\f\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6"+
		"\u0001\u0000\u0000\u0000\u00a6\u001b\u0001\u0000\u0000\u0000\u00a7\u00a8"+
		"\u00050\u0000\u0000\u00a8\u00a9\u0005\u001b\u0000\u0000\u00a9\u00aa\u0003"+
		"*\u0015\u0000\u00aa\u00ab\u0005\u001c\u0000\u0000\u00ab\u00ac\u0003\u0018"+
		"\f\u0000\u00ac\u001d\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005/\u0000"+
		"\u0000\u00ae\u00af\u0005\u001b\u0000\u0000\u00af\u00b0\u0003 \u0010\u0000"+
		"\u00b0\u00b2\u0003(\u0014\u0000\u00b1\u00b3\u0003*\u0015\u0000\u00b2\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005\u001c\u0000\u0000\u00b5\u00b6"+
		"\u0003\u0018\f\u0000\u00b6\u001f\u0001\u0000\u0000\u0000\u00b7\u00ba\u0003"+
		"\u000e\u0007\u0000\u00b8\u00ba\u0003(\u0014\u0000\u00b9\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000\u00ba!\u0001\u0000\u0000"+
		"\u0000\u00bb\u00bc\u00051\u0000\u0000\u00bc\u00bd\u0005\u001d\u0000\u0000"+
		"\u00bd#\u0001\u0000\u0000\u0000\u00be\u00bf\u00052\u0000\u0000\u00bf\u00c0"+
		"\u0005\u001d\u0000\u0000\u00c0%\u0001\u0000\u0000\u0000\u00c1\u00c3\u0005"+
		"3\u0000\u0000\u00c2\u00c4\u0003*\u0015\u0000\u00c3\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c6\u0005\u001d\u0000\u0000\u00c6\'\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c9\u0003*\u0015\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c9\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0005\u001d\u0000\u0000\u00cb)\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0006\u0015\uffff\uffff\u0000\u00cd\u00ce\u0005\u001b\u0000\u0000\u00ce"+
		"\u00cf\u0003*\u0015\u0000\u00cf\u00d0\u0005\u001c\u0000\u0000\u00d0\u00f7"+
		"\u0001\u0000\u0000\u0000\u00d1\u00d3\u0005\u0019\u0000\u0000\u00d2\u00d4"+
		"\u0005\u0011\u0000\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d3\u00d4"+
		"\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d6"+
		"\u0005\u001a\u0000\u0000\u00d6\u00d8\u0005\u001b\u0000\u0000\u00d7\u00d9"+
		"\u0003\u0006\u0003\u0000\u00d8\u00d7\u0001\u0000\u0000\u0000\u00d8\u00d9"+
		"\u0001\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000\u0000\u00da\u00db"+
		"\u0005\u001c\u0000\u0000\u00db\u00dc\u0005\"\u0000\u0000\u00dc\u00dd\u0005"+
		"\u001f\u0000\u0000\u00dd\u00de\u0003\b\u0004\u0000\u00de\u00df\u0005 "+
		"\u0000\u0000\u00df\u00e1\u0005\u001b\u0000\u0000\u00e0\u00e2\u00030\u0018"+
		"\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0005\u001c\u0000"+
		"\u0000\u00e4\u00f7\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\'\u0000\u0000"+
		"\u00e6\u00ea\u0003\u0014\n\u0000\u00e7\u00e9\u0003,\u0016\u0000\u00e8"+
		"\u00e7\u0001\u0000\u0000\u0000\u00e9\u00ec\u0001\u0000\u0000\u0000\u00ea"+
		"\u00e8\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb"+
		"\u00ef\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001\u0000\u0000\u0000\u00ed"+
		"\u00ee\u0005\u001b\u0000\u0000\u00ee\u00f0\u0005\u001c\u0000\u0000\u00ef"+
		"\u00ed\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0"+
		"\u00f7\u0001\u0000\u0000\u0000\u00f1\u00f2\u0007\u0001\u0000\u0000\u00f2"+
		"\u00f7\u0003*\u0015\u000f\u00f3\u00f4\u0007\u0002\u0000\u0000\u00f4\u00f7"+
		"\u0003*\u0015\r\u00f5\u00f7\u0003.\u0017\u0000\u00f6\u00cc\u0001\u0000"+
		"\u0000\u0000\u00f6\u00d1\u0001\u0000\u0000\u0000\u00f6\u00e5\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f1\u0001\u0000\u0000\u0000\u00f6\u00f3\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f5\u0001\u0000\u0000\u0000\u00f7\u012b\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f9\n\f\u0000\u0000\u00f9\u00fa\u0007\u0003\u0000"+
		"\u0000\u00fa\u012a\u0003*\u0015\r\u00fb\u00fc\n\u000b\u0000\u0000\u00fc"+
		"\u00fd\u0007\u0004\u0000\u0000\u00fd\u012a\u0003*\u0015\f\u00fe\u00ff"+
		"\n\n\u0000\u0000\u00ff\u0100\u0007\u0005\u0000\u0000\u0100\u012a\u0003"+
		"*\u0015\u000b\u0101\u0102\n\t\u0000\u0000\u0102\u0103\u0007\u0006\u0000"+
		"\u0000\u0103\u012a\u0003*\u0015\n\u0104\u0105\n\b\u0000\u0000\u0105\u0106"+
		"\u0007\u0007\u0000\u0000\u0106\u012a\u0003*\u0015\t\u0107\u0108\n\u0007"+
		"\u0000\u0000\u0108\u0109\u0005\u0011\u0000\u0000\u0109\u012a\u0003*\u0015"+
		"\b\u010a\u010b\n\u0006\u0000\u0000\u010b\u010c\u0005\u0013\u0000\u0000"+
		"\u010c\u012a\u0003*\u0015\u0007\u010d\u010e\n\u0005\u0000\u0000\u010e"+
		"\u010f\u0005\u0012\u0000\u0000\u010f\u012a\u0003*\u0015\u0006\u0110\u0111"+
		"\n\u0004\u0000\u0000\u0111\u0112\u0005\f\u0000\u0000\u0112\u012a\u0003"+
		"*\u0015\u0005\u0113\u0114\n\u0003\u0000\u0000\u0114\u0115\u0005\r\u0000"+
		"\u0000\u0115\u012a\u0003*\u0015\u0004\u0116\u0117\n\u0002\u0000\u0000"+
		"\u0117\u0118\u0005\u0015\u0000\u0000\u0118\u012a\u0003*\u0015\u0002\u0119"+
		"\u011a\n\u0012\u0000\u0000\u011a\u011b\u0005\u0018\u0000\u0000\u011b\u012a"+
		"\u00054\u0000\u0000\u011c\u011d\n\u0011\u0000\u0000\u011d\u011e\u0005"+
		"\u0019\u0000\u0000\u011e\u011f\u0003*\u0015\u0000\u011f\u0120\u0005\u001a"+
		"\u0000\u0000\u0120\u012a\u0001\u0000\u0000\u0000\u0121\u0122\n\u0010\u0000"+
		"\u0000\u0122\u0124\u0005\u001b\u0000\u0000\u0123\u0125\u00030\u0018\u0000"+
		"\u0124\u0123\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u012a\u0005\u001c\u0000\u0000"+
		"\u0127\u0128\n\u000e\u0000\u0000\u0128\u012a\u0007\u0001\u0000\u0000\u0129"+
		"\u00f8\u0001\u0000\u0000\u0000\u0129\u00fb\u0001\u0000\u0000\u0000\u0129"+
		"\u00fe\u0001\u0000\u0000\u0000\u0129\u0101\u0001\u0000\u0000\u0000\u0129"+
		"\u0104\u0001\u0000\u0000\u0000\u0129\u0107\u0001\u0000\u0000\u0000\u0129"+
		"\u010a\u0001\u0000\u0000\u0000\u0129\u010d\u0001\u0000\u0000\u0000\u0129"+
		"\u0110\u0001\u0000\u0000\u0000\u0129\u0113\u0001\u0000\u0000\u0000\u0129"+
		"\u0116\u0001\u0000\u0000\u0000\u0129\u0119\u0001\u0000\u0000\u0000\u0129"+
		"\u011c\u0001\u0000\u0000\u0000\u0129\u0121\u0001\u0000\u0000\u0000\u0129"+
		"\u0127\u0001\u0000\u0000\u0000\u012a\u012d\u0001\u0000\u0000\u0000\u012b"+
		"\u0129\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000\u012c"+
		"+\u0001\u0000\u0000\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012e\u0130"+
		"\u0005\u0019\u0000\u0000\u012f\u0131\u0003*\u0015\u0000\u0130\u012f\u0001"+
		"\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000\u0131\u0132\u0001"+
		"\u0000\u0000\u0000\u0132\u0133\u0005\u001a\u0000\u0000\u0133-\u0001\u0000"+
		"\u0000\u0000\u0134\u0135\u0007\b\u0000\u0000\u0135/\u0001\u0000\u0000"+
		"\u0000\u0136\u013b\u0003*\u0015\u0000\u0137\u0138\u0005\u001e\u0000\u0000"+
		"\u0138\u013a\u0003*\u0015\u0000\u0139\u0137\u0001\u0000\u0000\u0000\u013a"+
		"\u013d\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013b"+
		"\u013c\u0001\u0000\u0000\u0000\u013c1\u0001\u0000\u0000\u0000\u013d\u013b"+
		"\u0001\u0000\u0000\u0000\u001d57@ITZcex\u0080\u0087\u008c\u009c\u00a5"+
		"\u00b2\u00b9\u00c3\u00c8\u00d3\u00d8\u00e1\u00ea\u00ef\u00f6\u0124\u0129"+
		"\u012b\u0130\u013b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}