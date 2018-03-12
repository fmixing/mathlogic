// Generated from predicatecalculus/src/main/java/HW4/Assumptions.g4 by ANTLR 4.5.3

package HW4;
import expression.*;
import expression.Predicate;
import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AssumptionsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLUS=1, MUL=2, EQUALS=3, COMMA=4, IMPLIES=5, OR=6, AND=7, NOT=8, OB=9, 
		CB=10, VAR=11, PREDVAR=12, ZERO=13, NEXT=14, FORALL=15, EXISTS=16;
	public static final int
		RULE_parseAssumption = 0, RULE_expression = 1, RULE_disjunction = 2, RULE_conjunction = 3, 
		RULE_unary = 4, RULE_variable = 5, RULE_predicate = 6, RULE_term = 7, 
		RULE_adding = 8, RULE_multiplying = 9;
	public static final String[] ruleNames = {
		"parseAssumption", "expression", "disjunction", "conjunction", "unary", 
		"variable", "predicate", "term", "adding", "multiplying"
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

	@Override
	public String getGrammarFileName() { return "Assumptions.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AssumptionsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseAssumptionContext extends ParserRuleContext {
		public List<Expression> value;
		public ExpressionContext e1;
		public ExpressionContext e2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AssumptionsParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AssumptionsParser.COMMA, i);
		}
		public ParseAssumptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseAssumption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterParseAssumption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitParseAssumption(this);
		}
	}

	public final ParseAssumptionContext parseAssumption() throws RecognitionException {
		ParseAssumptionContext _localctx = new ParseAssumptionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parseAssumption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			((ParseAssumptionContext)_localctx).e1 = expression();
			((ParseAssumptionContext)_localctx).value =  new ArrayList(); _localctx.value.add(((ParseAssumptionContext)_localctx).e1.value);
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(22);
				match(COMMA);
				setState(23);
				((ParseAssumptionContext)_localctx).e2 = expression();
				_localctx.value.add(((ParseAssumptionContext)_localctx).e2.value);
				}
				}
				setState(30);
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

	public static class ExpressionContext extends ParserRuleContext {
		public Expression value;
		public DisjunctionContext d1;
		public DisjunctionContext d2;
		public ExpressionContext e;
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public TerminalNode IMPLIES() { return getToken(AssumptionsParser.IMPLIES, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				((ExpressionContext)_localctx).d1 = disjunction(0);
				((ExpressionContext)_localctx).value =  ((ExpressionContext)_localctx).d1.value;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				((ExpressionContext)_localctx).d2 = disjunction(0);
				setState(35);
				match(IMPLIES);
				setState(36);
				((ExpressionContext)_localctx).e = expression();
				((ExpressionContext)_localctx).value =  new Implication(((ExpressionContext)_localctx).d2.value, ((ExpressionContext)_localctx).e.value);
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

	public static class DisjunctionContext extends ParserRuleContext {
		public Expression value;
		public DisjunctionContext d;
		public ConjunctionContext c1;
		public ConjunctionContext c2;
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public TerminalNode OR() { return getToken(AssumptionsParser.OR, 0); }
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitDisjunction(this);
		}
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		return disjunction(0);
	}

	private DisjunctionContext disjunction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, _parentState);
		DisjunctionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_disjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(42);
			((DisjunctionContext)_localctx).c1 = conjunction(0);
			((DisjunctionContext)_localctx).value =  ((DisjunctionContext)_localctx).c1.value;
			}
			_ctx.stop = _input.LT(-1);
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DisjunctionContext(_parentctx, _parentState);
					_localctx.d = _prevctx;
					_localctx.d = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_disjunction);
					setState(45);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(46);
					match(OR);
					setState(47);
					((DisjunctionContext)_localctx).c2 = conjunction(0);
					((DisjunctionContext)_localctx).value =  new Disjunction(((DisjunctionContext)_localctx).d.value, ((DisjunctionContext)_localctx).c2.value);
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class ConjunctionContext extends ParserRuleContext {
		public Expression value;
		public ConjunctionContext c;
		public UnaryContext u1;
		public UnaryContext u2;
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TerminalNode AND() { return getToken(AssumptionsParser.AND, 0); }
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		return conjunction(0);
	}

	private ConjunctionContext conjunction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, _parentState);
		ConjunctionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_conjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(56);
			((ConjunctionContext)_localctx).u1 = unary();
			((ConjunctionContext)_localctx).value =  ((ConjunctionContext)_localctx).u1.value;
			}
			_ctx.stop = _input.LT(-1);
			setState(66);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConjunctionContext(_parentctx, _parentState);
					_localctx.c = _prevctx;
					_localctx.c = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_conjunction);
					setState(59);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(60);
					match(AND);
					setState(61);
					((ConjunctionContext)_localctx).u2 = unary();
					((ConjunctionContext)_localctx).value =  new Conjunction(((ConjunctionContext)_localctx).c.value, ((ConjunctionContext)_localctx).u2.value);
					}
					} 
				}
				setState(68);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class UnaryContext extends ParserRuleContext {
		public Expression value;
		public PredicateContext p;
		public UnaryContext u1;
		public ExpressionContext e;
		public VariableContext v1;
		public UnaryContext u2;
		public VariableContext v2;
		public UnaryContext u3;
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode NOT() { return getToken(AssumptionsParser.NOT, 0); }
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TerminalNode OB() { return getToken(AssumptionsParser.OB, 0); }
		public TerminalNode CB() { return getToken(AssumptionsParser.CB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode FORALL() { return getToken(AssumptionsParser.FORALL, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode EXISTS() { return getToken(AssumptionsParser.EXISTS, 0); }
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitUnary(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_unary);
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				((UnaryContext)_localctx).p = predicate();
				((UnaryContext)_localctx).value =  ((UnaryContext)_localctx).p.value;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(NOT);
				setState(73);
				((UnaryContext)_localctx).u1 = unary();
				((UnaryContext)_localctx).value =  new Negation(((UnaryContext)_localctx).u1.value);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				match(OB);
				setState(77);
				((UnaryContext)_localctx).e = expression();
				setState(78);
				match(CB);
				((UnaryContext)_localctx).value =  ((UnaryContext)_localctx).e.value;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
				match(FORALL);
				setState(82);
				((UnaryContext)_localctx).v1 = variable();
				setState(83);
				((UnaryContext)_localctx).u2 = unary();
				((UnaryContext)_localctx).value =  new Forall(((UnaryContext)_localctx).v1.value, ((UnaryContext)_localctx).u2.value);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(86);
				match(EXISTS);
				setState(87);
				((UnaryContext)_localctx).v2 = variable();
				setState(88);
				((UnaryContext)_localctx).u3 = unary();
				((UnaryContext)_localctx).value =  new Exists(((UnaryContext)_localctx).v2.value, ((UnaryContext)_localctx).u3.value);
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

	public static class VariableContext extends ParserRuleContext {
		public Variable value;
		public Token VAR;
		public TerminalNode VAR() { return getToken(AssumptionsParser.VAR, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			((VariableContext)_localctx).VAR = match(VAR);
			((VariableContext)_localctx).value =  new Variable((((VariableContext)_localctx).VAR!=null?((VariableContext)_localctx).VAR.getText():null));
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

	public static class PredicateContext extends ParserRuleContext {
		public Expression value;
		public Token PREDVAR;
		public TermContext t3;
		public TermContext t4;
		public TermContext t1;
		public TermContext t2;
		public TerminalNode PREDVAR() { return getToken(AssumptionsParser.PREDVAR, 0); }
		public TerminalNode OB() { return getToken(AssumptionsParser.OB, 0); }
		public TerminalNode CB() { return getToken(AssumptionsParser.CB, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AssumptionsParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AssumptionsParser.COMMA, i);
		}
		public TerminalNode EQUALS() { return getToken(AssumptionsParser.EQUALS, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_predicate);
		int _la;
		try {
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				((PredicateContext)_localctx).PREDVAR = match(PREDVAR);
				((PredicateContext)_localctx).value =  new Predicate((((PredicateContext)_localctx).PREDVAR!=null?((PredicateContext)_localctx).PREDVAR.getText():null));
				setState(98);
				match(OB);
				setState(99);
				((PredicateContext)_localctx).t3 = term(0);
				((Predicate)_localctx.value).addTerm(((PredicateContext)_localctx).t3.value);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(101);
					match(COMMA);
					setState(102);
					((PredicateContext)_localctx).t4 = term(0);
					((Predicate)_localctx.value).addTerm(((PredicateContext)_localctx).t4.value);
					}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(110);
				match(CB);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				((PredicateContext)_localctx).PREDVAR = match(PREDVAR);
				((PredicateContext)_localctx).value =  new Predicate((((PredicateContext)_localctx).PREDVAR!=null?((PredicateContext)_localctx).PREDVAR.getText():null));
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				((PredicateContext)_localctx).t1 = term(0);
				setState(115);
				match(EQUALS);
				setState(116);
				((PredicateContext)_localctx).t2 = term(0);
				((PredicateContext)_localctx).value =  new Equation(((PredicateContext)_localctx).t1.value, ((PredicateContext)_localctx).t2.value);
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

	public static class TermContext extends ParserRuleContext {
		public Expression value;
		public TermContext t;
		public AddingContext a1;
		public AddingContext a2;
		public AddingContext adding() {
			return getRuleContext(AddingContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(AssumptionsParser.PLUS, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(122);
			((TermContext)_localctx).a1 = adding(0);
			((TermContext)_localctx).value =  ((TermContext)_localctx).a1.value;
			}
			_ctx.stop = _input.LT(-1);
			setState(132);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					_localctx.t = _prevctx;
					_localctx.t = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(125);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(126);
					match(PLUS);
					setState(127);
					((TermContext)_localctx).a2 = adding(0);
					((TermContext)_localctx).value =  new Summation(((TermContext)_localctx).t.value, ((TermContext)_localctx).a2.value);
					}
					} 
				}
				setState(134);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class AddingContext extends ParserRuleContext {
		public Expression value;
		public AddingContext a;
		public MultiplyingContext m1;
		public MultiplyingContext multiplying;
		public MultiplyingContext m2;
		public MultiplyingContext multiplying() {
			return getRuleContext(MultiplyingContext.class,0);
		}
		public TerminalNode MUL() { return getToken(AssumptionsParser.MUL, 0); }
		public AddingContext adding() {
			return getRuleContext(AddingContext.class,0);
		}
		public AddingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_adding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterAdding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitAdding(this);
		}
	}

	public final AddingContext adding() throws RecognitionException {
		return adding(0);
	}

	private AddingContext adding(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AddingContext _localctx = new AddingContext(_ctx, _parentState);
		AddingContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_adding, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(136);
			((AddingContext)_localctx).m1 = ((AddingContext)_localctx).multiplying = multiplying(0);
			((AddingContext)_localctx).value =  ((AddingContext)_localctx).multiplying.value;
			}
			_ctx.stop = _input.LT(-1);
			setState(146);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AddingContext(_parentctx, _parentState);
					_localctx.a = _prevctx;
					_localctx.a = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_adding);
					setState(139);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(140);
					match(MUL);
					setState(141);
					((AddingContext)_localctx).m2 = ((AddingContext)_localctx).multiplying = multiplying(0);
					((AddingContext)_localctx).value =  new Multiplication(((AddingContext)_localctx).a.value, ((AddingContext)_localctx).m2.value);
					}
					} 
				}
				setState(148);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class MultiplyingContext extends ParserRuleContext {
		public Expression value;
		public MultiplyingContext m;
		public Token VAR;
		public TermContext t1;
		public TermContext t2;
		public VariableContext v;
		public TermContext t3;
		public TerminalNode VAR() { return getToken(AssumptionsParser.VAR, 0); }
		public TerminalNode OB() { return getToken(AssumptionsParser.OB, 0); }
		public TerminalNode CB() { return getToken(AssumptionsParser.CB, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AssumptionsParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AssumptionsParser.COMMA, i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ZERO() { return getToken(AssumptionsParser.ZERO, 0); }
		public TerminalNode NEXT() { return getToken(AssumptionsParser.NEXT, 0); }
		public MultiplyingContext multiplying() {
			return getRuleContext(MultiplyingContext.class,0);
		}
		public MultiplyingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplying; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).enterMultiplying(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssumptionsListener ) ((AssumptionsListener)listener).exitMultiplying(this);
		}
	}

	public final MultiplyingContext multiplying() throws RecognitionException {
		return multiplying(0);
	}

	private MultiplyingContext multiplying(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultiplyingContext _localctx = new MultiplyingContext(_ctx, _parentState);
		MultiplyingContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_multiplying, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(150);
				((MultiplyingContext)_localctx).VAR = match(VAR);
				setState(151);
				match(OB);
				setState(152);
				((MultiplyingContext)_localctx).t1 = term(0);
				((MultiplyingContext)_localctx).value =  new Function((((MultiplyingContext)_localctx).VAR!=null?((MultiplyingContext)_localctx).VAR.getText():null)); ((Function)_localctx.value).addTerm(((MultiplyingContext)_localctx).t1.value);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(154);
					match(COMMA);
					setState(155);
					((MultiplyingContext)_localctx).t2 = term(0);
					((Function)_localctx.value).addTerm(((MultiplyingContext)_localctx).t2.value);
					}
					}
					setState(162);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(163);
				match(CB);
				}
				break;
			case 2:
				{
				setState(165);
				((MultiplyingContext)_localctx).v = variable();
				((MultiplyingContext)_localctx).value =  ((MultiplyingContext)_localctx).v.value;
				}
				break;
			case 3:
				{
				setState(168);
				match(OB);
				setState(169);
				((MultiplyingContext)_localctx).t3 = term(0);
				setState(170);
				match(CB);
				((MultiplyingContext)_localctx).value =  ((MultiplyingContext)_localctx).t3.value;
				}
				break;
			case 4:
				{
				setState(173);
				match(ZERO);
				((MultiplyingContext)_localctx).value =  new Zero();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(182);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MultiplyingContext(_parentctx, _parentState);
					_localctx.m = _prevctx;
					_localctx.m = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_multiplying);
					setState(177);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(178);
					match(NEXT);
					((MultiplyingContext)_localctx).value =  new Successor(((MultiplyingContext)_localctx).m.value);
					}
					} 
				}
				setState(184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return disjunction_sempred((DisjunctionContext)_localctx, predIndex);
		case 3:
			return conjunction_sempred((ConjunctionContext)_localctx, predIndex);
		case 7:
			return term_sempred((TermContext)_localctx, predIndex);
		case 8:
			return adding_sempred((AddingContext)_localctx, predIndex);
		case 9:
			return multiplying_sempred((MultiplyingContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean disjunction_sempred(DisjunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean conjunction_sempred(ConjunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean adding_sempred(AddingContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean multiplying_sempred(MultiplyingContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\22\u00bc\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\2\3\2\3\2\7\2\35\n\2\f\2\16\2 \13\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\5\3*\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\65"+
		"\n\4\f\4\16\48\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5C\n\5\f\5\16"+
		"\5F\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6^\n\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\7\bl\n\b\f\b\16\bo\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\5\bz\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0085\n\t\f\t\16"+
		"\t\u0088\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0093\n\n\f\n\16"+
		"\n\u0096\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00a1"+
		"\n\13\f\13\16\13\u00a4\13\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u00b2\n\13\3\13\3\13\3\13\7\13\u00b7\n\13\f\13"+
		"\16\13\u00ba\13\13\3\13\2\7\6\b\20\22\24\f\2\4\6\b\n\f\16\20\22\24\2\2"+
		"\u00c3\2\26\3\2\2\2\4)\3\2\2\2\6+\3\2\2\2\b9\3\2\2\2\n]\3\2\2\2\f_\3\2"+
		"\2\2\16y\3\2\2\2\20{\3\2\2\2\22\u0089\3\2\2\2\24\u00b1\3\2\2\2\26\27\5"+
		"\4\3\2\27\36\b\2\1\2\30\31\7\6\2\2\31\32\5\4\3\2\32\33\b\2\1\2\33\35\3"+
		"\2\2\2\34\30\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37\3\3\2"+
		"\2\2 \36\3\2\2\2!\"\5\6\4\2\"#\b\3\1\2#*\3\2\2\2$%\5\6\4\2%&\7\7\2\2&"+
		"\'\5\4\3\2\'(\b\3\1\2(*\3\2\2\2)!\3\2\2\2)$\3\2\2\2*\5\3\2\2\2+,\b\4\1"+
		"\2,-\5\b\5\2-.\b\4\1\2.\66\3\2\2\2/\60\f\3\2\2\60\61\7\b\2\2\61\62\5\b"+
		"\5\2\62\63\b\4\1\2\63\65\3\2\2\2\64/\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2"+
		"\66\67\3\2\2\2\67\7\3\2\2\28\66\3\2\2\29:\b\5\1\2:;\5\n\6\2;<\b\5\1\2"+
		"<D\3\2\2\2=>\f\3\2\2>?\7\t\2\2?@\5\n\6\2@A\b\5\1\2AC\3\2\2\2B=\3\2\2\2"+
		"CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\t\3\2\2\2FD\3\2\2\2GH\5\16\b\2HI\b\6\1"+
		"\2I^\3\2\2\2JK\7\n\2\2KL\5\n\6\2LM\b\6\1\2M^\3\2\2\2NO\7\13\2\2OP\5\4"+
		"\3\2PQ\7\f\2\2QR\b\6\1\2R^\3\2\2\2ST\7\21\2\2TU\5\f\7\2UV\5\n\6\2VW\b"+
		"\6\1\2W^\3\2\2\2XY\7\22\2\2YZ\5\f\7\2Z[\5\n\6\2[\\\b\6\1\2\\^\3\2\2\2"+
		"]G\3\2\2\2]J\3\2\2\2]N\3\2\2\2]S\3\2\2\2]X\3\2\2\2^\13\3\2\2\2_`\7\r\2"+
		"\2`a\b\7\1\2a\r\3\2\2\2bc\7\16\2\2cd\b\b\1\2de\7\13\2\2ef\5\20\t\2fm\b"+
		"\b\1\2gh\7\6\2\2hi\5\20\t\2ij\b\b\1\2jl\3\2\2\2kg\3\2\2\2lo\3\2\2\2mk"+
		"\3\2\2\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pq\7\f\2\2qz\3\2\2\2rs\7\16\2\2"+
		"sz\b\b\1\2tu\5\20\t\2uv\7\5\2\2vw\5\20\t\2wx\b\b\1\2xz\3\2\2\2yb\3\2\2"+
		"\2yr\3\2\2\2yt\3\2\2\2z\17\3\2\2\2{|\b\t\1\2|}\5\22\n\2}~\b\t\1\2~\u0086"+
		"\3\2\2\2\177\u0080\f\3\2\2\u0080\u0081\7\3\2\2\u0081\u0082\5\22\n\2\u0082"+
		"\u0083\b\t\1\2\u0083\u0085\3\2\2\2\u0084\177\3\2\2\2\u0085\u0088\3\2\2"+
		"\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\21\3\2\2\2\u0088\u0086"+
		"\3\2\2\2\u0089\u008a\b\n\1\2\u008a\u008b\5\24\13\2\u008b\u008c\b\n\1\2"+
		"\u008c\u0094\3\2\2\2\u008d\u008e\f\3\2\2\u008e\u008f\7\4\2\2\u008f\u0090"+
		"\5\24\13\2\u0090\u0091\b\n\1\2\u0091\u0093\3\2\2\2\u0092\u008d\3\2\2\2"+
		"\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\23"+
		"\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\b\13\1\2\u0098\u0099\7\r\2\2"+
		"\u0099\u009a\7\13\2\2\u009a\u009b\5\20\t\2\u009b\u00a2\b\13\1\2\u009c"+
		"\u009d\7\6\2\2\u009d\u009e\5\20\t\2\u009e\u009f\b\13\1\2\u009f\u00a1\3"+
		"\2\2\2\u00a0\u009c\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7\f"+
		"\2\2\u00a6\u00b2\3\2\2\2\u00a7\u00a8\5\f\7\2\u00a8\u00a9\b\13\1\2\u00a9"+
		"\u00b2\3\2\2\2\u00aa\u00ab\7\13\2\2\u00ab\u00ac\5\20\t\2\u00ac\u00ad\7"+
		"\f\2\2\u00ad\u00ae\b\13\1\2\u00ae\u00b2\3\2\2\2\u00af\u00b0\7\17\2\2\u00b0"+
		"\u00b2\b\13\1\2\u00b1\u0097\3\2\2\2\u00b1\u00a7\3\2\2\2\u00b1\u00aa\3"+
		"\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b8\3\2\2\2\u00b3\u00b4\f\3\2\2\u00b4"+
		"\u00b5\7\20\2\2\u00b5\u00b7\b\13\1\2\u00b6\u00b3\3\2\2\2\u00b7\u00ba\3"+
		"\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\25\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\16\36)\66D]my\u0086\u0094\u00a2\u00b1\u00b8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}