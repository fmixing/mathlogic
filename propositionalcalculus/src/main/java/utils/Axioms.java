package utils;

import expression.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Axioms {

    public final static String axiomsPath = "propositionalcalculus/src/main/java/axioms";

    private List<Expression> axioms;

    private Map<String, Expression> substitution = new HashMap<>();

    public Axioms(List<Expression> axioms) {
        this.axioms = axioms;
    }

    int isAxiom(Expression expression) {
        Optional<Expression> first = axioms.stream().filter(v -> {
            substitution.clear();
            return check(expression, v);
        }).findFirst();

        return first.map(expr -> axioms.indexOf(expr) + 1).orElse(-1);
    }

    private boolean check(Expression expression, Expression axiom) {
        if (axiom.getClassName() == ClassName.VARIABLE) {
            if (substitution.containsKey(((Variable) axiom).getName())) {
                return substitution.get(((Variable) axiom).getName()).equals(expression);
            }
            substitution.put(((Variable) axiom).getName(), expression);
            return true;
        }
        if (!expression.getClass().equals(axiom.getClass()))
            return false;
        switch (expression.getClassName()) {
            case CONJUNCTION:
                return check(((Conjunction) expression).getLeft(), ((Conjunction) axiom).getLeft())
                        && check(((Conjunction) expression).getRight(), ((Conjunction) axiom).getRight());
            case DISJUNCTION:
                return check(((Disjunction) expression).getLeft(), ((Disjunction) axiom).getLeft())
                        && check(((Disjunction) expression).getRight(), ((Disjunction) axiom).getRight());
            case IMPLICATION:
                return check(((Implication) expression).getLeft(), ((Implication) axiom).getLeft())
                        && check(((Implication) expression).getRight(), ((Implication) axiom).getRight());
            case NEGATION:
                return check(((Negation) expression).getExpression(), ((Negation) axiom).getExpression());
            case VARIABLE:
                return true;
        }

        throw new RuntimeException("Something went wrong: expression class " + expression.getClass() +
                " should be equal to one of Conjunction, Disjunction, Implication, Negation or Variable classes");
    }
}
