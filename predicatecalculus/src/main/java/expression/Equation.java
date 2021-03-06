package expression;

import com.google.common.base.Objects;

import java.util.Map;

public class Equation implements BinOp {

    private Expression leftExpr;

    private Expression rightExpr;

    public Equation(Expression leftExpr, Expression rightExpr) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public ClassName getClassName() {
        return ClassName.EQUATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return Objects.equal(leftExpr, equation.leftExpr) &&
                Objects.equal(rightExpr, equation.rightExpr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(leftExpr, rightExpr);
    }

    @Override
    public String toString() {
        return leftExpr + "=" + rightExpr;
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
        return new Equation(leftExpr.cloneSubstitute(changeNames), rightExpr.cloneSubstitute(changeNames));
    }
}
