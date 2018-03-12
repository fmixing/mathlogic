package expression;

import com.google.common.base.Objects;

import java.util.Map;

public class Exists implements Quantifier {

    private Variable variable; // exists a . expr(a)

    private Expression expr;

    public Exists(Variable variable, Expression expr) {
        this.variable = variable;
        this.expr = expr;
    }

    @Override
    public ClassName getClassName() {
        return ClassName.EXISTS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exists exists = (Exists) o;
        return Objects.equal(variable, exists.variable) &&
                Objects.equal(expr, exists.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(variable, expr);
    }

    @Override
    public String toString() {
        return "?" + variable + expr;
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
