package expression;

import com.google.common.base.Objects;

import java.util.Map;

public class Forall implements Quantifier {

    private Variable variable; // forall a . expr(a)

    private Expression expr;

    public Forall(Variable variable, Expression expr) {
        this.variable = variable;
        this.expr = expr;
    }

    @Override
    public ClassName getClassName() {
        return ClassName.FORALL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forall forall = (Forall) o;
        return Objects.equal(variable, forall.variable) &&
                Objects.equal(expr, forall.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(variable, expr);
    }

    @Override
    public String toString() {
        return "@" + variable + expr;
    }

    @Override
    public Variable getVariable() {
        return variable;
    }

    @Override
    public Expression getExpr() {
        return expr;
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        if (changeNames.containsKey(variable.getName())) {
            return new Exists(new Variable(changeNames.get(variable.getName())), expr.cloneSubstitute(changeNames));
        }

        return new Exists(new Variable(variable.getName()), expr.cloneSubstitute(changeNames));
    }
}
