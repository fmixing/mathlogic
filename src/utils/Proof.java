package utils;

import HW1.FirstTaskLexer;
import HW1.FirstTaskParser;
import expression.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proof {

    private static String axiomsPath = "src/axioms";

    protected List<Expression> axiomsList;
    protected List<Expression> assumptions;
    protected List<Expression> proof;

    protected Expression alphaStatement;
    protected String outPath;

    protected Axioms axioms;

    protected String firstLine;

    public Proof(String inPath, String outPath) {

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
                    alphaStatement = assumptions.get(assumptions.size() - 1);
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
}
