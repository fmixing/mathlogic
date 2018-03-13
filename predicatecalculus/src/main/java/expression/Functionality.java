package expression;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Functionality extends Expression {

    String getName();

    List<Expression> getTerms();

    @Override
    default void getFreeVariables(Set<String> boundVariables, Set<String> freeVariables) {
        getTerms().forEach(e -> e.getFreeVariables(boundVariables, freeVariables));
    }

    @Override
    default boolean isomorphicTo(Expression expression, Map<Expression, Expression> checker) {
        if (getClassName() != expression.getClassName()) {
            return false;
        }

        Functionality functionality = (Functionality) expression;

        if (!getName().equals(functionality.getName())) {
            return false;
        }

        List<Expression> terms = getTerms();

        List<Expression> functionalityTerms = functionality.getTerms();

        if (terms.size() != functionalityTerms.size()) {
            return false;
        }

        for (int i = 0; i < terms.size(); i++) {
            if (!terms.get(i).isomorphicTo(functionalityTerms.get(i), checker)) {
                return false;
            }
        }

        return true;
    }

    @Override
    default boolean isFreeToSubstitute(Set<String> freeVariables, Variable variableToChange, Set<String> boundVariables) {

        List<Expression> terms = getTerms();

        for (Expression term : terms) {
            if (!term.isFreeToSubstitute(freeVariables, variableToChange, boundVariables)) {
                return false;
            }
        }

        return true;
    }
}
