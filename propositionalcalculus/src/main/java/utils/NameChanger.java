package utils;

import expression.*;

import java.util.Map;

public class NameChanger {

    static Expression changeNames(Expression expression, Map<String, Expression> newNames) {
        switch (expression.getClassName()) {
            case VARIABLE:
                if (!newNames.containsKey(((Variable) expression).getName())) {
                    throw new IllegalStateException("Map Name -> new expression should contain variable " + ((Variable) expression).getName());
                }
                return newNames.get(((Variable) expression).getName());
            case DISJUNCTION:
                return new Disjunction(changeNames(((Disjunction) expression).getLeft(), newNames),
                                        changeNames(((Disjunction) expression).getRight(), newNames));
            case CONJUNCTION:
                return new Conjunction(changeNames(((Conjunction) expression).getLeft(), newNames),
                                        changeNames(((Conjunction) expression).getRight(), newNames));
            case IMPLICATION:
                return new Implication(changeNames(((Implication) expression).getLeft(), newNames),
                        changeNames(((Implication) expression).getRight(), newNames));
            case NEGATION:
                return new Negation(changeNames(((Negation) expression).getExpression(), newNames));
        }

        throw new RuntimeException("Something went wrong: expression class " + expression.getClass() +
                " should be equal to one of Conjunction, Disjunction, Implication, Negation or Variable classes");
    }
}
