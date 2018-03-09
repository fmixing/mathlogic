import expression.Expression;
import utils.*;

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
import java.util.stream.Stream;

public class Task3 {

    private final static String lemmaProofs = "propositionalcalculus/src/main/java/task3Proofs";

    private static final String deductiontheoremproofs = "propositionalcalculus/src/main/java/deductiontheoremproofs";

    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            throw new IllegalArgumentException("Wrong args: first arg should contain the input path, " +
                    "second arg should contain the path to output file");

        Map<String, Proof> lemmas = new HashMap<>();

        List<Expression> axiomExpressions = ExpressionsParser.parse(Axioms.axiomsPath);

        Axioms axioms = new Axioms(axiomExpressions);

        try (Stream<Path> paths = Files.walk(Paths.get(lemmaProofs))) {
            paths.filter(Files::isRegularFile).forEach(path -> {
                Proof proof = ExpressionsParser.parseProof(path.toString());

                String fileName = path.getFileName().toString();

                lemmas.put(fileName, proof);
            });
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        List<Expression> parse = ExpressionsParser.parse(args[0]);
        assert parse.size() == 1;

        Prover prover = new Prover(lemmas, axioms, deductionProofs);

        List<Expression> prove = prover.prove(parse.get(0));

        try (PrintWriter out = new PrintWriter(new File(args[1]))) {
            ProofAnnotator proofAnnotator = new ProofAnnotator(axioms);
            Proof proof = new Proof();
            proof.setProof(prove);
            proofAnnotator.annotate(proof, "propositionalcalculus/src/test/java/HW3/annotate.out");

            prove.forEach(out::println);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

