package expression;

import java.util.Map;
import java.util.Set;

public interface BinOp extends Expression {

    Expression getLeft();

    Expression getRight();

    @Override
    default void getFreeVariables(Set<String> boundVariables, Set<String> freeVariables) {
        getLeft().getFreeVariables(boundVariables, freeVariables);
        getRight().getFreeVariables(boundVariables, freeVariables);
    }

    @Override
    default boolean isomorphicTo(Expression expression, Map<Expression, Expression> checker) {
        return getClassName() == expression.getClassName()
                && getRight().isomorphicTo(((BinOp) expression).getRight(), checker)
                && getLeft().isomorphicTo(((BinOp) expression).getLeft(), checker);
    }

    @Override
    default boolean isFreeToSubstitute(Set<String> freeVariables, Variable variableToChange, Set<String> boundVariables) {
        return getLeft().isFreeToSubstitute(freeVariables, variableToChange, boundVariables)
                && getRight().isFreeToSubstitute(freeVariables, variableToChange, boundVariables);
    }
}
