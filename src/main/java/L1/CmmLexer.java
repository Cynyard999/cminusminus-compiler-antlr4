// Generated from /Users/cynyard/Documents/大学学习/大四上/编译原理-助教版/ANTLR-Labs/src/main/java/L1/CmmLexer.g4 by ANTLR 4.9.2
package L1;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CmmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Whitespace=1, Newline=2, BlockComment=3, LineComment=4, DO=5, WHILE=6, 
		IF=7, ELSE=8, RETURN=9, STRUCT=10, TYPE=11, LP=12, RP=13, LB=14, RB=15, 
		LC=16, RC=17, RELOP=18, PLUS=19, MINUS=20, STAR=21, DIV=22, AND=23, OR=24, 
		NOT=25, SEMI=26, COMMA=27, ASSIGNOP=28, DOT=29, ID=30, INT=31, FLOAT=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Whitespace", "Newline", "BlockComment", "LineComment", "DO", "WHILE", 
			"IF", "ELSE", "RETURN", "STRUCT", "TYPE", "LP", "RP", "LB", "RB", "LC", 
			"RC", "RELOP", "PLUS", "MINUS", "STAR", "DIV", "AND", "OR", "NOT", "SEMI", 
			"COMMA", "ASSIGNOP", "DOT", "ID", "INT", "FLOAT", "Nondigit", "Digit", 
			"NonZeroDigit", "Int10", "Int8", "Int16", "Real", "Science"
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


	public CmmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CmmLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u0130\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\6\2U\n\2\r"+
		"\2\16\2V\3\2\3\2\3\3\3\3\5\3]\n\3\3\3\5\3`\n\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\7\4h\n\4\f\4\16\4k\13\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\7\5v\n\5"+
		"\f\5\16\5y\13\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a4\n\f\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00bc\n\23\3\24\3\24\3\25\3\25\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\7\37\u00d9\n\37\f\37\16\37\u00dc"+
		"\13\37\3 \3 \3 \5 \u00e1\n \3!\3!\5!\u00e5\n!\3\"\3\"\3#\3#\3$\3$\3%\3"+
		"%\7%\u00ef\n%\f%\16%\u00f2\13%\3&\3&\7&\u00f6\n&\f&\16&\u00f9\13&\3\'"+
		"\3\'\3\'\3\'\6\'\u00ff\n\'\r\'\16\'\u0100\3(\6(\u0104\n(\r(\16(\u0105"+
		"\3(\3(\6(\u010a\n(\r(\16(\u010b\3)\7)\u010f\n)\f)\16)\u0112\13)\3)\3)"+
		"\6)\u0116\n)\r)\16)\u0117\3)\6)\u011b\n)\r)\16)\u011c\3)\3)\7)\u0121\n"+
		")\f)\16)\u0124\13)\5)\u0126\n)\3)\3)\5)\u012a\n)\3)\6)\u012d\n)\r)\16"+
		")\u012e\3i\2*\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34"+
		"\67\359\36;\37= ?!A\"C\2E\2G\2I\2K\2M\2O\2Q\2\3\2\f\4\2\13\13\"\"\4\2"+
		"\f\f\17\17\5\2C\\aac|\3\2\62;\3\2\63;\3\2\629\4\2ZZzz\4\2CHch\4\2GGgg"+
		"\4\2--//\2\u0144\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3T\3\2\2\2\5_"+
		"\3\2\2\2\7c\3\2\2\2\tq\3\2\2\2\13|\3\2\2\2\r\177\3\2\2\2\17\u0085\3\2"+
		"\2\2\21\u0088\3\2\2\2\23\u008d\3\2\2\2\25\u0094\3\2\2\2\27\u00a3\3\2\2"+
		"\2\31\u00a5\3\2\2\2\33\u00a7\3\2\2\2\35\u00a9\3\2\2\2\37\u00ab\3\2\2\2"+
		"!\u00ad\3\2\2\2#\u00af\3\2\2\2%\u00bb\3\2\2\2\'\u00bd\3\2\2\2)\u00bf\3"+
		"\2\2\2+\u00c1\3\2\2\2-\u00c3\3\2\2\2/\u00c5\3\2\2\2\61\u00c8\3\2\2\2\63"+
		"\u00cb\3\2\2\2\65\u00cd\3\2\2\2\67\u00cf\3\2\2\29\u00d1\3\2\2\2;\u00d3"+
		"\3\2\2\2=\u00d5\3\2\2\2?\u00e0\3\2\2\2A\u00e4\3\2\2\2C\u00e6\3\2\2\2E"+
		"\u00e8\3\2\2\2G\u00ea\3\2\2\2I\u00ec\3\2\2\2K\u00f3\3\2\2\2M\u00fa\3\2"+
		"\2\2O\u0103\3\2\2\2Q\u0125\3\2\2\2SU\t\2\2\2TS\3\2\2\2UV\3\2\2\2VT\3\2"+
		"\2\2VW\3\2\2\2WX\3\2\2\2XY\b\2\2\2Y\4\3\2\2\2Z\\\7\17\2\2[]\7\f\2\2\\"+
		"[\3\2\2\2\\]\3\2\2\2]`\3\2\2\2^`\7\f\2\2_Z\3\2\2\2_^\3\2\2\2`a\3\2\2\2"+
		"ab\b\3\2\2b\6\3\2\2\2cd\7\61\2\2de\7,\2\2ei\3\2\2\2fh\13\2\2\2gf\3\2\2"+
		"\2hk\3\2\2\2ij\3\2\2\2ig\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7,\2\2mn\7\61\2"+
		"\2no\3\2\2\2op\b\4\2\2p\b\3\2\2\2qr\7\61\2\2rs\7\61\2\2sw\3\2\2\2tv\n"+
		"\3\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\b"+
		"\5\2\2{\n\3\2\2\2|}\7f\2\2}~\7q\2\2~\f\3\2\2\2\177\u0080\7y\2\2\u0080"+
		"\u0081\7j\2\2\u0081\u0082\7k\2\2\u0082\u0083\7n\2\2\u0083\u0084\7g\2\2"+
		"\u0084\16\3\2\2\2\u0085\u0086\7k\2\2\u0086\u0087\7h\2\2\u0087\20\3\2\2"+
		"\2\u0088\u0089\7g\2\2\u0089\u008a\7n\2\2\u008a\u008b\7u\2\2\u008b\u008c"+
		"\7g\2\2\u008c\22\3\2\2\2\u008d\u008e\7t\2\2\u008e\u008f\7g\2\2\u008f\u0090"+
		"\7v\2\2\u0090\u0091\7w\2\2\u0091\u0092\7t\2\2\u0092\u0093\7p\2\2\u0093"+
		"\24\3\2\2\2\u0094\u0095\7u\2\2\u0095\u0096\7v\2\2\u0096\u0097\7t\2\2\u0097"+
		"\u0098\7w\2\2\u0098\u0099\7e\2\2\u0099\u009a\7v\2\2\u009a\26\3\2\2\2\u009b"+
		"\u009c\7k\2\2\u009c\u009d\7p\2\2\u009d\u00a4\7v\2\2\u009e\u009f\7h\2\2"+
		"\u009f\u00a0\7n\2\2\u00a0\u00a1\7q\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a4"+
		"\7v\2\2\u00a3\u009b\3\2\2\2\u00a3\u009e\3\2\2\2\u00a4\30\3\2\2\2\u00a5"+
		"\u00a6\7*\2\2\u00a6\32\3\2\2\2\u00a7\u00a8\7+\2\2\u00a8\34\3\2\2\2\u00a9"+
		"\u00aa\7]\2\2\u00aa\36\3\2\2\2\u00ab\u00ac\7_\2\2\u00ac \3\2\2\2\u00ad"+
		"\u00ae\7}\2\2\u00ae\"\3\2\2\2\u00af\u00b0\7\177\2\2\u00b0$\3\2\2\2\u00b1"+
		"\u00bc\7>\2\2\u00b2\u00b3\7>\2\2\u00b3\u00bc\7?\2\2\u00b4\u00bc\7@\2\2"+
		"\u00b5\u00b6\7@\2\2\u00b6\u00bc\7?\2\2\u00b7\u00b8\7?\2\2\u00b8\u00bc"+
		"\7?\2\2\u00b9\u00ba\7#\2\2\u00ba\u00bc\7?\2\2\u00bb\u00b1\3\2\2\2\u00bb"+
		"\u00b2\3\2\2\2\u00bb\u00b4\3\2\2\2\u00bb\u00b5\3\2\2\2\u00bb\u00b7\3\2"+
		"\2\2\u00bb\u00b9\3\2\2\2\u00bc&\3\2\2\2\u00bd\u00be\7-\2\2\u00be(\3\2"+
		"\2\2\u00bf\u00c0\7/\2\2\u00c0*\3\2\2\2\u00c1\u00c2\7,\2\2\u00c2,\3\2\2"+
		"\2\u00c3\u00c4\7\61\2\2\u00c4.\3\2\2\2\u00c5\u00c6\7(\2\2\u00c6\u00c7"+
		"\7(\2\2\u00c7\60\3\2\2\2\u00c8\u00c9\7~\2\2\u00c9\u00ca\7~\2\2\u00ca\62"+
		"\3\2\2\2\u00cb\u00cc\7#\2\2\u00cc\64\3\2\2\2\u00cd\u00ce\7=\2\2\u00ce"+
		"\66\3\2\2\2\u00cf\u00d0\7.\2\2\u00d08\3\2\2\2\u00d1\u00d2\7?\2\2\u00d2"+
		":\3\2\2\2\u00d3\u00d4\7\60\2\2\u00d4<\3\2\2\2\u00d5\u00da\5C\"\2\u00d6"+
		"\u00d9\5C\"\2\u00d7\u00d9\5E#\2\u00d8\u00d6\3\2\2\2\u00d8\u00d7\3\2\2"+
		"\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db>"+
		"\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00e1\5K&\2\u00de\u00e1\5I%\2\u00df"+
		"\u00e1\5M\'\2\u00e0\u00dd\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00df\3\2"+
		"\2\2\u00e1@\3\2\2\2\u00e2\u00e5\5Q)\2\u00e3\u00e5\5O(\2\u00e4\u00e2\3"+
		"\2\2\2\u00e4\u00e3\3\2\2\2\u00e5B\3\2\2\2\u00e6\u00e7\t\4\2\2\u00e7D\3"+
		"\2\2\2\u00e8\u00e9\t\5\2\2\u00e9F\3\2\2\2\u00ea\u00eb\t\6\2\2\u00ebH\3"+
		"\2\2\2\u00ec\u00f0\5G$\2\u00ed\u00ef\5E#\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2"+
		"\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1J\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\u00f7\7\62\2\2\u00f4\u00f6\t\7\2\2\u00f5\u00f4\3"+
		"\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8"+
		"L\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00fb\7\62\2\2\u00fb\u00fe\t\b\2\2"+
		"\u00fc\u00ff\t\t\2\2\u00fd\u00ff\5E#\2\u00fe\u00fc\3\2\2\2\u00fe\u00fd"+
		"\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"N\3\2\2\2\u0102\u0104\5E#\2\u0103\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0109\5;"+
		"\36\2\u0108\u010a\5E#\2\u0109\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010cP\3\2\2\2\u010d\u010f\5E#\2\u010e"+
		"\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2"+
		"\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0115\5;\36\2\u0114"+
		"\u0116\5E#\2\u0115\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2\2"+
		"\2\u0117\u0118\3\2\2\2\u0118\u0126\3\2\2\2\u0119\u011b\5E#\2\u011a\u0119"+
		"\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\u011e\3\2\2\2\u011e\u0122\5;\36\2\u011f\u0121\5E#\2\u0120\u011f\3\2\2"+
		"\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0126"+
		"\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0110\3\2\2\2\u0125\u011a\3\2\2\2\u0126"+
		"\u0127\3\2\2\2\u0127\u0129\t\n\2\2\u0128\u012a\t\13\2\2\u0129\u0128\3"+
		"\2\2\2\u0129\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b\u012d\5E#\2\u012c"+
		"\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2"+
		"\2\2\u012fR\3\2\2\2\33\2V\\_iw\u00a3\u00bb\u00d8\u00da\u00e0\u00e4\u00f0"+
		"\u00f7\u00fe\u0100\u0105\u010b\u0110\u0117\u011c\u0122\u0125\u0129\u012e"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}