package utils;

import expression.ClassName;
import expression.Expression;
import expression.Implication;

import java.util.*;
import java.util.stream.Collectors;

public class ProofDeductor {

    private Axioms axioms;

    private Map<String, Proof> deductionProofs;

    public ProofDeductor(Axioms axioms, Map<String, Proof> deductionProofs) {
        this.axioms = axioms;
        this.deductionProofs = deductionProofs;
    }

    public List<Expression> deduct(Proof proofToDeduct) {
        List<Expression> ans = new ArrayList<>();

        List<Expression> proof = proofToDeduct.getProof();

        outer:
        for (int currExpr = 0; currExpr < proof.size(); currExpr++) {
            Expression expression = proof.get(currExpr);

            int isAxiom = axioms.isAxiom(expression);
            int isAssumption = proofToDeduct.getAssumptions().indexOf(expression);

            if (expression.equals(proofToDeduct.getAlphaStatement())) {
                alphaParser(proofToDeduct.getAlphaStatement(), ans);
                continue;
            }

            if (isAxiom > 0 || isAssumption >= 0) {
                AxOrAssumParser(expression, proofToDeduct.getAlphaStatement(), ans);
                continue;
            }

            for (int i = currExpr; i >= 0; i--) {
                if (proof.get(i).getClassName() == ClassName.IMPLICATION
                        && ((Implication) proof.get(i)).getRight().equals(expression)) {

                    int implLeft = proof.indexOf(((Implication) proof.get(i)).getLeft());
                    if (implLeft > -1 && implLeft < currExpr) {
                        MPParser(expression, ((Implication) proof.get(i)).getLeft(),
                                proofToDeduct.getAlphaStatement(), ans);
                        continue outer;
                    }
                }
            }
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
}
