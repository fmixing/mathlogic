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
import java.util.stream.Collectors;

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
                }
                else {
                    proof.add(parse(statement));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        implications = proof.stream().filter(v -> v instanceof Implication).collect(Collectors.toList());

    }

    private Expression parse(String statement) {
        ANTLRInputStream is = new ANTLRInputStream(statement);
        FirstTaskLexer lexer = new FirstTaskLexer(is);
        TokenStream ts = new CommonTokenStream(lexer);
        FirstTaskParser parser = new FirstTaskParser(ts);
        return parser.expression().expr;
    }


    public void annotate() {
        try (PrintWriter out = new PrintWriter(new File(outPath))) {
            out.print(firstLine);
            proof.forEach(expression -> {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(").append(proof.indexOf(expression) + 1).append(")")
                .append(" ").append(expression).append(" (");

                int isAxiom = axioms.isAxiom(expression);

                if (isAxiom > 0) {
                    stringBuilder.append("Сх. акс. ").append(isAxiom).append(")").append("\n");
                    out.print(stringBuilder.toString());
                    return;
                }

                Optional<Expression> isAssumption = assumptions.stream().filter(expression::equals).findAny();

                if (isAssumption.isPresent()) {
                    stringBuilder.append("Предп. ").append(assumptions.indexOf(isAssumption.get()) + 1).append(")").append("\n");
                    out.print(stringBuilder.toString());
                    return;
                }

                Optional<Expression> isMP = implications.stream().
                        filter(v -> proof.contains(((Implication) v).getLeft()) && ((Implication) v).getRight().equals(expression)).findAny();

                if (isMP.isPresent()) {
                    stringBuilder.append("M.P. ").append(proof.indexOf(isMP.get()) + 1).append(", ").
                            append(proof.indexOf(((Implication) isMP.get()).getLeft()) + 1).append(")").append("\n");
                    out.print(stringBuilder.toString());
                    return;
                }

                stringBuilder.append("Не доказано").append(")").append("\n");
                out.print(stringBuilder.toString());
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
