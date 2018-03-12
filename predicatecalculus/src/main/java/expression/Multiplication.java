package expression;

import com.google.common.base.Objects;

import java.util.Map;

public class Multiplication implements BinOp {

    private Expression leftExpr;

    private Expression rightExpr;

    public Multiplication(Expression leftExpr, Expression rightExpr) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public ClassName getClassName() {
        return ClassName.MULTIPLICATION;
    }

    @Override
    public OperationType getType() {
        return OperationType.ARITHMETIC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multiplication that = (Multiplication) o;
        return Objects.equal(leftExpr, that.leftExpr) &&
                Objects.equal(rightExpr, that.rightExpr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(leftExpr, rightExpr);
    }

    @Override
    public String toString() {
        return "(" + leftExpr + "*" + rightExpr + ")";
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
        return new Multiplication(leftExpr.cloneSubstitute(changeNames), rightExpr.cloneSubstitute(changeNames));
    }
}
