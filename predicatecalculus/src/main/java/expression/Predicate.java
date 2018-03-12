package expression;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Predicate implements Functionality {

    private String name;

    private List<Expression> terms;

    public Predicate(String name) {
        this.name = name;
        this.terms = new ArrayList<>();
    }

    @Override
    public ClassName getClassName() {
        return ClassName.PREDICATE;
    }

    public void addTerm(Expression expression) {
        terms.add(expression);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predicate predicate = (Predicate) o;
        return Objects.equal(name, predicate.name) &&
                Objects.equal(terms, predicate.terms);
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
            Predicate predicate = new Predicate(changeNames.get(name));
            terms.forEach(term -> predicate.addTerm(term.cloneSubstitute(changeNames)));
            return predicate;
        }

        Predicate predicate = new Predicate(name);
        terms.forEach(term -> predicate.addTerm(term.cloneSubstitute(changeNames)));
        return predicate;
    }
}
