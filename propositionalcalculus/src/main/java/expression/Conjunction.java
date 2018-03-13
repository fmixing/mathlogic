package expression;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Conjunction implements BinOp {

    private Expression left; //Conjunction

    private Expression right; //Negation

    public Conjunction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression getLeft() {
        return left;
    }

    @Override
    public Expression getRight() {
        return right;
    }

    @Override
    public ClassName getClassName() {
        return ClassName.CONJUNCTION;
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        return new Conjunction(left.cloneSubstitute(changeNames), right.cloneSubstitute(changeNames));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conjunction that = (Conjunction) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "&" + right.toString() + ")";
    }
}
