package utils;

import expression.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Axioms {

    private List<Expression> axioms;

    private Map<String, Expression> substitution;

    public Axioms(List<Expression> axioms) {
        this.axioms = axioms;
        substitution = new HashMap<>();
    }

    public int isAxiom(Expression expression) {
        Optional<Expression> first = axioms.stream().filter(v ->  {
            substitution.clear();
            return this.check(expression, v);
        }).findFirst();

        return first.map(expression1 -> axioms.indexOf(expression1) + 1).orElse(-1);
    }

    private boolean check(Expression expression, Expression axiom) {
        if (axiom instanceof Variable) {
            if (substitution.containsKey(((Variable) axiom).getName())) {
                return substitution.get(((Variable) axiom).getName()).equals(expression);
            }
            substitution.put(((Variable) axiom).getName(), expression);
            return true;
        }
        if (!expression.getClass().equals(axiom.getClass()))
            return false;
        if (expression instanceof Conjunction) {
            return check(((Conjunction) expression).getLeft(), ((Conjunction) axiom).getLeft())
                    && check(((Conjunction) expression).getRight(), ((Conjunction) axiom).getRight());
        }
        else if (expression instanceof Disjunction) {
            return check(((Disjunction) expression).getLeft(), ((Disjunction) axiom).getLeft())
                    && check(((Disjunction) expression).getRight(), ((Disjunction) axiom).getRight());
        }
        else if (expression instanceof Implication) {
            return check(((Implication) expression).getLeft(), ((Implication) axiom).getLeft())
                    && check(((Implication) expression).getRight(), ((Implication) axiom).getRight());
        }
        else if (expression instanceof Negation) {
            return check(((Negation) expression).getNegated(), ((Negation) axiom).getNegated());
        }
        else if (expression instanceof Variable) {
            return true;
        }

        throw new RuntimeException("Something went wrong: expression class " + expression.getClass() +
                " should be equal to one of Conjunction, Disjunction, Implication, Negation or Variable classes");
    }
}
