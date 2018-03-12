package expression;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Disjunction implements BinOp {

    private Expression left; //Disjunction

    private Expression right; //Conjunction

    public Disjunction(Expression left, Expression right) {
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
        Disjunction that = (Disjunction) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "|" + right.toString() + ")";
    }

    @Override
    public ClassName getClassName() {
        return ClassName.DISJUNCTION;
    }

    @Override
    public OperationType getType() {
        return OperationType.LOGIC;
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        return new Disjunction(left.cloneSubstitute(changeNames), right.cloneSubstitute(changeNames));
    }
}
