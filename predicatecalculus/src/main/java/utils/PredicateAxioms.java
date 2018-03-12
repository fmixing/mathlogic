package utils;

import expression.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredicateAxioms {

    public final static String predicateAxiomsPath = "predicatecalculus/src/main/java/predicateaxioms";

    private List<Expression> predicateAxioms;

    public PredicateAxioms(List<Expression> predicateAxioms) {
        this.predicateAxioms = predicateAxioms;
    }

    public boolean isForAllAxiom(Expression expression) {
        // forall x P(x) -> P(_y_), здесь _y_ является конкретным
        // То есть то, что слева, является обобщением того, что справа
        // Также необходимо, чтобы _y_ была свободна для подстановки в P вместо x

        String exception;

        if (expression.getClassName() != ClassName.IMPLICATION) {
            exception = "Expression is not isomorfic to forall axiom: " + expression;
            return false;
        }

        Implication implication = (Implication) expression;
        Expression maybeForall = implication.getLeft();
        Expression right = implication.getRight();

        if (maybeForall.getClassName() != ClassName.FORALL) {
            exception = "Expression is not isomorfic to forall axiom: " + expression;
            return false;
        }

        Forall forall = (Forall) maybeForall;

        // todo: сделать адекватную проверку, что выражение слева является общим для выражения справа (с подставленным _y_)

        Map<Expression, Expression> check = new HashMap<>();

        Expression exprToCheck = forall.getExpr();

        if (!exprToCheck.isomorphicTo(right, check)) {
//            logger.debug("Expression is not isomorfic to forall (P(x) !~ P(_y_)): {}", expression);
            return false;
        }

        // это значит, что в P не было зависимости от x -> true
        if (!check.containsKey(forall.getVariable())) {
            return true;
        }

        // todo: сделать проверку, что _y_ свободен для подстановки вместо x
        // todo: проверить, что нет других подстановок

        // находим переменную, которая была вместо x
        Expression _y_ = check.get(forall.getVariable());

        // Пробуем подставить вместо x _y_
        if (exprToCheck.isFreeToSubstitute(forall.getVariable(), _y_)) {
            return true;
        }
        else {
            throw new RuntimeException("терм " + _y_ + " не свободен для подстановки в формулу "
                    + exprToCheck + " вместо переменной " + forall.getVariable() + ".");
        }
    }

    public boolean isExistsAxiom(Expression expression) {
        // P(_x_) -> exists y . P(y), здесь _x_ является конкретным
        // То есть то, что справа, является обобщением того, что слева
        // Также необходимо, чтобы _x_ была свободна для подстановки в P вместо y
        // Вполне корректная аксиома !?a@b?bP(a,b)|Q(a,b)->?a1(!?a@b?bP(a,b)|Q(a,b))

        if (expression.getClassName() != ClassName.IMPLICATION) {
            return false;
        }

        Implication implication = (Implication) expression;
        Expression left = implication.getLeft();
        Expression maybeExists = implication.getRight();

        if (maybeExists.getClassName() != ClassName.EXISTS) {
            return false;
        }

        Exists exists = (Exists) maybeExists;

        // todo: сделать адекватную проверку, что выражение справа является общим для выражения слева (с подставленным _x_)

        Map<Expression, Expression> check = new HashMap<>();

        Expression exprToCheck = exists.getExpr();

        if (!exprToCheck.isomorphicTo(left, check)) {
            return false;
        }

        // это значит, что в P не было зависимости от y
        if (!check.containsKey(exists.getVariable())) {
            return true;
        }

        // todo: сделать проверку, что _x_ свободен для подстановки вместо y

        // находим переменную, которая была вместо y
        Expression _x_ = check.get(exists.getVariable());

        // Подставляем вместо y _x_
        if (exprToCheck.isFreeToSubstitute(exists.getVariable(), _x_)) {
            return true;
        }
        else {
            throw new RuntimeException("терм " + _x_ + " не свободен для подстановки в формулу "
                    + exprToCheck + " вместо переменной " + exists.getVariable() + ".");
        }
    }

    public boolean isInductionAxiom(Expression expression) {
        // F(0) & forall x . (F -> F(x')) -> F
        if (expression.getClassName() != ClassName.IMPLICATION) {
            return false;
        }

        Expression maybeConjunction = ((Implication) expression).getLeft();
        Expression right = ((Implication) expression).getRight();

        if (maybeConjunction.getClassName() != ClassName.CONJUNCTION) {
            return false;
        }

        Conjunction conjunction = (Conjunction) maybeConjunction;

        Expression conjunctionLeft = conjunction.getLeft();
        Expression maybeForall = conjunction.getRight();

        if (maybeForall.getClassName() != ClassName.FORALL) {
            return false;
        }

        Forall forall = (Forall) maybeForall;

        Expression maybeImplication = forall.getExpr();

        if (maybeImplication.getClassName() != ClassName.IMPLICATION) {
            return false;
        }

        Implication implication = (Implication) maybeImplication;

        // todo:
        // Теперь надо смотреть, что F(x) общее для F(0)
        // (проверять на то, что свободно для подстановки не надо, так как 0 не имеет переменных)
        // Что F(x) общее для F(x')
        // Что вместо x действительно подставляется x'
        // Что F в импликациях совпадают

        Expression mainPredicate = implication.getLeft();

        // Проверяем, что главное выражение равно F
        if (!mainPredicate.equals(right)) {
            return false;
        }

        Map<Expression, Expression> checker = new HashMap<>();

        // Проверяем, что главное выражение изоморфно F(0)
        if (!mainPredicate.isomorphicTo(conjunctionLeft, checker)) {
            return false;
        }

        // Проверяем, что в F(0) вместо x действительно 0
        if (!(checker.containsKey(forall.getVariable())
                && checker.remove(forall.getVariable()).equals(new Zero()))) { // проверка на то, что замена была только x
            return false;
        }

        // проверка, что замена была только x
        for (Map.Entry<Expression, Expression> entry : checker.entrySet()) {
            if (!entry.getKey().equals(entry.getValue())) {
                return false;
            }
        }

        checker.clear();

        if (!mainPredicate.isomorphicTo(implication.getRight(), checker)) {
            return false;
        }

        Expression maybeSuccessor = checker.remove(forall.getVariable());

        if (maybeSuccessor == null || maybeSuccessor.getClassName() != ClassName.SUCCESSOR) {
            return false;
        }

        // проверка, что замена была только x
        for (Map.Entry<Expression, Expression> entry : checker.entrySet()) {
            if (!entry.getKey().equals(entry.getValue())) {
                return false;
            }
        }

        Successor successor = (Successor) maybeSuccessor;

        return forall.getVariable().equals(successor.getExpression());
    }

}
