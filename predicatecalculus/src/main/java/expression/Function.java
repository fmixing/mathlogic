package expression;

import com.google.common.base.Objects;

import java.util.*;
import java.util.stream.Collectors;

public class Function implements Functionality {

    private String name;

    private List<Expression> terms;

    public Function(String name) {
        this.name = name;
        this.terms = new ArrayList<>();
    }

    @Override
    public ClassName getClassName() {
        return ClassName.FUNCTION;
    }

    public void addTerm(Expression expression) {
        terms.add(expression);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equal(name, function.name) &&
                Objects.equal(terms, function.terms);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, terms);
    }

    @Override
    public String toString() {
        if (terms.isEmpty()) {
            return name;
        }
        StringJoiner sj = new StringJoiner(",", "(", ")");
        terms.forEach(term -> sj.add(term.toString()));
        return name + sj.toString();
    }

    @Override
    public List<Expression> getTerms() {
        return terms;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Expression cloneSubstitute(Map<String, String> changeNames) {
        if (changeNames.containsKey(name)) {
            Function function = new Function(changeNames.get(name));
            terms.forEach(term -> function.addTerm(term.cloneSubstitute(changeNames)));
            return function;
        }

        Function function = new Function(name);
        terms.forEach(term -> function.addTerm(term.cloneSubstitute(changeNames)));
        return function;
    }
}
