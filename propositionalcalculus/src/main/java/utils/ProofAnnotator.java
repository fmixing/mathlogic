package utils;

import expression.ClassName;
import expression.Expression;
import expression.Implication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ProofAnnotator {

    private Axioms axioms;

    public ProofAnnotator(Axioms axioms) {

        this.axioms = axioms;
    }

    public void annotate(Proof proofToAnnotate, String outPath) {
        try (PrintWriter out = new PrintWriter(new File(outPath))) {
            List<Expression> proof = proofToAnnotate.getProof();
            if (proofToAnnotate.getFirstLine() != null)
                out.println(proofToAnnotate.getFirstLine());

            outer:
            for (int currExpr = 0; currExpr < proof.size(); currExpr++) {
                Expression expression = proof.get(currExpr);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(").append(currExpr + 1).append(")")
                        .append(" ").append(expression).append(" (");

                int isAxiom = axioms.isAxiom(expression);

                if (isAxiom > 0) {
                    stringBuilder.append("Сх. акс. ").append(isAxiom).append(")").append("\n");
                    out.print(stringBuilder.toString());
                    continue;
                }

                int isAssumption = proofToAnnotate.getAssumptions().indexOf(expression);

                if (isAssumption >= 0) {
                    stringBuilder.append("Предп. ").append(isAssumption + 1).append(")").append("\n");
                    out.print(stringBuilder.toString());
                    continue;
                }

                for (int i = currExpr; i >= 0; i--) {
                    if (proof.get(i).getClassName() == ClassName.IMPLICATION
                            && ((Implication) proof.get(i)).getRight().equals(expression)) {
                        int implLeft = proof.indexOf(((Implication) proof.get(i)).getLeft());
                        if (implLeft > -1 && implLeft < currExpr) {
                            stringBuilder.append("M.P. ").append(i + 1).append(", ").
                                    append(implLeft + 1).append(")").append("\n");
                            out.print(stringBuilder.toString());
                            continue outer;
                        }
                    }
                }

                stringBuilder.append("Не доказано").append(")").append("\n");
                out.print(stringBuilder.toString());
                break;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
