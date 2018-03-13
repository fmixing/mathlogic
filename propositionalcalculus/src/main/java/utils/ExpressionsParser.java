package utils;

import HW1.FirstTaskLexer;
import HW1.FirstTaskParser;
import expression.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class ExpressionsParser {

    public static Proof parseProof(String inPath, boolean deduct) {
        Proof proof = new Proof();

        try (Scanner in = new Scanner(new File(inPath))) {
            if (in.hasNext()) {
                String statement = in.next();

                List<Expression> assumptions = tryParseAlphaStatement(statement);

                if (!assumptions.isEmpty()) {
                    proof.setFirstLine(statement);
                    proof.setAssumptions(assumptions);
                    if (deduct) {
                        proof.setAlphaStatement(assumptions.remove(assumptions.size() - 1));
                    }
                    else {
                        proof.setAlphaStatement(assumptions.get(assumptions.size() - 1));
                    }
                    proof.setBetaStatement(getBetaStatement(statement));
                }

                proof.setProof(readFile(inPath));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return proof;
    }

    public static List<Expression> parse(String inPath) {
        return readFile(inPath);
    }

    private static List<Expression> readFile(String path) {
        List<Expression> expressions = new ArrayList<>();

        try (Scanner in = new Scanner(new File(path))) {
            while (in.hasNext()) {
                String statement = in.next();

                if (!statement.contains("|-")) {
                    expressions.add(parseInternal(statement));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return expressions;
    }

    private static List<Expression> tryParseAlphaStatement(String statement) {
        if (!statement.contains("|-")) {
            return Collections.emptyList();
        }

        return Arrays.stream(statement.split("\\|-")[0].split(","))
                .map(ExpressionsParser::parseInternal).collect(Collectors.toList());
    }

    private static Expression getBetaStatement(String statement) {
        String beta = statement.split("\\|-")[1];
        if (beta == null || beta.isEmpty()) {
            throw new RuntimeException("Header should contain expression to prove");
        }
        return parseInternal(beta);
    }

    private static Expression parseInternal(String statement) {
        ANTLRInputStream is = new ANTLRInputStream(statement);
        FirstTaskLexer lexer = new FirstTaskLexer(is);
        TokenStream ts = new CommonTokenStream(lexer);
        FirstTaskParser parser = new FirstTaskParser(ts);
        return parser.expression().expr;
    }

}
