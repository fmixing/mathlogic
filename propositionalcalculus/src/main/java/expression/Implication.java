package expression;

import java.util.Map;
import java.util.Objects;

public class Implication implements BinOp {

    private Expression left; //Disjunction
    private Expression right; //Expression

    public Implication(Expression left, Expression right) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Implication that = (Implication) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "->" + right.toString() + ")";
    }

    @Override
    public ClassName getClassName() {
        return ClassName.IMPLICATION;
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        return new Implication(left.cloneSubstitute(changeNames), right.cloneSubstitute(changeNames));
    }
}
