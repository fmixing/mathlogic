package expression;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Variable implements Expression {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public ClassName getClassName() {
        return ClassName.VARIABLE;
    }

    public String getName() {
        return name;
    }

    @Override
    public void getFreeVariables(Set<String> boundVariables, Set<String> freeVariables) {
        if (!boundVariables.contains(name)) {
            freeVariables.add(name);
        }
    }

    @Override
    public OperationType getType() {
        // todo: определить, чем является переменная
        return null;
    }

    @Override
    public boolean isomorphicTo(Expression expression, Map<Expression, Expression> checker) {
        if (checker.containsKey(this)) {
            return checker.get(this).equals(expression);
        }
        checker.put(this, expression);
        return true;
    }

    @Override
    public boolean isFreeToSubstitute(Set<String> freeVariables, Variable variableToChange, Set<String> boundVariables) {
        // Проверяем, что мы находимся в том узле, который хотим заменить
        if (!name.equals(variableToChange.getName())) {
            return true;
        }

        // Если на данную переменную уже где-то навешен квантор, todo: то что??
        if (boundVariables.contains(name)) {
            return true;
        }

        // Теперь для каждой свободной переменной посмотрим, не стала ли она связной на этом уровне
        // True, если сеты не пересекаются
        return Collections.disjoint(freeVariables, boundVariables);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        if (changeNames.containsKey(name)) {
            return new Variable(changeNames.get(name));
        }
        return new Variable(name);
    }
}
