package utils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import expression.*;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Annotator {

    private PredicateAxioms predicateAxioms;

    private ArithmeticAxioms arithmeticAxioms;

    private Axioms axioms;

    private Map<Expression, Integer> expressions = new HashMap<>();

    private Map<Expression, Pair<Integer, Integer>> mp = new HashMap<>();

    private Multimap<Expression, Expression> allImplStartedWithExpr = HashMultimap.create();

    private Map<Expression, Integer> awaiting = new HashMap<>();

    public Annotator(PredicateAxioms predicateAxioms, ArithmeticAxioms arithmeticAxioms, Axioms axioms) {
        this.predicateAxioms = predicateAxioms;
        this.arithmeticAxioms = arithmeticAxioms;
        this.axioms = axioms;
    }

    public void annotate(Proof proofToAnnotate, String outPath) {
        clear();

        List<Expression> proof = proofToAnnotate.getProof();

        StringBuilder stringBuilder = new StringBuilder();

        Expression alphaStatement = proofToAnnotate.getAlphaStatement();

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

            stringBuilder.append("(").append(currExpr + 1).append(")")
                    .append(" ").append(expression).append(" (");

            // Проверяем на то, что аксиомы
            int isAxiom = arithmeticAxioms.isAxiom(expression);
            if (isAxiom >= 0) {
                stringBuilder.append("арифм. акс. ").append(isAxiom).append(")").append("\n");
                continue;
            }

            isAxiom = axioms.isAxiom(expression);

            if (isAxiom >= 0) {
                stringBuilder.append("сх. акс. ").append(isAxiom).append(")").append("\n");
                continue;
            }

            if (predicateAxioms.isForAllAxiom(expression)) {
                stringBuilder.append("сх. акс. ").append(11).append(")").append("\n");
                continue;
            }

            if (predicateAxioms.isExistsAxiom(expression)) {
                stringBuilder.append("сх. акс. ").append(12).append(")").append("\n");
                continue;
            }

            if (predicateAxioms.isInductionAxiom(expression)) {
                stringBuilder.append("акс. индукции").append(")").append("\n");
                continue;
            }

            int isAnnotate = proofToAnnotate.getAssumptions().indexOf(expression);
            if (isAnnotate >= 0 || alphaStatement != null && alphaStatement.equals(expression)) {
                stringBuilder.append("предп.").append(")").append("\n");
                continue;
            }

            // Проверка на MP
            if (mp.containsKey(expression)) {
                Pair<Integer, Integer> indexes = mp.get(expression);
                stringBuilder.append("M.P. ").append(indexes.getKey() + 1)
                        .append(",").append(indexes.getValue() + 1).append(")").append("\n");
                continue;
            }

            if (RulesChecker.isForallRule(expression, stringBuilder, currExpr + 1, alphaStatement, expressions)) {
                continue;
            }

            if (RulesChecker.isExistsRule(expression, stringBuilder, currExpr + 1, alphaStatement, expressions)) {
                continue;
            }

            stringBuilder.append("Не доказано").append(")").append("\n");
            break;
        }

        try (PrintWriter out = new PrintWriter(new File(outPath))) {
            if (proofToAnnotate.getFirstLine() != null) {
                String newHeader = "|-" + (proofToAnnotate.getAlphaStatement() == null
                        ? proofToAnnotate.getBetaStatement()
                        : proofToAnnotate.getAlphaStatement() + "->" + proofToAnnotate.getBetaStatement());
                StringJoiner sj = new StringJoiner(",", "", newHeader);
                proofToAnnotate.getAssumptions().forEach(assumption -> sj.add(assumption.toString()));
                out.println(sj.toString());
            }
            out.print(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clear() {
        expressions.clear();
        mp.clear();
        allImplStartedWithExpr.clear();
        awaiting.clear();
    }
}
