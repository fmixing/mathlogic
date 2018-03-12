package expression;

import java.util.Map;
import java.util.Set;

public interface UnOp extends Expression {

    Expression getExpression();

    @Override
    default void getFreeVariables(Set<String> boundVariables, Set<String> freeVariables) {
        getExpression().getFreeVariables(boundVariables, freeVariables);
    }

    @Override
    default boolean isomorphicTo(Expression expression, Map<Expression, Expression> checker) {
        return getClassName() == expression.getClassName()
                && getExpression().isomorphicTo(((UnOp) expression).getExpression(), checker);
    }

    @Override
    default boolean isFreeToSubstitute(Set<String> freeVariables, Variable variableToChange, Set<String> boundVariables) {
        return getExpression().isFreeToSubstitute(freeVariables, variableToChange, boundVariables);
    }
}
