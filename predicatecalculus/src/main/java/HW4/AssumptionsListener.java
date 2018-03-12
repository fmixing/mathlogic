// Generated from predicatecalculus/src/main/java/HW4/Assumptions.g4 by ANTLR 4.5.3

package HW4;
import expression.*;
import expression.Predicate;
import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AssumptionsParser}.
 */
public interface AssumptionsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#parseAssumption}.
	 * @param ctx the parse tree
	 */
	void enterParseAssumption(AssumptionsParser.ParseAssumptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#parseAssumption}.
	 * @param ctx the parse tree
	 */
	void exitParseAssumption(AssumptionsParser.ParseAssumptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(AssumptionsParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(AssumptionsParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(AssumptionsParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(AssumptionsParser.DisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(AssumptionsParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(AssumptionsParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(AssumptionsParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(AssumptionsParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(AssumptionsParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(AssumptionsParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(AssumptionsParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(AssumptionsParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(AssumptionsParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(AssumptionsParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#adding}.
	 * @param ctx the parse tree
	 */
	void enterAdding(AssumptionsParser.AddingContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#adding}.
	 * @param ctx the parse tree
	 */
	void exitAdding(AssumptionsParser.AddingContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssumptionsParser#multiplying}.
	 * @param ctx the parse tree
	 */
	void enterMultiplying(AssumptionsParser.MultiplyingContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssumptionsParser#multiplying}.
	 * @param ctx the parse tree
	 */
	void exitMultiplying(AssumptionsParser.MultiplyingContext ctx);
}