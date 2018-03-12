package utils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import expression.ClassName;
import expression.Expression;
import expression.Implication;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Deductor {

    private PredicateAxioms predicateAxioms;

    private ArithmeticAxioms arithmeticAxioms;

    private Axioms axioms;

    private Map<String, Proof> deductionProofs;

    private Map<String, List<String>> rules;

    private Map<Expression, Integer> expressions = new HashMap<>();

    private Map<Expression, Pair<Integer, Integer>> mp = new HashMap<>();

    private Multimap<Expression, Expression> allImplStartedWithExpr = HashMultimap.create();

    private Map<Expression, Integer> awaiting = new HashMap<>();

    public Deductor(PredicateAxioms predicateAxioms, ArithmeticAxioms arithmeticAxioms, Axioms axioms,
                    Map<String, Proof> deductionProofs, Map<String, List<String>> rules) {
        this.predicateAxioms = predicateAxioms;
        this.arithmeticAxioms = arithmeticAxioms;
        this.axioms = axioms;
        this.deductionProofs = deductionProofs;

        this.rules = rules;
    }

    public List<Expression> deduct(Proof proofToDeduct) {
        clear();

        List<Expression> ans = new ArrayList<>();

        List<Expression> proof = proofToDeduct.getProof();
        List<Expression> assumptions = proofToDeduct.getAssumptions();
        Expression alphaStatement = proofToDeduct.getAlphaStatement();

        if (alphaStatement == null) {
            return proof;
        }

        for (int currExpr = 0; currExpr < proof.size(); currExpr++) {
            Expression expression = proof.get(currExpr);

            // Добавляем всевозможные MP
            if (!expressions.containsKey(expression)) {
                expressions.put(expression, currExpr);

                if (allImplStartedWithExpr.containsKey(expression)) {
                    Collection<Expression> implications = allImplStartedWithExpr.get(expression);
                    int i = currExpr;

                    // Кладем в мапу MP те выражения, которые начинались с данного
                    implications.forEach(exprToMp -> {
                        if (!expressions.containsKey(exprToMp)) {
                            mp.put(exprToMp, new Pair<>(i, awaiting.get(exprToMp)));
                            awaiting.remove(exprToMp);
                        }
                    });

                }
            }

            if (expression.getClassName() == ClassName.IMPLICATION) {
                Implication implication = (Implication) expression;
                Expression left = implication.getLeft();

                // Если импликация, и уже есть expressions в рассмотренном множестве, то можем добавить MP
                if (expressions.containsKey(left)) {
                    mp.put(implication.getRight(), new Pair<>(expressions.get(left), currExpr));
                }
                // Иначе будем ждать left для данной импликации
                else {
                    allImplStartedWithExpr.put(left, implication.getRight());
                    awaiting.put(implication.getRight(), currExpr);
                }
            }

            int isAxiom = arithmeticAxioms.isAxiom(expression);
            if (isAxiom >= 0) {
                AxOrAssumParser(expression, alphaStatement, ans);
                continue;
            }

            isAxiom = axioms.isAxiom(expression);

            if (isAxiom >= 0) {
                AxOrAssumParser(expression, alphaStatement, ans);
                continue;
            }

            if (predicateAxioms.isForAllAxiom(expression)) {
                AxOrAssumParser(expression, alphaStatement, ans);
                continue;
            }

            if (predicateAxioms.isExistsAxiom(expression)) {
                AxOrAssumParser(expression, alphaStatement, ans);
                continue;
            }

            if (predicateAxioms.isInductionAxiom(expression)) {
                AxOrAssumParser(expression, alphaStatement, ans);
                continue;
            }

            int isAnnotate = assumptions.indexOf(expression);
            if (isAnnotate >= 0 || alphaStatement.equals(expression)) {
                AxOrAssumParser(expression, alphaStatement, ans);
                continue;
            }

            if (expression.equals(proofToDeduct.getAlphaStatement()))
            {
                alphaParser(proofToDeduct.getAlphaStatement(), ans);
                continue;
            }

            // Проверка на MP
            if (mp.containsKey(expression)) {
                Pair<Integer, Integer> indexes = mp.get(expression);
                Integer leftIndex = indexes.getKey();
                Expression left;
                if (leftIndex != null && leftIndex >= 0) {
                    left = proof.get(leftIndex);
                }
                else {
                    throw new RuntimeException("mp map should contain for (a->b) pairs of indexed (index a + 1, index b + 1): "
                            + indexes);
                }

                MPParser(expression, left, alphaStatement, ans);
                continue;
            }

            // проверяем правила вывода новые

            if (RulesChecker.isForallRule(expression, null, currExpr + 1, alphaStatement, expressions)) {
                List<String> forallProof = rules.get("@proof");
                RulesChecker.substituteToForallProof(expression, alphaStatement, forallProof, ans);
                continue;
            }

            if (RulesChecker.isExistsRule(expression, null, currExpr + 1, alphaStatement, expressions)) {
                List<String> existsProof = rules.get("?proof");
                RulesChecker.substituteToExistsRule(expression, alphaStatement, existsProof, ans);
                continue;
            }
            throw new RuntimeException("(" + (currExpr + 1) + ") выражение " + expression
                    + " не является аксиомой и не выводится из предыдущих.");
        }
        return ans;
    }

    private void AxOrAssumParser(Expression expression, Expression alphaStatement, List<Expression> ans) {
        ans.addAll(Arrays.asList(expression,
                new Implication(expression, new Implication(alphaStatement, expression)),
                new Implication(alphaStatement, expression)));
    }

    /**
     * Доказательство, что |- a -> a
     */
    private void alphaParser(Expression alpha, List<Expression> ans) {
        Map<String, Expression> substitution = new HashMap<>();
        substitution.put("a", alpha);

        ans.addAll(deductionProofs.get("alpha").getProof().stream()
                .map(expr -> NameChanger.changeNames(expr, substitution))
                .collect(Collectors.toList()));
    }

    /**
     * Доказательство по MP (когда текущее высказывание выводится из двух предыдущих по MP)
     */
    private void MPParser(Expression expression, Expression mpExpression, Expression alphaStatement, List<Expression> ans) {
        Map<String, Expression> substitution = new HashMap<>();
        substitution.put("a", alphaStatement);
        substitution.put("b1", mpExpression);
        substitution.put("b2", expression);

        ans.addAll(deductionProofs.get("mp").getProof().stream()
                .map(expr -> NameChanger.changeNames(expr, substitution))
                .collect(Collectors.toList()));
    }

    private void clear() {
        expressions.clear();
        mp.clear();
        allImplStartedWithExpr.clear();
        awaiting.clear();
    }
}
