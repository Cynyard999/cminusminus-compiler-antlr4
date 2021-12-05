// Generated from /Users/cynyard/Documents/大学学习/大四上/编译原理-助教版/ANTLR-Labs/src/main/java/L3/CmmParser.g4 by ANTLR 4.9.2
package L3;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CmmParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Whitespace=1, Newline=2, BlockComment=3, LineComment=4, DO=5, WHILE=6, 
		IF=7, ELSE=8, RETURN=9, STRUCT=10, TYPE=11, LP=12, RP=13, LB=14, RB=15, 
		LC=16, RC=17, RELOP=18, PLUS=19, MINUS=20, STAR=21, DIV=22, AND=23, OR=24, 
		NOT=25, SEMI=26, COMMA=27, ASSIGNOP=28, DOT=29, ID=30, INT=31, FLOAT=32;
	public static final int
		RULE_program = 0, RULE_extDef = 1, RULE_extDecList = 2, RULE_specifier = 3, 
		RULE_structSpecifier = 4, RULE_optTag = 5, RULE_tag = 6, RULE_varDec = 7, 
		RULE_funDec = 8, RULE_varList = 9, RULE_paramDec = 10, RULE_compSt = 11, 
		RULE_stmtList = 12, RULE_stmt = 13, RULE_defList = 14, RULE_def = 15, 
		RULE_decList = 16, RULE_dec = 17, RULE_exp = 18, RULE_args = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "extDef", "extDecList", "specifier", "structSpecifier", "optTag", 
			"tag", "varDec", "funDec", "varList", "paramDec", "compSt", "stmtList", 
			"stmt", "defList", "def", "decList", "dec", "exp", "args"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'do'", "'while'", "'if'", "'else'", "'return'", 
			"'struct'", null, "'('", "')'", "'['", "']'", "'{'", "'}'", null, "'+'", 
			"'-'", "'*'", "'/'", "'&&'", "'||'", "'!'", "';'", "','", "'='", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Whitespace", "Newline", "BlockComment", "LineComment", "DO", "WHILE", 
			"IF", "ELSE", "RETURN", "STRUCT", "TYPE", "LP", "RP", "LB", "RB", "LC", 
			"RC", "RELOP", "PLUS", "MINUS", "STAR", "DIV", "AND", "OR", "NOT", "SEMI", 
			"COMMA", "ASSIGNOP", "DOT", "ID", "INT", "FLOAT"
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
	public String getGrammarFileName() { return "CmmParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CmmParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CmmParser.EOF, 0); }
		public List<ExtDefContext> extDef() {
			return getRuleContexts(ExtDefContext.class);
		}
		public ExtDefContext extDef(int i) {
			return getRuleContext(ExtDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitProgram(this);
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
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRUCT || _la==TYPE) {
				{
				{
				setState(40);
				extDef();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
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

	public static class ExtDefContext extends ParserRuleContext {
		public SpecifierContext specifier() {
			return getRuleContext(SpecifierContext.class,0);
		}
		public ExtDecListContext extDecList() {
			return getRuleContext(ExtDecListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CmmParser.SEMI, 0); }
		public FunDecContext funDec() {
			return getRuleContext(FunDecContext.class,0);
		}
		public CompStContext compSt() {
			return getRuleContext(CompStContext.class,0);
		}
		public ExtDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExtDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExtDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExtDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtDefContext extDef() throws RecognitionException {
		ExtDefContext _localctx = new ExtDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_extDef);
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				specifier();
				setState(49);
				extDecList();
				setState(50);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				specifier();
				setState(53);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				specifier();
				setState(56);
				funDec();
				setState(57);
				compSt();
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

	public static class ExtDecListContext extends ParserRuleContext {
		public List<VarDecContext> varDec() {
			return getRuleContexts(VarDecContext.class);
		}
		public VarDecContext varDec(int i) {
			return getRuleContext(VarDecContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CmmParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CmmParser.COMMA, i);
		}
		public ExtDecListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extDecList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExtDecList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExtDecList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExtDecList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtDecListContext extDecList() throws RecognitionException {
		ExtDecListContext _localctx = new ExtDecListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_extDecList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			varDec(false);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(62);
				match(COMMA);
				setState(63);
				varDec(false);
				}
				}
				setState(68);
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

	public static class SpecifierContext extends ParserRuleContext {
		public StructSpecifierContext structSpecifier() {
			return getRuleContext(StructSpecifierContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(CmmParser.TYPE, 0); }
		public SpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitSpecifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitSpecifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecifierContext specifier() throws RecognitionException {
		SpecifierContext _localctx = new SpecifierContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_specifier);
		try {
			setState(71);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRUCT:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				structSpecifier();
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(TYPE);
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

	public static class StructSpecifierContext extends ParserRuleContext {
		public TerminalNode STRUCT() { return getToken(CmmParser.STRUCT, 0); }
		public OptTagContext optTag() {
			return getRuleContext(OptTagContext.class,0);
		}
		public TerminalNode LC() { return getToken(CmmParser.LC, 0); }
		public DefListContext defList() {
			return getRuleContext(DefListContext.class,0);
		}
		public TerminalNode RC() { return getToken(CmmParser.RC, 0); }
		public TagContext tag() {
			return getRuleContext(TagContext.class,0);
		}
		public StructSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structSpecifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStructSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStructSpecifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStructSpecifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructSpecifierContext structSpecifier() throws RecognitionException {
		StructSpecifierContext _localctx = new StructSpecifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_structSpecifier);
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				match(STRUCT);
				setState(74);
				optTag();
				setState(75);
				match(LC);
				setState(76);
				defList(true);
				setState(77);
				match(RC);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				match(STRUCT);
				setState(80);
				tag();
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

	public static class OptTagContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public OptTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterOptTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitOptTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitOptTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptTagContext optTag() throws RecognitionException {
		OptTagContext _localctx = new OptTagContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_optTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(83);
				match(ID);
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

	public static class TagContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public TagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagContext tag() throws RecognitionException {
		TagContext _localctx = new TagContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(ID);
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

	public static class VarDecContext extends ParserRuleContext {
		public boolean inStruct;
		public Token errorToken;
		public List<TerminalNode> ID() { return getTokens(CmmParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CmmParser.ID, i);
		}
		public List<TerminalNode> LB() { return getTokens(CmmParser.LB); }
		public TerminalNode LB(int i) {
			return getToken(CmmParser.LB, i);
		}
		public List<TerminalNode> RB() { return getTokens(CmmParser.RB); }
		public TerminalNode RB(int i) {
			return getToken(CmmParser.RB, i);
		}
		public List<TerminalNode> INT() { return getTokens(CmmParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(CmmParser.INT, i);
		}
		public List<TerminalNode> FLOAT() { return getTokens(CmmParser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(CmmParser.FLOAT, i);
		}
		public VarDecContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public VarDecContext(ParserRuleContext parent, int invokingState, boolean inStruct) {
			super(parent, invokingState);
			this.inStruct = inStruct;
		}
		@Override public int getRuleIndex() { return RULE_varDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterVarDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitVarDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitVarDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDecContext varDec(boolean inStruct) throws RecognitionException {
		VarDecContext _localctx = new VarDecContext(_ctx, getState(), inStruct);
		enterRule(_localctx, 14, RULE_varDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(ID);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LB) {
				{
				{
				setState(89);
				match(LB);
				setState(96);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INT:
					{
					setState(90);
					match(INT);
					}
					break;
				case ID:
				case FLOAT:
					{
					setState(93);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case FLOAT:
						{
						setState(91);
						((VarDecContext)_localctx).errorToken = match(FLOAT);
						}
						break;
					case ID:
						{
						setState(92);
						((VarDecContext)_localctx).errorToken = match(ID);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					notifyErrorListeners(((VarDecContext)_localctx).errorToken, "array size must be an integer constant, not "+((VarDecContext)_localctx).errorToken.getText(), null);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(98);
				match(RB);
				}
				}
				setState(103);
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

	public static class FunDecContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public TerminalNode LP() { return getToken(CmmParser.LP, 0); }
		public VarListContext varList() {
			return getRuleContext(VarListContext.class,0);
		}
		public TerminalNode RP() { return getToken(CmmParser.RP, 0); }
		public FunDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterFunDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitFunDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitFunDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDecContext funDec() throws RecognitionException {
		FunDecContext _localctx = new FunDecContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funDec);
		try {
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(ID);
				setState(105);
				match(LP);
				setState(106);
				varList();
				setState(107);
				match(RP);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(ID);
				setState(110);
				match(LP);
				setState(111);
				match(RP);
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

	public static class VarListContext extends ParserRuleContext {
		public List<ParamDecContext> paramDec() {
			return getRuleContexts(ParamDecContext.class);
		}
		public ParamDecContext paramDec(int i) {
			return getRuleContext(ParamDecContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CmmParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CmmParser.COMMA, i);
		}
		public VarListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterVarList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitVarList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitVarList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarListContext varList() throws RecognitionException {
		VarListContext _localctx = new VarListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			paramDec();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(115);
				match(COMMA);
				setState(116);
				paramDec();
				}
				}
				setState(121);
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

	public static class ParamDecContext extends ParserRuleContext {
		public SpecifierContext specifier() {
			return getRuleContext(SpecifierContext.class,0);
		}
		public VarDecContext varDec() {
			return getRuleContext(VarDecContext.class,0);
		}
		public ParamDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterParamDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitParamDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitParamDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamDecContext paramDec() throws RecognitionException {
		ParamDecContext _localctx = new ParamDecContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_paramDec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			specifier();
			setState(123);
			varDec(false);
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

	public static class CompStContext extends ParserRuleContext {
		public TerminalNode LC() { return getToken(CmmParser.LC, 0); }
		public DefListContext defList() {
			return getRuleContext(DefListContext.class,0);
		}
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public TerminalNode RC() { return getToken(CmmParser.RC, 0); }
		public CompStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterCompSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitCompSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitCompSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompStContext compSt() throws RecognitionException {
		CompStContext _localctx = new CompStContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_compSt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(LC);
			setState(126);
			defList(false);
			setState(127);
			stmtList();
			setState(128);
			match(RC);
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

	public static class StmtListContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StmtListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStmtList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStmtList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStmtList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtListContext stmtList() throws RecognitionException {
		StmtListContext _localctx = new StmtListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stmtList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHILE) | (1L << IF) | (1L << RETURN) | (1L << LP) | (1L << LC) | (1L << MINUS) | (1L << NOT) | (1L << ID) | (1L << INT) | (1L << FLOAT))) != 0)) {
				{
				{
				setState(130);
				stmt();
				}
				}
				setState(135);
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

	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StmtExpContext extends StmtContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CmmParser.SEMI, 0); }
		public StmtExpContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStmtExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStmtExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStmtExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtWhileContext extends StmtContext {
		public TerminalNode WHILE() { return getToken(CmmParser.WHILE, 0); }
		public TerminalNode LP() { return getToken(CmmParser.LP, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RP() { return getToken(CmmParser.RP, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public StmtWhileContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStmtWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStmtWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStmtWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtIfContext extends StmtContext {
		public TerminalNode IF() { return getToken(CmmParser.IF, 0); }
		public TerminalNode LP() { return getToken(CmmParser.LP, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RP() { return getToken(CmmParser.RP, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public StmtIfContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStmtIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStmtIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStmtIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtIfElseContext extends StmtContext {
		public TerminalNode IF() { return getToken(CmmParser.IF, 0); }
		public TerminalNode LP() { return getToken(CmmParser.LP, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RP() { return getToken(CmmParser.RP, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(CmmParser.ELSE, 0); }
		public StmtIfElseContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStmtIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStmtIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStmtIfElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtReturnContext extends StmtContext {
		public TerminalNode RETURN() { return getToken(CmmParser.RETURN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CmmParser.SEMI, 0); }
		public StmtReturnContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStmtReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStmtReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStmtReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtCompStContext extends StmtContext {
		public CompStContext compSt() {
			return getRuleContext(CompStContext.class,0);
		}
		public StmtCompStContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStmtCompSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStmtCompSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStmtCompSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stmt);
		try {
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new StmtExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				exp(0);
				setState(137);
				match(SEMI);
				}
				break;
			case 2:
				_localctx = new StmtCompStContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				compSt();
				}
				break;
			case 3:
				_localctx = new StmtReturnContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				match(RETURN);
				setState(141);
				exp(0);
				setState(142);
				match(SEMI);
				}
				break;
			case 4:
				_localctx = new StmtIfContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				match(IF);
				setState(145);
				match(LP);
				setState(146);
				exp(0);
				setState(147);
				match(RP);
				setState(148);
				stmt();
				}
				break;
			case 5:
				_localctx = new StmtIfElseContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				match(IF);
				setState(151);
				match(LP);
				setState(152);
				exp(0);
				setState(153);
				match(RP);
				setState(154);
				stmt();
				setState(155);
				match(ELSE);
				setState(156);
				stmt();
				}
				break;
			case 6:
				_localctx = new StmtWhileContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(158);
				match(WHILE);
				setState(159);
				match(LP);
				setState(160);
				exp(0);
				setState(161);
				match(RP);
				setState(162);
				stmt();
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

	public static class DefListContext extends ParserRuleContext {
		public boolean inStruct;
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public DefListContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public DefListContext(ParserRuleContext parent, int invokingState, boolean inStruct) {
			super(parent, invokingState);
			this.inStruct = inStruct;
		}
		@Override public int getRuleIndex() { return RULE_defList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterDefList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitDefList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitDefList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefListContext defList(boolean inStruct) throws RecognitionException {
		DefListContext _localctx = new DefListContext(_ctx, getState(), inStruct);
		enterRule(_localctx, 28, RULE_defList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRUCT || _la==TYPE) {
				{
				{
				setState(166);
				def(_localctx.inStruct);
				}
				}
				setState(171);
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

	public static class DefContext extends ParserRuleContext {
		public boolean inStruct;
		public SpecifierContext specifier() {
			return getRuleContext(SpecifierContext.class,0);
		}
		public DecListContext decList() {
			return getRuleContext(DecListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CmmParser.SEMI, 0); }
		public DefContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public DefContext(ParserRuleContext parent, int invokingState, boolean inStruct) {
			super(parent, invokingState);
			this.inStruct = inStruct;
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def(boolean inStruct) throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState(), inStruct);
		enterRule(_localctx, 30, RULE_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			specifier();
			setState(173);
			decList(_localctx.inStruct);
			setState(174);
			match(SEMI);
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

	public static class DecListContext extends ParserRuleContext {
		public boolean inStruct;
		public List<DecContext> dec() {
			return getRuleContexts(DecContext.class);
		}
		public DecContext dec(int i) {
			return getRuleContext(DecContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CmmParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CmmParser.COMMA, i);
		}
		public DecListContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public DecListContext(ParserRuleContext parent, int invokingState, boolean inStruct) {
			super(parent, invokingState);
			this.inStruct = inStruct;
		}
		@Override public int getRuleIndex() { return RULE_decList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterDecList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitDecList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitDecList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecListContext decList(boolean inStruct) throws RecognitionException {
		DecListContext _localctx = new DecListContext(_ctx, getState(), inStruct);
		enterRule(_localctx, 32, RULE_decList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			dec(_localctx.inStruct);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(177);
				match(COMMA);
				setState(178);
				dec(_localctx.inStruct);
				}
				}
				setState(183);
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

	public static class DecContext extends ParserRuleContext {
		public boolean inStruct;
		public VarDecContext varDec() {
			return getRuleContext(VarDecContext.class,0);
		}
		public TerminalNode ASSIGNOP() { return getToken(CmmParser.ASSIGNOP, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public DecContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public DecContext(ParserRuleContext parent, int invokingState, boolean inStruct) {
			super(parent, invokingState);
			this.inStruct = inStruct;
		}
		@Override public int getRuleIndex() { return RULE_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecContext dec(boolean inStruct) throws RecognitionException {
		DecContext _localctx = new DecContext(_ctx, getState(), inStruct);
		enterRule(_localctx, 34, RULE_dec);
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				varDec(_localctx.inStruct);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				varDec(_localctx.inStruct);
				setState(186);
				match(ASSIGNOP);
				setState(187);
				exp(0);
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

	public static class ExpContext extends ParserRuleContext {
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	 
		public ExpContext() { }
		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpDotContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode DOT() { return getToken(CmmParser.DOT, 0); }
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public ExpDotContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpDot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpDot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpDot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpOrContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode OR() { return getToken(CmmParser.OR, 0); }
		public ExpOrContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpRelopContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode RELOP() { return getToken(CmmParser.RELOP, 0); }
		public ExpRelopContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpRelop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpRelop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpRelop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpPlusMinusContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(CmmParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CmmParser.MINUS, 0); }
		public ExpPlusMinusContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpPlusMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpPlusMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpPlusMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpAssignopContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode ASSIGNOP() { return getToken(CmmParser.ASSIGNOP, 0); }
		public ExpAssignopContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpAssignop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpAssignop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpAssignop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpParenthesisContext extends ExpContext {
		public TerminalNode LP() { return getToken(CmmParser.LP, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RP() { return getToken(CmmParser.RP, 0); }
		public ExpParenthesisContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpFloatContext extends ExpContext {
		public TerminalNode FLOAT() { return getToken(CmmParser.FLOAT, 0); }
		public ExpFloatContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpAndContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode AND() { return getToken(CmmParser.AND, 0); }
		public ExpAndContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpFuncArgsContext extends ExpContext {
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public TerminalNode LP() { return getToken(CmmParser.LP, 0); }
		public TerminalNode RP() { return getToken(CmmParser.RP, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExpFuncArgsContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpFuncArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpFuncArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpFuncArgs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpStarDivContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode STAR() { return getToken(CmmParser.STAR, 0); }
		public TerminalNode DIV() { return getToken(CmmParser.DIV, 0); }
		public ExpStarDivContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpStarDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpStarDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpStarDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpIntContext extends ExpContext {
		public TerminalNode INT() { return getToken(CmmParser.INT, 0); }
		public ExpIntContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpMinusNotContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(CmmParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(CmmParser.NOT, 0); }
		public ExpMinusNotContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpMinusNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpMinusNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpMinusNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpIdContext extends ExpContext {
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public ExpIdContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpBracketsContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode LB() { return getToken(CmmParser.LB, 0); }
		public TerminalNode RB() { return getToken(CmmParser.RB, 0); }
		public ExpBracketsContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExpBrackets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExpBrackets(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExpBrackets(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				_localctx = new ExpFuncArgsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(192);
				match(ID);
				setState(193);
				match(LP);
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LP) | (1L << MINUS) | (1L << NOT) | (1L << ID) | (1L << INT) | (1L << FLOAT))) != 0)) {
					{
					setState(194);
					args();
					}
				}

				setState(197);
				match(RP);
				}
				break;
			case 2:
				{
				_localctx = new ExpParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(198);
				match(LP);
				setState(199);
				exp(0);
				setState(200);
				match(RP);
				}
				break;
			case 3:
				{
				_localctx = new ExpMinusNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(202);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(203);
				exp(10);
				}
				break;
			case 4:
				{
				_localctx = new ExpIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(204);
				match(ID);
				}
				break;
			case 5:
				{
				_localctx = new ExpIntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(205);
				match(INT);
				}
				break;
			case 6:
				{
				_localctx = new ExpFloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(206);
				match(FLOAT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(237);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(235);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new ExpStarDivContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(209);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(210);
						_la = _input.LA(1);
						if ( !(_la==STAR || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(211);
						exp(10);
						}
						break;
					case 2:
						{
						_localctx = new ExpPlusMinusContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(212);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(213);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(214);
						exp(9);
						}
						break;
					case 3:
						{
						_localctx = new ExpRelopContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(215);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(216);
						match(RELOP);
						setState(217);
						exp(8);
						}
						break;
					case 4:
						{
						_localctx = new ExpAndContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(218);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(219);
						match(AND);
						setState(220);
						exp(7);
						}
						break;
					case 5:
						{
						_localctx = new ExpOrContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(221);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(222);
						match(OR);
						setState(223);
						exp(6);
						}
						break;
					case 6:
						{
						_localctx = new ExpAssignopContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(224);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(225);
						match(ASSIGNOP);
						setState(226);
						exp(4);
						}
						break;
					case 7:
						{
						_localctx = new ExpBracketsContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(227);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(228);
						match(LB);
						setState(229);
						exp(0);
						setState(230);
						match(RB);
						}
						break;
					case 8:
						{
						_localctx = new ExpDotContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(232);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(233);
						match(DOT);
						setState(234);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(239);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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

	public static class ArgsContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CmmParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CmmParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			exp(0);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(241);
				match(COMMA);
				setState(242);
				exp(0);
				}
				}
				setState(247);
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
		case 18:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 12);
		case 7:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\u00fb\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3>\n\3\3\4\3\4\3\4\7\4C\n\4"+
		"\f\4\16\4F\13\4\3\5\3\5\5\5J\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6T"+
		"\n\6\3\7\5\7W\n\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t`\n\t\3\t\5\tc\n\t\3"+
		"\t\7\tf\n\t\f\t\16\ti\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\ns\n\n\3"+
		"\13\3\13\3\13\7\13x\n\13\f\13\16\13{\13\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\7\16\u0086\n\16\f\16\16\16\u0089\13\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00a7\n\17\3\20"+
		"\7\20\u00aa\n\20\f\20\16\20\u00ad\13\20\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\7\22\u00b6\n\22\f\22\16\22\u00b9\13\22\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u00c0\n\23\3\24\3\24\3\24\3\24\5\24\u00c6\n\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00d2\n\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u00ee\n\24\f\24\16\24\u00f1"+
		"\13\24\3\25\3\25\3\25\7\25\u00f6\n\25\f\25\16\25\u00f9\13\25\3\25\2\3"+
		"&\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\5\4\2\26\26\33\33"+
		"\3\2\27\30\3\2\25\26\2\u010a\2-\3\2\2\2\4=\3\2\2\2\6?\3\2\2\2\bI\3\2\2"+
		"\2\nS\3\2\2\2\fV\3\2\2\2\16X\3\2\2\2\20Z\3\2\2\2\22r\3\2\2\2\24t\3\2\2"+
		"\2\26|\3\2\2\2\30\177\3\2\2\2\32\u0087\3\2\2\2\34\u00a6\3\2\2\2\36\u00ab"+
		"\3\2\2\2 \u00ae\3\2\2\2\"\u00b2\3\2\2\2$\u00bf\3\2\2\2&\u00d1\3\2\2\2"+
		"(\u00f2\3\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60"+
		"\3\2\2\2/-\3\2\2\2\60\61\7\2\2\3\61\3\3\2\2\2\62\63\5\b\5\2\63\64\5\6"+
		"\4\2\64\65\7\34\2\2\65>\3\2\2\2\66\67\5\b\5\2\678\7\34\2\28>\3\2\2\29"+
		":\5\b\5\2:;\5\22\n\2;<\5\30\r\2<>\3\2\2\2=\62\3\2\2\2=\66\3\2\2\2=9\3"+
		"\2\2\2>\5\3\2\2\2?D\5\20\t\2@A\7\35\2\2AC\5\20\t\2B@\3\2\2\2CF\3\2\2\2"+
		"DB\3\2\2\2DE\3\2\2\2E\7\3\2\2\2FD\3\2\2\2GJ\5\n\6\2HJ\7\r\2\2IG\3\2\2"+
		"\2IH\3\2\2\2J\t\3\2\2\2KL\7\f\2\2LM\5\f\7\2MN\7\22\2\2NO\5\36\20\2OP\7"+
		"\23\2\2PT\3\2\2\2QR\7\f\2\2RT\5\16\b\2SK\3\2\2\2SQ\3\2\2\2T\13\3\2\2\2"+
		"UW\7 \2\2VU\3\2\2\2VW\3\2\2\2W\r\3\2\2\2XY\7 \2\2Y\17\3\2\2\2Zg\7 \2\2"+
		"[b\7\20\2\2\\c\7!\2\2]`\7\"\2\2^`\7 \2\2_]\3\2\2\2_^\3\2\2\2`a\3\2\2\2"+
		"ac\b\t\1\2b\\\3\2\2\2b_\3\2\2\2cd\3\2\2\2df\7\21\2\2e[\3\2\2\2fi\3\2\2"+
		"\2ge\3\2\2\2gh\3\2\2\2h\21\3\2\2\2ig\3\2\2\2jk\7 \2\2kl\7\16\2\2lm\5\24"+
		"\13\2mn\7\17\2\2ns\3\2\2\2op\7 \2\2pq\7\16\2\2qs\7\17\2\2rj\3\2\2\2ro"+
		"\3\2\2\2s\23\3\2\2\2ty\5\26\f\2uv\7\35\2\2vx\5\26\f\2wu\3\2\2\2x{\3\2"+
		"\2\2yw\3\2\2\2yz\3\2\2\2z\25\3\2\2\2{y\3\2\2\2|}\5\b\5\2}~\5\20\t\2~\27"+
		"\3\2\2\2\177\u0080\7\22\2\2\u0080\u0081\5\36\20\2\u0081\u0082\5\32\16"+
		"\2\u0082\u0083\7\23\2\2\u0083\31\3\2\2\2\u0084\u0086\5\34\17\2\u0085\u0084"+
		"\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\33\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008b\5&\24\2\u008b\u008c\7\34\2"+
		"\2\u008c\u00a7\3\2\2\2\u008d\u00a7\5\30\r\2\u008e\u008f\7\13\2\2\u008f"+
		"\u0090\5&\24\2\u0090\u0091\7\34\2\2\u0091\u00a7\3\2\2\2\u0092\u0093\7"+
		"\t\2\2\u0093\u0094\7\16\2\2\u0094\u0095\5&\24\2\u0095\u0096\7\17\2\2\u0096"+
		"\u0097\5\34\17\2\u0097\u00a7\3\2\2\2\u0098\u0099\7\t\2\2\u0099\u009a\7"+
		"\16\2\2\u009a\u009b\5&\24\2\u009b\u009c\7\17\2\2\u009c\u009d\5\34\17\2"+
		"\u009d\u009e\7\n\2\2\u009e\u009f\5\34\17\2\u009f\u00a7\3\2\2\2\u00a0\u00a1"+
		"\7\b\2\2\u00a1\u00a2\7\16\2\2\u00a2\u00a3\5&\24\2\u00a3\u00a4\7\17\2\2"+
		"\u00a4\u00a5\5\34\17\2\u00a5\u00a7\3\2\2\2\u00a6\u008a\3\2\2\2\u00a6\u008d"+
		"\3\2\2\2\u00a6\u008e\3\2\2\2\u00a6\u0092\3\2\2\2\u00a6\u0098\3\2\2\2\u00a6"+
		"\u00a0\3\2\2\2\u00a7\35\3\2\2\2\u00a8\u00aa\5 \21\2\u00a9\u00a8\3\2\2"+
		"\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\37"+
		"\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\5\b\5\2\u00af\u00b0\5\"\22\2"+
		"\u00b0\u00b1\7\34\2\2\u00b1!\3\2\2\2\u00b2\u00b7\5$\23\2\u00b3\u00b4\7"+
		"\35\2\2\u00b4\u00b6\5$\23\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7"+
		"\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8#\3\2\2\2\u00b9\u00b7\3\2\2\2"+
		"\u00ba\u00c0\5\20\t\2\u00bb\u00bc\5\20\t\2\u00bc\u00bd\7\36\2\2\u00bd"+
		"\u00be\5&\24\2\u00be\u00c0\3\2\2\2\u00bf\u00ba\3\2\2\2\u00bf\u00bb\3\2"+
		"\2\2\u00c0%\3\2\2\2\u00c1\u00c2\b\24\1\2\u00c2\u00c3\7 \2\2\u00c3\u00c5"+
		"\7\16\2\2\u00c4\u00c6\5(\25\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2"+
		"\u00c6\u00c7\3\2\2\2\u00c7\u00d2\7\17\2\2\u00c8\u00c9\7\16\2\2\u00c9\u00ca"+
		"\5&\24\2\u00ca\u00cb\7\17\2\2\u00cb\u00d2\3\2\2\2\u00cc\u00cd\t\2\2\2"+
		"\u00cd\u00d2\5&\24\f\u00ce\u00d2\7 \2\2\u00cf\u00d2\7!\2\2\u00d0\u00d2"+
		"\7\"\2\2\u00d1\u00c1\3\2\2\2\u00d1\u00c8\3\2\2\2\u00d1\u00cc\3\2\2\2\u00d1"+
		"\u00ce\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00ef\3\2"+
		"\2\2\u00d3\u00d4\f\13\2\2\u00d4\u00d5\t\3\2\2\u00d5\u00ee\5&\24\f\u00d6"+
		"\u00d7\f\n\2\2\u00d7\u00d8\t\4\2\2\u00d8\u00ee\5&\24\13\u00d9\u00da\f"+
		"\t\2\2\u00da\u00db\7\24\2\2\u00db\u00ee\5&\24\n\u00dc\u00dd\f\b\2\2\u00dd"+
		"\u00de\7\31\2\2\u00de\u00ee\5&\24\t\u00df\u00e0\f\7\2\2\u00e0\u00e1\7"+
		"\32\2\2\u00e1\u00ee\5&\24\b\u00e2\u00e3\f\6\2\2\u00e3\u00e4\7\36\2\2\u00e4"+
		"\u00ee\5&\24\6\u00e5\u00e6\f\16\2\2\u00e6\u00e7\7\20\2\2\u00e7\u00e8\5"+
		"&\24\2\u00e8\u00e9\7\21\2\2\u00e9\u00ee\3\2\2\2\u00ea\u00eb\f\r\2\2\u00eb"+
		"\u00ec\7\37\2\2\u00ec\u00ee\7 \2\2\u00ed\u00d3\3\2\2\2\u00ed\u00d6\3\2"+
		"\2\2\u00ed\u00d9\3\2\2\2\u00ed\u00dc\3\2\2\2\u00ed\u00df\3\2\2\2\u00ed"+
		"\u00e2\3\2\2\2\u00ed\u00e5\3\2\2\2\u00ed\u00ea\3\2\2\2\u00ee\u00f1\3\2"+
		"\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\'\3\2\2\2\u00f1\u00ef"+
		"\3\2\2\2\u00f2\u00f7\5&\24\2\u00f3\u00f4\7\35\2\2\u00f4\u00f6\5&\24\2"+
		"\u00f5\u00f3\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8"+
		"\3\2\2\2\u00f8)\3\2\2\2\u00f9\u00f7\3\2\2\2\27-=DISV_bgry\u0087\u00a6"+
		"\u00ab\u00b7\u00bf\u00c5\u00d1\u00ed\u00ef\u00f7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}