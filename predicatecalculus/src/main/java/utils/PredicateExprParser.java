package utils;

import HW4.AssumptionsLexer;
import HW4.AssumptionsParser;
import HW4.PredicatesLexer;
import HW4.PredicatesParser;
import expression.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class PredicateExprParser {

    public static Proof parsePredicateProof(String inPath) {
        Proof proof = new Proof();

        try (Scanner in = new Scanner(new File(inPath))) {
            if (in.hasNext()) {
                String statement = in.next();

                List<Expression> assumptions = tryParseAlphaStatement(statement);

                if (!assumptions.isEmpty()) {
                    proof.setFirstLine(statement);
                    proof.setAssumptions(assumptions);
                    proof.setAlphaStatement(assumptions.remove(assumptions.size() - 1));
                    proof.setBetaStatement(getBetaStatement(statement));
                }

                proof.setProof(predicateReadFile(inPath));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return proof;
    }

    public static List<Expression> predicateParse(String inPath) {
        return predicateReadFile(inPath);
    }

    private static List<Expression> predicateReadFile(String path) {
        List<Expression> expressions = new ArrayList<>();

        try (Scanner in = new Scanner(new File(path))) {
            while (in.hasNext()) {
                String statement = in.next();

                if (!statement.contains("|-")) {
                    expressions.add(predicateParseInternal(statement));
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

        return parseAssumptions(statement.split("\\|-")[0]);
    }

    private static Expression getBetaStatement(String statement) {
        String beta = statement.split("\\|-")[1];
        if (beta == null || beta.isEmpty()) {
            throw new RuntimeException("Header should contain expression to prove");
        }
        return predicateParseInternal(beta);
    }

    public static Expression predicateParseInternal(String statement) {
        ANTLRInputStream is = new ANTLRInputStream(statement);
        PredicatesLexer lexer = new PredicatesLexer(is);
        TokenStream ts = new CommonTokenStream(lexer);
        PredicatesParser parser = new PredicatesParser(ts);
        return parser.expression().value;
    }

    private static List<Expression> parseAssumptions(String statements) {
        ANTLRInputStream is = new ANTLRInputStream(statements);
        AssumptionsLexer lexer = new AssumptionsLexer(is);
        TokenStream ts = new CommonTokenStream(lexer);
        AssumptionsParser parser = new AssumptionsParser(ts);
        return parser.parseAssumption().value;
    }
}
