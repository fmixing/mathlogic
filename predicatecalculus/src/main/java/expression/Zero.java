package expression;

import java.util.Map;
import java.util.Set;

public class Zero implements Expression {

    @Override
    public ClassName getClassName() {
        return ClassName.ZERO;
    }

    @Override
    public String toString() {
        return "0";
    }

    @Override
    public void getFreeVariables(Set<String> boundVariables, Set<String> freeVariables) {}

    @Override
    public OperationType getType() {
        return OperationType.ARITHMETIC;
    }

    @Override
    public boolean isomorphicTo(Expression expression, Map<Expression, Expression> checker) {
        return getClassName() == expression.getClassName();
    }

    @Override
    public boolean isFreeToSubstitute(Set<String> freeVariables, Variable variableToChange, Set<String> boundVariables) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return "0".hashCode();
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        return new Zero();
    }
}
