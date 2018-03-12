package expression;

import java.util.Map;
import java.util.Set;

public interface Quantifier extends Expression {

    Variable getVariable();

    Expression getExpr();

    @Override
    default void getFreeVariables(Set<String> boundVariables, Set<String> freeVariables) {
        String name = getVariable().getName();
        boolean isBound = boundVariables.add(name);
        getExpr().getFreeVariables(boundVariables, freeVariables);
        if (isBound) {
            boundVariables.remove(name);
        }
    }

    @Override
    default OperationType getType() {
        return OperationType.LOGIC;
    }

    @Override
    default boolean isomorphicTo(Expression expression, Map<Expression, Expression> checker) {
        return getClassName() == expression.getClassName()
                && getVariable().equals(((Quantifier) expression).getVariable())
                && getExpr().isomorphicTo(((Quantifier) expression).getExpr(), checker);
    }

    @Override
    default boolean isFreeToSubstitute(Set<String> freeVariables, Variable variableToChange, Set<String> boundVariables) {
        String name = getVariable().getName();
        boolean isBound = boundVariables.add(name);
        boolean freeToSubstitute = getExpr().isFreeToSubstitute(freeVariables, variableToChange, boundVariables);
        if (!isBound) {
            boundVariables.remove(name);
        }
        return freeToSubstitute;
    }
}
