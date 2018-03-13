package expression;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Negation implements UnOp {

    private Expression negated;

    public Negation(Expression negated) {
        this.negated = negated;
    }

    @Override
    public Expression getExpression() {
        return negated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Negation negation = (Negation) o;
        return Objects.equals(negated, negation.negated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(negated);
    }

    @Override
    public ClassName getClassName() {
        return ClassName.NEGATION;
    }

    @Override
    public String toString() {
        return "!" + negated.toString();
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        return new Negation(negated.cloneSubstitute(changeNames));
    }
}
