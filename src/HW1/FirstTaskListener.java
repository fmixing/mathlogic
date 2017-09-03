// Generated from FirstTask.g4 by ANTLR 4.5.3

package HW1;
import expression.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FirstTaskParser}.
 */
public interface FirstTaskListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FirstTaskParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FirstTaskParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstTaskParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FirstTaskParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FirstTaskParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(FirstTaskParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstTaskParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(FirstTaskParser.DisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FirstTaskParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(FirstTaskParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstTaskParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(FirstTaskParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FirstTaskParser#negation}.
	 * @param ctx the parse tree
	 */
	void enterNegation(FirstTaskParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstTaskParser#negation}.
	 * @param ctx the parse tree
	 */
	void exitNegation(FirstTaskParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FirstTaskParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(FirstTaskParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstTaskParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(FirstTaskParser.VariableContext ctx);
}