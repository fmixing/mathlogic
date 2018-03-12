package expression;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface Expression {

    ClassName getClassName();

    void getFreeVariables(Set<String> boundVariables,  Set<String> freeVariables);

    OperationType getType();

    // Мапа, в которой будут лежать замены переменных этого выражения подвыражениями expression
    boolean isomorphicTo(Expression expression, Map<Expression, Expression> checker);

    boolean isFreeToSubstitute(Set<String> freeVariables, Variable variableToChange, Set<String> boundVariables);

    default boolean isFreeToSubstitute(Variable variableToChange, Expression substitution) {
        Set<String> freeVariables = new HashSet<>();
        // Нужно посмотреть, что не станет связным ни одно вхождение свободной переменной в substitution
        substitution.getFreeVariables(new HashSet<>(), freeVariables);

        return variableToChange.equals(substitution) || isFreeToSubstitute(freeVariables, variableToChange, new HashSet<>());

    }

    Expression cloneSubstitute(Map<String, String> changeNames);
}
