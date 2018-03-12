// Generated from predicatecalculus/src/main/java/HW4/Predicates.g4 by ANTLR 4.5.3

package HW4;
import expression.*;
import expression.Predicate;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PredicatesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLUS=1, MUL=2, EQUALS=3, COMMA=4, IMPLIES=5, OR=6, AND=7, NOT=8, OB=9, 
		CB=10, VAR=11, PREDVAR=12, ZERO=13, NEXT=14, FORALL=15, EXISTS=16;
	public static final int
		RULE_expression = 0, RULE_disjunction = 1, RULE_conjunction = 2, RULE_unary = 3, 
		RULE_variable = 4, RULE_predicate = 5, RULE_term = 6, RULE_adding = 7, 
		RULE_multiplying = 8;
	public static final String[] ruleNames = {
		"expression", "disjunction", "conjunction", "unary", "variable", "predicate", 
		"term", "adding", "multiplying"
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
	public String getGrammarFileName() { return "Predicates.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PredicatesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public Expression value;
		public DisjunctionContext d1;
		public DisjunctionContext d2;
		public ExpressionContext e;
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public TerminalNode IMPLIES() { return getToken(PredicatesParser.IMPLIES, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			setState(26);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				((ExpressionContext)_localctx).d1 = disjunction(0);
				((ExpressionContext)_localctx).value =  ((ExpressionContext)_localctx).d1.value;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(21);
				((ExpressionContext)_localctx).d2 = disjunction(0);
				setState(22);
				match(IMPLIES);
				setState(23);
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
		public TerminalNode OR() { return getToken(PredicatesParser.OR, 0); }
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitDisjunction(this);
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
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_disjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(29);
			((DisjunctionContext)_localctx).c1 = conjunction(0);
			((DisjunctionContext)_localctx).value =  ((DisjunctionContext)_localctx).c1.value;
			}
			_ctx.stop = _input.LT(-1);
			setState(39);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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
					setState(32);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(33);
					match(OR);
					setState(34);
					((DisjunctionContext)_localctx).c2 = conjunction(0);
					((DisjunctionContext)_localctx).value =  new Disjunction(((DisjunctionContext)_localctx).d.value, ((DisjunctionContext)_localctx).c2.value);
					}
					} 
				}
				setState(41);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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
		public TerminalNode AND() { return getToken(PredicatesParser.AND, 0); }
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitConjunction(this);
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
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_conjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(43);
			((ConjunctionContext)_localctx).u1 = unary();
			((ConjunctionContext)_localctx).value =  ((ConjunctionContext)_localctx).u1.value;
			}
			_ctx.stop = _input.LT(-1);
			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
					setState(46);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(47);
					match(AND);
					setState(48);
					((ConjunctionContext)_localctx).u2 = unary();
					((ConjunctionContext)_localctx).value =  new Conjunction(((ConjunctionContext)_localctx).c.value, ((ConjunctionContext)_localctx).u2.value);
					}
					} 
				}
				setState(55);
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
		public TerminalNode NOT() { return getToken(PredicatesParser.NOT, 0); }
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TerminalNode OB() { return getToken(PredicatesParser.OB, 0); }
		public TerminalNode CB() { return getToken(PredicatesParser.CB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode FORALL() { return getToken(PredicatesParser.FORALL, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode EXISTS() { return getToken(PredicatesParser.EXISTS, 0); }
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitUnary(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_unary);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				((UnaryContext)_localctx).p = predicate();
				((UnaryContext)_localctx).value =  ((UnaryContext)_localctx).p.value;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				match(NOT);
				setState(60);
				((UnaryContext)_localctx).u1 = unary();
				((UnaryContext)_localctx).value =  new Negation(((UnaryContext)_localctx).u1.value);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				match(OB);
				setState(64);
				((UnaryContext)_localctx).e = expression();
				setState(65);
				match(CB);
				((UnaryContext)_localctx).value =  ((UnaryContext)_localctx).e.value;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(68);
				match(FORALL);
				setState(69);
				((UnaryContext)_localctx).v1 = variable();
				setState(70);
				((UnaryContext)_localctx).u2 = unary();
				((UnaryContext)_localctx).value =  new Forall(((UnaryContext)_localctx).v1.value, ((UnaryContext)_localctx).u2.value);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				match(EXISTS);
				setState(74);
				((UnaryContext)_localctx).v2 = variable();
				setState(75);
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
		public TerminalNode VAR() { return getToken(PredicatesParser.VAR, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
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
		public TerminalNode PREDVAR() { return getToken(PredicatesParser.PREDVAR, 0); }
		public TerminalNode OB() { return getToken(PredicatesParser.OB, 0); }
		public TerminalNode CB() { return getToken(PredicatesParser.CB, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PredicatesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PredicatesParser.COMMA, i);
		}
		public TerminalNode EQUALS() { return getToken(PredicatesParser.EQUALS, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_predicate);
		int _la;
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				((PredicateContext)_localctx).PREDVAR = match(PREDVAR);
				((PredicateContext)_localctx).value =  new Predicate((((PredicateContext)_localctx).PREDVAR!=null?((PredicateContext)_localctx).PREDVAR.getText():null));
				setState(85);
				match(OB);
				setState(86);
				((PredicateContext)_localctx).t3 = term(0);
				((Predicate)_localctx.value).addTerm(((PredicateContext)_localctx).t3.value);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(88);
					match(COMMA);
					setState(89);
					((PredicateContext)_localctx).t4 = term(0);
					((Predicate)_localctx.value).addTerm(((PredicateContext)_localctx).t4.value);
					}
					}
					setState(96);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(97);
				match(CB);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				((PredicateContext)_localctx).PREDVAR = match(PREDVAR);
				((PredicateContext)_localctx).value =  new Predicate((((PredicateContext)_localctx).PREDVAR!=null?((PredicateContext)_localctx).PREDVAR.getText():null));
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				((PredicateContext)_localctx).t1 = term(0);
				setState(102);
				match(EQUALS);
				setState(103);
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
		public TerminalNode PLUS() { return getToken(PredicatesParser.PLUS, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitTerm(this);
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
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(109);
			((TermContext)_localctx).a1 = adding(0);
			((TermContext)_localctx).value =  ((TermContext)_localctx).a1.value;
			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
					setState(112);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(113);
					match(PLUS);
					setState(114);
					((TermContext)_localctx).a2 = adding(0);
					((TermContext)_localctx).value =  new Summation(((TermContext)_localctx).t.value, ((TermContext)_localctx).a2.value);
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
		public TerminalNode MUL() { return getToken(PredicatesParser.MUL, 0); }
		public AddingContext adding() {
			return getRuleContext(AddingContext.class,0);
		}
		public AddingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_adding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterAdding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitAdding(this);
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_adding, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(123);
			((AddingContext)_localctx).m1 = ((AddingContext)_localctx).multiplying = multiplying(0);
			((AddingContext)_localctx).value =  ((AddingContext)_localctx).multiplying.value;
			}
			_ctx.stop = _input.LT(-1);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
					setState(126);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(127);
					match(MUL);
					setState(128);
					((AddingContext)_localctx).m2 = ((AddingContext)_localctx).multiplying = multiplying(0);
					((AddingContext)_localctx).value =  new Multiplication(((AddingContext)_localctx).a.value, ((AddingContext)_localctx).m2.value);
					}
					} 
				}
				setState(135);
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

	public static class MultiplyingContext extends ParserRuleContext {
		public Expression value;
		public MultiplyingContext m;
		public Token VAR;
		public TermContext t1;
		public TermContext t2;
		public VariableContext v;
		public TermContext t3;
		public TerminalNode VAR() { return getToken(PredicatesParser.VAR, 0); }
		public TerminalNode OB() { return getToken(PredicatesParser.OB, 0); }
		public TerminalNode CB() { return getToken(PredicatesParser.CB, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PredicatesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PredicatesParser.COMMA, i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ZERO() { return getToken(PredicatesParser.ZERO, 0); }
		public TerminalNode NEXT() { return getToken(PredicatesParser.NEXT, 0); }
		public MultiplyingContext multiplying() {
			return getRuleContext(MultiplyingContext.class,0);
		}
		public MultiplyingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplying; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).enterMultiplying(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PredicatesListener ) ((PredicatesListener)listener).exitMultiplying(this);
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_multiplying, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(137);
				((MultiplyingContext)_localctx).VAR = match(VAR);
				setState(138);
				match(OB);
				setState(139);
				((MultiplyingContext)_localctx).t1 = term(0);
				((MultiplyingContext)_localctx).value =  new Function((((MultiplyingContext)_localctx).VAR!=null?((MultiplyingContext)_localctx).VAR.getText():null)); ((Function)_localctx.value).addTerm(((MultiplyingContext)_localctx).t1.value);
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(141);
					match(COMMA);
					setState(142);
					((MultiplyingContext)_localctx).t2 = term(0);
					((Function)_localctx.value).addTerm(((MultiplyingContext)_localctx).t2.value);
					}
					}
					setState(149);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(150);
				match(CB);
				}
				break;
			case 2:
				{
				setState(152);
				((MultiplyingContext)_localctx).v = variable();
				((MultiplyingContext)_localctx).value =  ((MultiplyingContext)_localctx).v.value;
				}
				break;
			case 3:
				{
				setState(155);
				match(OB);
				setState(156);
				((MultiplyingContext)_localctx).t3 = term(0);
				setState(157);
				match(CB);
				((MultiplyingContext)_localctx).value =  ((MultiplyingContext)_localctx).t3.value;
				}
				break;
			case 4:
				{
				setState(160);
				match(ZERO);
				((MultiplyingContext)_localctx).value =  new Zero();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(169);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
					setState(164);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(165);
					match(NEXT);
					((MultiplyingContext)_localctx).value =  new Successor(((MultiplyingContext)_localctx).m.value);
					}
					} 
				}
				setState(171);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		case 1:
			return disjunction_sempred((DisjunctionContext)_localctx, predIndex);
		case 2:
			return conjunction_sempred((ConjunctionContext)_localctx, predIndex);
		case 6:
			return term_sempred((TermContext)_localctx, predIndex);
		case 7:
			return adding_sempred((AddingContext)_localctx, predIndex);
		case 8:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\22\u00af\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\35\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\7\3(\n\3\f\3\16\3+\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\66"+
		"\n\4\f\4\16\49\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5Q\n\5\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7_\n\7\f\7\16\7b\13\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7m\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bx\n\b"+
		"\f\b\16\b{\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0086\n\t\f\t"+
		"\16\t\u0089\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0094\n\n\f\n"+
		"\16\n\u0097\13\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a5"+
		"\n\n\3\n\3\n\3\n\7\n\u00aa\n\n\f\n\16\n\u00ad\13\n\3\n\2\7\4\6\16\20\22"+
		"\13\2\4\6\b\n\f\16\20\22\2\2\u00b6\2\34\3\2\2\2\4\36\3\2\2\2\6,\3\2\2"+
		"\2\bP\3\2\2\2\nR\3\2\2\2\fl\3\2\2\2\16n\3\2\2\2\20|\3\2\2\2\22\u00a4\3"+
		"\2\2\2\24\25\5\4\3\2\25\26\b\2\1\2\26\35\3\2\2\2\27\30\5\4\3\2\30\31\7"+
		"\7\2\2\31\32\5\2\2\2\32\33\b\2\1\2\33\35\3\2\2\2\34\24\3\2\2\2\34\27\3"+
		"\2\2\2\35\3\3\2\2\2\36\37\b\3\1\2\37 \5\6\4\2 !\b\3\1\2!)\3\2\2\2\"#\f"+
		"\3\2\2#$\7\b\2\2$%\5\6\4\2%&\b\3\1\2&(\3\2\2\2\'\"\3\2\2\2(+\3\2\2\2)"+
		"\'\3\2\2\2)*\3\2\2\2*\5\3\2\2\2+)\3\2\2\2,-\b\4\1\2-.\5\b\5\2./\b\4\1"+
		"\2/\67\3\2\2\2\60\61\f\3\2\2\61\62\7\t\2\2\62\63\5\b\5\2\63\64\b\4\1\2"+
		"\64\66\3\2\2\2\65\60\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\7"+
		"\3\2\2\29\67\3\2\2\2:;\5\f\7\2;<\b\5\1\2<Q\3\2\2\2=>\7\n\2\2>?\5\b\5\2"+
		"?@\b\5\1\2@Q\3\2\2\2AB\7\13\2\2BC\5\2\2\2CD\7\f\2\2DE\b\5\1\2EQ\3\2\2"+
		"\2FG\7\21\2\2GH\5\n\6\2HI\5\b\5\2IJ\b\5\1\2JQ\3\2\2\2KL\7\22\2\2LM\5\n"+
		"\6\2MN\5\b\5\2NO\b\5\1\2OQ\3\2\2\2P:\3\2\2\2P=\3\2\2\2PA\3\2\2\2PF\3\2"+
		"\2\2PK\3\2\2\2Q\t\3\2\2\2RS\7\r\2\2ST\b\6\1\2T\13\3\2\2\2UV\7\16\2\2V"+
		"W\b\7\1\2WX\7\13\2\2XY\5\16\b\2Y`\b\7\1\2Z[\7\6\2\2[\\\5\16\b\2\\]\b\7"+
		"\1\2]_\3\2\2\2^Z\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2ac\3\2\2\2b`\3\2"+
		"\2\2cd\7\f\2\2dm\3\2\2\2ef\7\16\2\2fm\b\7\1\2gh\5\16\b\2hi\7\5\2\2ij\5"+
		"\16\b\2jk\b\7\1\2km\3\2\2\2lU\3\2\2\2le\3\2\2\2lg\3\2\2\2m\r\3\2\2\2n"+
		"o\b\b\1\2op\5\20\t\2pq\b\b\1\2qy\3\2\2\2rs\f\3\2\2st\7\3\2\2tu\5\20\t"+
		"\2uv\b\b\1\2vx\3\2\2\2wr\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\17\3\2"+
		"\2\2{y\3\2\2\2|}\b\t\1\2}~\5\22\n\2~\177\b\t\1\2\177\u0087\3\2\2\2\u0080"+
		"\u0081\f\3\2\2\u0081\u0082\7\4\2\2\u0082\u0083\5\22\n\2\u0083\u0084\b"+
		"\t\1\2\u0084\u0086\3\2\2\2\u0085\u0080\3\2\2\2\u0086\u0089\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\21\3\2\2\2\u0089\u0087\3\2\2"+
		"\2\u008a\u008b\b\n\1\2\u008b\u008c\7\r\2\2\u008c\u008d\7\13\2\2\u008d"+
		"\u008e\5\16\b\2\u008e\u0095\b\n\1\2\u008f\u0090\7\6\2\2\u0090\u0091\5"+
		"\16\b\2\u0091\u0092\b\n\1\2\u0092\u0094\3\2\2\2\u0093\u008f\3\2\2\2\u0094"+
		"\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2"+
		"\2\2\u0097\u0095\3\2\2\2\u0098\u0099\7\f\2\2\u0099\u00a5\3\2\2\2\u009a"+
		"\u009b\5\n\6\2\u009b\u009c\b\n\1\2\u009c\u00a5\3\2\2\2\u009d\u009e\7\13"+
		"\2\2\u009e\u009f\5\16\b\2\u009f\u00a0\7\f\2\2\u00a0\u00a1\b\n\1\2\u00a1"+
		"\u00a5\3\2\2\2\u00a2\u00a3\7\17\2\2\u00a3\u00a5\b\n\1\2\u00a4\u008a\3"+
		"\2\2\2\u00a4\u009a\3\2\2\2\u00a4\u009d\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5"+
		"\u00ab\3\2\2\2\u00a6\u00a7\f\3\2\2\u00a7\u00a8\7\20\2\2\u00a8\u00aa\b"+
		"\n\1\2\u00a9\u00a6\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\23\3\2\2\2\u00ad\u00ab\3\2\2\2\r\34)\67P`ly\u0087"+
		"\u0095\u00a4\u00ab";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}