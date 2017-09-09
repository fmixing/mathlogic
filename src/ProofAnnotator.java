import HW1.FirstTaskLexer;
import HW1.FirstTaskParser;
import expression.Expression;
import expression.Implication;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import utils.Axioms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class ProofAnnotator {

    private static String axiomsPath = "src/axioms";

    private List<Expression> axiomsList;
    private List<Expression> assumptions;
    private List<Expression> proof;

    private Expression alphaStatement;
    private String outPath;

    private Axioms axioms;

    List<Expression> implications;

    private String firstLine;

    public ProofAnnotator(String inPath, String outPath) {

        this.outPath = outPath;

        try (Scanner in = new Scanner(new File(axiomsPath))) {
            axiomsList = new ArrayList<>();
            ANTLRInputStream is;
            while (in.hasNext()) {
                String statement = in.next();
                is = new ANTLRInputStream(statement);
                FirstTaskLexer lexer = new FirstTaskLexer(is);
                TokenStream ts = new CommonTokenStream(lexer);
                FirstTaskParser parser = new FirstTaskParser(ts);
                axiomsList.add(parser.expression().expr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        axioms = new Axioms(axiomsList);

        try (Scanner in = new Scanner(new File(inPath))) {
            assumptions = new ArrayList<>();
            proof = new ArrayList<>();
            while (in.hasNext()) {
                String statement = in.next();

                if (statement.contains("|-")) {
                    firstLine = statement;
                    String[] split = statement.split("\\|-")[0].split(",");
                    for (String assumption : split) {
                        assumptions.add(parse(assumption));
                    }
                    alphaStatement = parse(statement.split("\\|-")[1]);
                } else {
                    proof.add(parse(statement));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Expression parse(String statement) {
        ANTLRInputStream is = new ANTLRInputStream(statement);
        FirstTaskLexer lexer = new FirstTaskLexer(is);
        TokenStream ts = new CommonTokenStream(lexer);
        FirstTaskParser parser = new FirstTaskParser(ts);
        return parser.expression().expr;
    }


    //TODO
    public void annotate() {
        try (PrintWriter out = new PrintWriter(new File(outPath))) {
            if (firstLine != null)
                System.out.print(firstLine);

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

                Optional<Expression> isAssumption = assumptions.stream().filter(expression::equals).findAny();

                if (isAssumption.isPresent()) {
                    stringBuilder.append("Предп. ").append(assumptions.indexOf(isAssumption.get()) + 1).append(")").append("\n");
                    out.print(stringBuilder.toString());
                    continue;
                }

                for (int i = currExpr; i >= 0; i--) {
                    if (proof.get(i) instanceof Implication
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
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
