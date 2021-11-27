// Generated from /Users/cynyard/Documents/大学学习/大四上/编译原理-助教版/ANTLR-Labs/src/main/java/L2/CmmParser.g4 by ANTLR 4.9.2
package L2;
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
			varDec();
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(62);
				match(COMMA);
				setState(63);
				varDec();
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
				defList();
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
		try {
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				match(ID);
				}
				break;
			case LC:
				enterOuterAlt(_localctx, 2);
				{
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
			setState(87);
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
		public VarDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final VarDecContext varDec() throws RecognitionException {
		VarDecContext _localctx = new VarDecContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(ID);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LB) {
				{
				{
				setState(90);
				match(LB);
				setState(91);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INT) | (1L << FLOAT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(92);
				match(RB);
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			    if (_localctx.ID().size() > 1 || _localctx.FLOAT().size() > 0) {
			        List<TerminalNode> IDList = _localctx.ID();
			        List<TerminalNode> FLOATList = _localctx.FLOAT();
			        List<TerminalNode> ErrorList = new ArrayList<>();
			        ErrorList.addAll(IDList);
			        ErrorList.addAll(FLOATList);
			        ErrorList.sort((node1, node2) ->
			                node1.getSymbol().getLine() - node2.getSymbol().getLine() == 0 ?
			                        node1.getSymbol().getCharPositionInLine() - node2.getSymbol()
			                                .getCharPositionInLine()
			                        : node1.getSymbol().getLine() - node2.getSymbol().getLine());
			        boolean skip = true;
			        for (TerminalNode id : ErrorList) {
			            if (skip) {
			                skip = false;
			                continue;
			            }
			            notifyErrorListeners(id.getSymbol(),
			                    "array size must be an integer constant, not " + id.getText(),
			                    null);
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
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				match(ID);
				setState(101);
				match(LP);
				setState(102);
				varList();
				setState(103);
				match(RP);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(ID);
				setState(106);
				match(LP);
				setState(107);
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
			setState(110);
			paramDec();
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(111);
				match(COMMA);
				setState(112);
				paramDec();
				}
				}
				setState(117);
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
			setState(118);
			specifier();
			setState(119);
			varDec();
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
			setState(121);
			match(LC);
			setState(122);
			defList();
			setState(123);
			stmtList();
			setState(124);
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
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHILE) | (1L << IF) | (1L << RETURN) | (1L << LP) | (1L << LC) | (1L << MINUS) | (1L << NOT) | (1L << ID) | (1L << INT) | (1L << FLOAT))) != 0)) {
				{
				{
				setState(126);
				stmt();
				}
				}
				setState(131);
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
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CmmParser.SEMI, 0); }
		public CompStContext compSt() {
			return getRuleContext(CompStContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(CmmParser.RETURN, 0); }
		public TerminalNode IF() { return getToken(CmmParser.IF, 0); }
		public TerminalNode LP() { return getToken(CmmParser.LP, 0); }
		public TerminalNode RP() { return getToken(CmmParser.RP, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(CmmParser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(CmmParser.WHILE, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stmt);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				exp(0);
				setState(133);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				compSt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				match(RETURN);
				setState(137);
				exp(0);
				setState(138);
				match(SEMI);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				match(IF);
				setState(141);
				match(LP);
				setState(142);
				exp(0);
				setState(143);
				match(RP);
				setState(144);
				stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(146);
				match(IF);
				setState(147);
				match(LP);
				setState(148);
				exp(0);
				setState(149);
				match(RP);
				setState(150);
				stmt();
				setState(151);
				match(ELSE);
				setState(152);
				stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(154);
				match(WHILE);
				setState(155);
				match(LP);
				setState(156);
				exp(0);
				setState(157);
				match(RP);
				setState(158);
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
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public DefListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final DefListContext defList() throws RecognitionException {
		DefListContext _localctx = new DefListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_defList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRUCT || _la==TYPE) {
				{
				{
				setState(162);
				def();
				}
				}
				setState(167);
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
		public SpecifierContext specifier() {
			return getRuleContext(SpecifierContext.class,0);
		}
		public DecListContext decList() {
			return getRuleContext(DecListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CmmParser.SEMI, 0); }
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			specifier();
			setState(169);
			decList();
			setState(170);
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
		public DecListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final DecListContext decList() throws RecognitionException {
		DecListContext _localctx = new DecListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_decList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			dec();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(173);
				match(COMMA);
				setState(174);
				dec();
				}
				}
				setState(179);
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
		public VarDecContext varDec() {
			return getRuleContext(VarDecContext.class,0);
		}
		public TerminalNode ASSIGNOP() { return getToken(CmmParser.ASSIGNOP, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public DecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final DecContext dec() throws RecognitionException {
		DecContext _localctx = new DecContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_dec);
		try {
			setState(185);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				varDec();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				varDec();
				setState(182);
				match(ASSIGNOP);
				setState(183);
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
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public TerminalNode LP() { return getToken(CmmParser.LP, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode RP() { return getToken(CmmParser.RP, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(CmmParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(CmmParser.NOT, 0); }
		public TerminalNode INT() { return getToken(CmmParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(CmmParser.FLOAT, 0); }
		public TerminalNode STAR() { return getToken(CmmParser.STAR, 0); }
		public TerminalNode DIV() { return getToken(CmmParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(CmmParser.PLUS, 0); }
		public TerminalNode RELOP() { return getToken(CmmParser.RELOP, 0); }
		public TerminalNode ASSIGNOP() { return getToken(CmmParser.ASSIGNOP, 0); }
		public TerminalNode AND() { return getToken(CmmParser.AND, 0); }
		public TerminalNode OR() { return getToken(CmmParser.OR, 0); }
		public TerminalNode LB() { return getToken(CmmParser.LB, 0); }
		public TerminalNode RB() { return getToken(CmmParser.RB, 0); }
		public TerminalNode DOT() { return getToken(CmmParser.DOT, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CmmParserListener ) ((CmmParserListener)listener).exitExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmmParserVisitor ) return ((CmmParserVisitor<? extends T>)visitor).visitExp(this);
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
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(188);
				match(ID);
				setState(189);
				match(LP);
				setState(190);
				args();
				setState(191);
				match(RP);
				}
				break;
			case 2:
				{
				setState(193);
				match(ID);
				setState(194);
				match(LP);
				setState(195);
				match(RP);
				}
				break;
			case 3:
				{
				setState(196);
				match(LP);
				setState(197);
				exp(0);
				setState(198);
				match(RP);
				}
				break;
			case 4:
				{
				setState(200);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(201);
				exp(10);
				}
				break;
			case 5:
				{
				setState(202);
				match(ID);
				}
				break;
			case 6:
				{
				setState(203);
				match(INT);
				}
				break;
			case 7:
				{
				setState(204);
				match(FLOAT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(233);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(207);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(208);
						_la = _input.LA(1);
						if ( !(_la==STAR || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(209);
						exp(10);
						}
						break;
					case 2:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(210);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(211);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(212);
						exp(9);
						}
						break;
					case 3:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(213);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(214);
						match(RELOP);
						setState(215);
						exp(8);
						}
						break;
					case 4:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(216);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(217);
						match(ASSIGNOP);
						setState(218);
						exp(6);
						}
						break;
					case 5:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(219);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(220);
						match(AND);
						setState(221);
						exp(6);
						}
						break;
					case 6:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(222);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(223);
						match(OR);
						setState(224);
						exp(5);
						}
						break;
					case 7:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(225);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(226);
						match(LB);
						setState(227);
						exp(0);
						setState(228);
						match(RB);
						}
						break;
					case 8:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(230);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(231);
						match(DOT);
						setState(232);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
			setState(238);
			exp(0);
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(239);
				match(COMMA);
				setState(240);
				exp(0);
				}
				}
				setState(245);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\u00f9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3>\n\3\3\4\3\4\3\4\7\4C\n\4"+
		"\f\4\16\4F\13\4\3\5\3\5\5\5J\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6T"+
		"\n\6\3\7\3\7\5\7X\n\7\3\b\3\b\3\t\3\t\3\t\3\t\7\t`\n\t\f\t\16\tc\13\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\no\n\n\3\13\3\13\3\13\7\13"+
		"t\n\13\f\13\16\13w\13\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\7\16\u0082"+
		"\n\16\f\16\16\16\u0085\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\5\17\u00a3\n\17\3\20\7\20\u00a6\n\20\f\20"+
		"\16\20\u00a9\13\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\7\22\u00b2\n\22"+
		"\f\22\16\22\u00b5\13\22\3\23\3\23\3\23\3\23\3\23\5\23\u00bc\n\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u00d0\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\7\24\u00ec\n\24\f\24\16\24\u00ef\13\24\3\25\3\25"+
		"\3\25\7\25\u00f4\n\25\f\25\16\25\u00f7\13\25\3\25\2\3&\26\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(\2\6\3\2 \"\4\2\26\26\33\33\3\2\27\30"+
		"\3\2\25\26\2\u0106\2-\3\2\2\2\4=\3\2\2\2\6?\3\2\2\2\bI\3\2\2\2\nS\3\2"+
		"\2\2\fW\3\2\2\2\16Y\3\2\2\2\20[\3\2\2\2\22n\3\2\2\2\24p\3\2\2\2\26x\3"+
		"\2\2\2\30{\3\2\2\2\32\u0083\3\2\2\2\34\u00a2\3\2\2\2\36\u00a7\3\2\2\2"+
		" \u00aa\3\2\2\2\"\u00ae\3\2\2\2$\u00bb\3\2\2\2&\u00cf\3\2\2\2(\u00f0\3"+
		"\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/"+
		"-\3\2\2\2\60\61\7\2\2\3\61\3\3\2\2\2\62\63\5\b\5\2\63\64\5\6\4\2\64\65"+
		"\7\34\2\2\65>\3\2\2\2\66\67\5\b\5\2\678\7\34\2\28>\3\2\2\29:\5\b\5\2:"+
		";\5\22\n\2;<\5\30\r\2<>\3\2\2\2=\62\3\2\2\2=\66\3\2\2\2=9\3\2\2\2>\5\3"+
		"\2\2\2?D\5\20\t\2@A\7\35\2\2AC\5\20\t\2B@\3\2\2\2CF\3\2\2\2DB\3\2\2\2"+
		"DE\3\2\2\2E\7\3\2\2\2FD\3\2\2\2GJ\5\n\6\2HJ\7\r\2\2IG\3\2\2\2IH\3\2\2"+
		"\2J\t\3\2\2\2KL\7\f\2\2LM\5\f\7\2MN\7\22\2\2NO\5\36\20\2OP\7\23\2\2PT"+
		"\3\2\2\2QR\7\f\2\2RT\5\16\b\2SK\3\2\2\2SQ\3\2\2\2T\13\3\2\2\2UX\7 \2\2"+
		"VX\3\2\2\2WU\3\2\2\2WV\3\2\2\2X\r\3\2\2\2YZ\7 \2\2Z\17\3\2\2\2[a\7 \2"+
		"\2\\]\7\20\2\2]^\t\2\2\2^`\7\21\2\2_\\\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3"+
		"\2\2\2bd\3\2\2\2ca\3\2\2\2de\b\t\1\2e\21\3\2\2\2fg\7 \2\2gh\7\16\2\2h"+
		"i\5\24\13\2ij\7\17\2\2jo\3\2\2\2kl\7 \2\2lm\7\16\2\2mo\7\17\2\2nf\3\2"+
		"\2\2nk\3\2\2\2o\23\3\2\2\2pu\5\26\f\2qr\7\35\2\2rt\5\26\f\2sq\3\2\2\2"+
		"tw\3\2\2\2us\3\2\2\2uv\3\2\2\2v\25\3\2\2\2wu\3\2\2\2xy\5\b\5\2yz\5\20"+
		"\t\2z\27\3\2\2\2{|\7\22\2\2|}\5\36\20\2}~\5\32\16\2~\177\7\23\2\2\177"+
		"\31\3\2\2\2\u0080\u0082\5\34\17\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2"+
		"\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\33\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0086\u0087\5&\24\2\u0087\u0088\7\34\2\2\u0088\u00a3\3\2\2\2"+
		"\u0089\u00a3\5\30\r\2\u008a\u008b\7\13\2\2\u008b\u008c\5&\24\2\u008c\u008d"+
		"\7\34\2\2\u008d\u00a3\3\2\2\2\u008e\u008f\7\t\2\2\u008f\u0090\7\16\2\2"+
		"\u0090\u0091\5&\24\2\u0091\u0092\7\17\2\2\u0092\u0093\5\34\17\2\u0093"+
		"\u00a3\3\2\2\2\u0094\u0095\7\t\2\2\u0095\u0096\7\16\2\2\u0096\u0097\5"+
		"&\24\2\u0097\u0098\7\17\2\2\u0098\u0099\5\34\17\2\u0099\u009a\7\n\2\2"+
		"\u009a\u009b\5\34\17\2\u009b\u00a3\3\2\2\2\u009c\u009d\7\b\2\2\u009d\u009e"+
		"\7\16\2\2\u009e\u009f\5&\24\2\u009f\u00a0\7\17\2\2\u00a0\u00a1\5\34\17"+
		"\2\u00a1\u00a3\3\2\2\2\u00a2\u0086\3\2\2\2\u00a2\u0089\3\2\2\2\u00a2\u008a"+
		"\3\2\2\2\u00a2\u008e\3\2\2\2\u00a2\u0094\3\2\2\2\u00a2\u009c\3\2\2\2\u00a3"+
		"\35\3\2\2\2\u00a4\u00a6\5 \21\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2"+
		"\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\37\3\2\2\2\u00a9\u00a7"+
		"\3\2\2\2\u00aa\u00ab\5\b\5\2\u00ab\u00ac\5\"\22\2\u00ac\u00ad\7\34\2\2"+
		"\u00ad!\3\2\2\2\u00ae\u00b3\5$\23\2\u00af\u00b0\7\35\2\2\u00b0\u00b2\5"+
		"$\23\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4#\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00bc\5\20\t\2"+
		"\u00b7\u00b8\5\20\t\2\u00b8\u00b9\7\36\2\2\u00b9\u00ba\5&\24\2\u00ba\u00bc"+
		"\3\2\2\2\u00bb\u00b6\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bc%\3\2\2\2\u00bd"+
		"\u00be\b\24\1\2\u00be\u00bf\7 \2\2\u00bf\u00c0\7\16\2\2\u00c0\u00c1\5"+
		"(\25\2\u00c1\u00c2\7\17\2\2\u00c2\u00d0\3\2\2\2\u00c3\u00c4\7 \2\2\u00c4"+
		"\u00c5\7\16\2\2\u00c5\u00d0\7\17\2\2\u00c6\u00c7\7\16\2\2\u00c7\u00c8"+
		"\5&\24\2\u00c8\u00c9\7\17\2\2\u00c9\u00d0\3\2\2\2\u00ca\u00cb\t\3\2\2"+
		"\u00cb\u00d0\5&\24\f\u00cc\u00d0\7 \2\2\u00cd\u00d0\7!\2\2\u00ce\u00d0"+
		"\7\"\2\2\u00cf\u00bd\3\2\2\2\u00cf\u00c3\3\2\2\2\u00cf\u00c6\3\2\2\2\u00cf"+
		"\u00ca\3\2\2\2\u00cf\u00cc\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00ce\3\2"+
		"\2\2\u00d0\u00ed\3\2\2\2\u00d1\u00d2\f\13\2\2\u00d2\u00d3\t\4\2\2\u00d3"+
		"\u00ec\5&\24\f\u00d4\u00d5\f\n\2\2\u00d5\u00d6\t\5\2\2\u00d6\u00ec\5&"+
		"\24\13\u00d7\u00d8\f\t\2\2\u00d8\u00d9\7\24\2\2\u00d9\u00ec\5&\24\n\u00da"+
		"\u00db\f\b\2\2\u00db\u00dc\7\36\2\2\u00dc\u00ec\5&\24\b\u00dd\u00de\f"+
		"\7\2\2\u00de\u00df\7\31\2\2\u00df\u00ec\5&\24\b\u00e0\u00e1\f\6\2\2\u00e1"+
		"\u00e2\7\32\2\2\u00e2\u00ec\5&\24\7\u00e3\u00e4\f\16\2\2\u00e4\u00e5\7"+
		"\20\2\2\u00e5\u00e6\5&\24\2\u00e6\u00e7\7\21\2\2\u00e7\u00ec\3\2\2\2\u00e8"+
		"\u00e9\f\r\2\2\u00e9\u00ea\7\37\2\2\u00ea\u00ec\7 \2\2\u00eb\u00d1\3\2"+
		"\2\2\u00eb\u00d4\3\2\2\2\u00eb\u00d7\3\2\2\2\u00eb\u00da\3\2\2\2\u00eb"+
		"\u00dd\3\2\2\2\u00eb\u00e0\3\2\2\2\u00eb\u00e3\3\2\2\2\u00eb\u00e8\3\2"+
		"\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee"+
		"\'\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f5\5&\24\2\u00f1\u00f2\7\35\2"+
		"\2\u00f2\u00f4\5&\24\2\u00f3\u00f1\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3"+
		"\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6)\3\2\2\2\u00f7\u00f5\3\2\2\2\24-=D"+
		"ISWanu\u0083\u00a2\u00a7\u00b3\u00bb\u00cf\u00eb\u00ed\u00f5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}