// Generated from FirstTask.g4 by ANTLR 4.5.3

package HW1;
import expression.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FirstTaskParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FirstTaskVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FirstTaskParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(FirstTaskParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FirstTaskParser#disjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisjunction(FirstTaskParser.DisjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FirstTaskParser#conjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConjunction(FirstTaskParser.ConjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FirstTaskParser#negation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(FirstTaskParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by {@link FirstTaskParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(FirstTaskParser.VariableContext ctx);
}