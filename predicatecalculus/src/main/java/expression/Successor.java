package expression;

import com.google.common.base.Objects;

import java.util.Map;

public class Successor implements UnOp {

    private Expression expr;

    public Successor(Expression expr) {
        this.expr = expr;
    }

    @Override
    public ClassName getClassName() {
        return ClassName.SUCCESSOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Successor successor = (Successor) o;
        return Objects.equal(expr, successor.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(expr);
    }

    @Override
    public String toString() {
        return expr + "'";
    }

    @Override
    public Expression getExpression() {
        return expr;
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        return new Successor(expr.cloneSubstitute(changeNames));
    }
}
