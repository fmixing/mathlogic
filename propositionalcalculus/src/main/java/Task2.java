import expression.Expression;
import utils.Axioms;
import utils.ExpressionsParser;
import utils.Proof;
import utils.ProofDeductor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Task2 {

    private static final String deductiontheoremproofs = "propositionalcalculus/src/main/java/deductiontheoremproofs";

    /**
     * @param args first arg should contain the input path, second arg should contain the path to output file
     */
    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            throw new IllegalArgumentException("Wrong args: first arg should contain the input path, second arg should contain the path to output file");
        List<Expression> axiomExpressions = ExpressionsParser.parse(Axioms.axiomsPath);

        Axioms axioms = new Axioms(axiomExpressions);

        Proof proof = ExpressionsParser.parseProof(args[0]);

        Map<String, Proof> deductionProofs = new HashMap<>();

        try (Stream<Path> paths = Files.walk(Paths.get(deductiontheoremproofs))) {
            paths.filter(Files::isRegularFile).forEach(path -> {
                Proof p = ExpressionsParser.parseProof(path.toString());

                String fileName = path.getFileName().toString();

                deductionProofs.put(fileName, p);
            });
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProofDeductor proofDeductor = new ProofDeductor(axioms, deductionProofs);

        List<Expression> deduct = proofDeductor.deduct(proof);

        try (PrintWriter out = new PrintWriter(new File(args[1]))) {
            if (proof.getFirstLine() != null) {
                String newHeader = "|-" + (proof.getAlphaStatement() == null
                        ? proof.getBetaStatement()
                        : proof.getAlphaStatement() + "->" + proof.getBetaStatement());
                StringJoiner sj = new StringJoiner(",", "", newHeader);
                proof.getAssumptions().forEach(assumption -> sj.add(assumption.toString()));
                out.println(sj.toString());
            }

            deduct.forEach(out::println);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
