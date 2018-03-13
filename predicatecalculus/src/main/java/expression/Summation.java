package expression;

import com.google.common.base.Objects;

import java.util.Map;

public class Summation implements BinOp {

    private Expression leftExpr;

    private Expression rightExpr;

    public Summation(Expression leftExpr, Expression rightExpr) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public ClassName getClassName() {
        return ClassName.SUMMATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summation summation = (Summation) o;
        return Objects.equal(leftExpr, summation.leftExpr) &&
                Objects.equal(rightExpr, summation.rightExpr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(leftExpr, rightExpr);
    }

    @Override
    public String toString() {
        return "(" + leftExpr + "+" + rightExpr + ")";
    }

    @Override
    public Expression getLeft() {
        return leftExpr;
    }

    @Override
    public Expression getRight() {
        return rightExpr;
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        return new Summation(leftExpr.cloneSubstitute(changeNames), rightExpr.cloneSubstitute(changeNames));
    }
}
