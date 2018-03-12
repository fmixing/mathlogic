package utils;

import expression.*;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RulesChecker {

    static boolean isForallRule(Expression expression, @Nullable StringBuilder stringBuilder, int currExpr,
                                Expression alphaStatement, Map<Expression, Integer> expressions) {
        // F -> P
        // F -> forall x . P
        if (expression.getClassName() != ClassName.IMPLICATION) {
            return false;
        }

        Implication implication = (Implication) expression;

        Expression left = implication.getLeft();
        Expression maybeForall = implication.getRight();

        if (maybeForall.getClassName() != ClassName.FORALL) {
            return false;
        }

        Forall forall = (Forall) maybeForall;

        Implication implicationToCheck = new Implication(left, forall.getExpr());

        if (!expressions.containsKey(implicationToCheck)) {
            return false;
        }

        HashSet<String> freeVariables = new HashSet<>();
        HashSet<String> boundVariables = new HashSet<>();
        left.getFreeVariables(boundVariables, freeVariables);

        if (freeVariables.contains(forall.getVariable().getName())) {
            throw new RuntimeException("(" + (currExpr) + "). переменная " + forall.getVariable()
                    + " входит свободно в формулу " + forall + ".");
        }

        freeVariables.clear();
        boundVariables.clear();

        if (alphaStatement != null) {
            alphaStatement.getFreeVariables(boundVariables, freeVariables);

            if (freeVariables.contains(forall.getVariable().getName())) {
                throw new RuntimeException("(" + (currExpr) + ") используется правило с квантором по переменной "
                        + forall.getVariable() + ", входящей свободно в допущение " + alphaStatement);
            }
        }

        if (stringBuilder != null) {
            stringBuilder.append("правило вывода для @: ").append(expressions.get(implicationToCheck))
                    .append(",").append(currExpr).append(")").append("\n");
        }

        return expressions.containsKey(implicationToCheck);
    }

    static boolean isExistsRule(Expression expression, @Nullable StringBuilder stringBuilder, int currExpr,
                                Expression alphaStatement, Map<Expression, Integer> expressions) {
        // F -> P
        // exists x . F -> P

        if (expression.getClassName() != ClassName.IMPLICATION) {
            return false;
        }

        Implication implication = (Implication) expression;

        Expression maybeExists = implication.getLeft();
        Expression right = implication.getRight();

        if (maybeExists.getClassName() != ClassName.EXISTS) {
            return false;
        }

        Exists exists = (Exists) maybeExists;

        Implication implicationToCheck = new Implication(exists.getExpr(), right);

        HashSet<String> freeVariables = new HashSet<>();
        HashSet<String> boundVariables = new HashSet<>();
        right.getFreeVariables(boundVariables, freeVariables);

        if (freeVariables.contains(exists.getVariable().getName())) {
            throw new RuntimeException("(" + currExpr + "). переменная " + exists.getVariable()
                    + " входит свободно в формулу " + right + ".");
        }

        freeVariables.clear();
        boundVariables.clear();

        if (alphaStatement != null) {
            alphaStatement.getFreeVariables(boundVariables, freeVariables);

            if (freeVariables.contains(exists.getVariable().getName())) {
                throw new RuntimeException("(" + currExpr + "используется правило с квантором по переменной "
                        + exists.getVariable() + ", входящей свободно в допущение " + alphaStatement);
            }
        }

        if (stringBuilder != null) {
            stringBuilder.append("правило вывода для ?: ").append(expressions.get(implicationToCheck))
                    .append(",").append(currExpr).append(")").append("\n");
        }

        return expressions.containsKey(implicationToCheck);
    }

    static void substituteToForallProof(Expression expression, Expression alpha, List<String> forallProof,
                                        List<Expression> ans) {
        // F -> P
        // F -> forall x . P
        if (expression.getClassName() != ClassName.IMPLICATION) {
            throw new IllegalStateException();
        }

        Implication implication = (Implication) expression;

        Expression left = implication.getLeft();
        Expression maybeForall = implication.getRight();

        if (maybeForall.getClassName() != ClassName.FORALL) {
            throw new IllegalStateException();
        }

        Forall forall = (Forall) maybeForall;

        List<Expression> collect = forallProof.stream().map(expr -> expr.replace("A", alpha.toString())
                .replace("B", left.toString())
                .replace("C", forall.getExpr().toString())
                .replace("_", forall.getVariable().toString()))
                .map(PredicateExprParser::predicateParseInternal).collect(Collectors.toList());

        ans.addAll(collect);
    }

    static void substituteToExistsRule(Expression expression, Expression alpha, List<String> existsProof,
                                       List<Expression> ans) {
        // F -> P
        // exists x . F -> P

        if (expression.getClassName() != ClassName.IMPLICATION) {
            throw new IllegalStateException();
        }

        Implication implication = (Implication) expression;

        Expression maybeExists = implication.getLeft();
        Expression right = implication.getRight();

        if (maybeExists.getClassName() != ClassName.EXISTS) {
            throw new IllegalStateException();
        }

        Exists exists = (Exists) maybeExists;

        List<Expression> collect = existsProof.stream().map(expr -> expr.replace("A", alpha.toString())
                    .replace("B", exists.getExpr().toString())
                    .replace("C", right.toString())
                    .replace("_", exists.getVariable().toString()))
                .map(PredicateExprParser::predicateParseInternal).collect(Collectors.toList());

        ans.addAll(collect);
    }
}
