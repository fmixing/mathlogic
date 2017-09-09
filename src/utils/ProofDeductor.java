package utils;

import expression.ClassName;
import expression.Expression;
import expression.Implication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProofDeductor extends Proof {


    public ProofDeductor(String inPath, String outPath) {

        super(inPath, outPath);
    }


    public void deduct() {
        try (PrintWriter out = new PrintWriter(new File(outPath))) {

            if (firstLine != null)
                out.println(firstLine);

            outer:
            for (int currExpr = 0; currExpr < proof.size(); currExpr++) {
                Expression expression = proof.get(currExpr);

                int isAxiom = axioms.isAxiom(expression);
                int isAssumption = assumptions.indexOf(expression);

                if (isAxiom > 0 || isAssumption > 0) {
                    AxOrAssumParser(out, expression);
                    continue;
                }

                if (expression.equals(alphaStatement)) {
                    alphaParser(out, alphaStatement);
                    continue;
                }

                for (int i = currExpr; i >= 0; i--) {
                    if (proof.get(i).getClassName() == ClassName.IMPLICATION
                            && ((Implication) proof.get(i)).getRight().equals(expression)) {
                        int implLeft = proof.indexOf(((Implication) proof.get(i)).getLeft());
                        if (implLeft > -1 && implLeft < currExpr) {
                            MPParser(out, expression, ((Implication) proof.get(i)).getLeft());
                            continue outer;
                        }

                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void AxOrAssumParser(PrintWriter out, Expression expression) {
        out.println(expression);
        out.println(new Implication(expression, new Implication(alphaStatement, expression)));
        out.println(new Implication(alphaStatement, expression));
    }

    private void alphaParser(PrintWriter out, Expression expression) {
        String outLines = ("(a)->((a)->(a))\n" +
                "((a)->((a)->(a)))->((a)->(((a)->(a))->(a)))->((a)->(a))\n" +
                "((a)->(((a)->(a))->(a)))->((a)->(a))\n" +
                "((a)->(((a)->(a))->(a)))\n" +
                "(a)->(a)").replace("a", expression.toString());
        out.println(outLines);
    }

    private void MPParser(PrintWriter out, Expression expression, Expression mpExpression) {
        String outLines = ("((a)->(b1))->(((a)->((b1)->(b2)))->((a)->(b2)))\n" +
                "(((a)->((b1)->(b2)))->((a)->(b2)))\n" +
                "(a)->(b2)").replace("a", alphaStatement.toString()).
                replace("b1", mpExpression.toString()).
                replace("b2", expression.toString());
        out.println(outLines);
    }
}
