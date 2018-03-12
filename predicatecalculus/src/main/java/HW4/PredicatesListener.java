// Generated from predicatecalculus/src/main/java/HW4/Predicates.g4 by ANTLR 4.5.3

package HW4;
import expression.*;
import expression.Predicate;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PredicatesParser}.
 */
public interface PredicatesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PredicatesParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PredicatesParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(PredicatesParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(PredicatesParser.DisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(PredicatesParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(PredicatesParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(PredicatesParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(PredicatesParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(PredicatesParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(PredicatesParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(PredicatesParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(PredicatesParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(PredicatesParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(PredicatesParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#adding}.
	 * @param ctx the parse tree
	 */
	void enterAdding(PredicatesParser.AddingContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#adding}.
	 * @param ctx the parse tree
	 */
	void exitAdding(PredicatesParser.AddingContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicatesParser#multiplying}.
	 * @param ctx the parse tree
	 */
	void enterMultiplying(PredicatesParser.MultiplyingContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicatesParser#multiplying}.
	 * @param ctx the parse tree
	 */
	void exitMultiplying(PredicatesParser.MultiplyingContext ctx);
}