// Generated from predicatecalculus/src/main/java/HW4/Assumptions.g4 by ANTLR 4.5.3

package HW4;
import expression.*;
import expression.Predicate;
import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AssumptionsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLUS=1, MUL=2, EQUALS=3, COMMA=4, IMPLIES=5, OR=6, AND=7, NOT=8, OB=9, 
		CB=10, VAR=11, PREDVAR=12, ZERO=13, NEXT=14, FORALL=15, EXISTS=16;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PLUS", "MUL", "EQUALS", "COMMA", "IMPLIES", "OR", "AND", "NOT", "OB", 
		"CB", "VAR", "PREDVAR", "ZERO", "NEXT", "FORALL", "EXISTS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'+'", "'*'", "'='", "','", "'->'", "'|'", "'&'", "'!'", "'('", 
		"')'", null, null, "'0'", "'''", "'@'", "'?'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PLUS", "MUL", "EQUALS", "COMMA", "IMPLIES", "OR", "AND", "NOT", 
		"OB", "CB", "VAR", "PREDVAR", "ZERO", "NEXT", "FORALL", "EXISTS"
	};
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


	public AssumptionsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Assumptions.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\22N\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\7\f;\n\f\f\f\16\f>\13\f\3\r\3\r\7\rB\n\r\f\r\16\rE\13\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\2\2\22\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22\3\2\5\3\2c|\3"+
		"\2\62;\3\2C\\O\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\3#\3\2\2\2\5%\3\2\2\2\7\'\3\2\2\2\t)\3\2\2\2\13+\3\2\2\2\r.\3\2"+
		"\2\2\17\60\3\2\2\2\21\62\3\2\2\2\23\64\3\2\2\2\25\66\3\2\2\2\278\3\2\2"+
		"\2\31?\3\2\2\2\33F\3\2\2\2\35H\3\2\2\2\37J\3\2\2\2!L\3\2\2\2#$\7-\2\2"+
		"$\4\3\2\2\2%&\7,\2\2&\6\3\2\2\2\'(\7?\2\2(\b\3\2\2\2)*\7.\2\2*\n\3\2\2"+
		"\2+,\7/\2\2,-\7@\2\2-\f\3\2\2\2./\7~\2\2/\16\3\2\2\2\60\61\7(\2\2\61\20"+
		"\3\2\2\2\62\63\7#\2\2\63\22\3\2\2\2\64\65\7*\2\2\65\24\3\2\2\2\66\67\7"+
		"+\2\2\67\26\3\2\2\28<\t\2\2\29;\t\3\2\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2"+
		"<=\3\2\2\2=\30\3\2\2\2><\3\2\2\2?C\t\4\2\2@B\t\3\2\2A@\3\2\2\2BE\3\2\2"+
		"\2CA\3\2\2\2CD\3\2\2\2D\32\3\2\2\2EC\3\2\2\2FG\7\62\2\2G\34\3\2\2\2HI"+
		"\7)\2\2I\36\3\2\2\2JK\7B\2\2K \3\2\2\2LM\7A\2\2M\"\3\2\2\2\5\2<C\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}