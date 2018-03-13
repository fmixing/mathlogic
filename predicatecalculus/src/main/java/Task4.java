import expression.Expression;
import utils.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Task4 {

    private static final String deductiontheoremproofs = "propositionalcalculus/src/main/java/deductiontheoremproofs";

    private static final String rulesPath = "predicatecalculus/src/main/java/rules";

    private static final String outPath = "predicatecalculus/src/test/java/HW4/annotate.out";

    private static final String baseOutPath = "predicatecalculus/src/test/java/HW4/base_annotate.out";

    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            throw new IllegalArgumentException("Wrong args: first arg should contain the input path, " +
                    "second arg should contain the path to output file");

        Proof proof = PredicateExprParser.parsePredicateProof(args[0]);

        List<Expression> axioms = ExpressionsParser.parse(Axioms.axiomsPath);

        List<Expression> arithmeticAxioms = PredicateExprParser.predicateParse(ArithmeticAxioms.arithmeticAxiomsPath);

        Map<String, Proof> deductionProofs = new HashMap<>();

        try (Stream<Path> paths = Files.walk(Paths.get(deductiontheoremproofs))) {
            paths.filter(Files::isRegularFile).forEach(path -> {
                Proof p = ExpressionsParser.parseProof(path.toString(), false);

                String fileName = path.getFileName().toString();

                deductionProofs.put(fileName, p);
            });
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, List<String>> rulesString = new HashMap<>();

        try (Stream<Path> paths = Files.walk(Paths.get(rulesPath))) {
            paths.filter(Files::isRegularFile).forEach(path -> {
                String fileName = path.getFileName().toString();
                List<String> strings = new ArrayList<>();
                try (Scanner in = new Scanner(new File(path.toString()))) {
                    while (in.hasNext()) {
                        strings.add(in.next());
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                rulesString.put(fileName, strings);
            });
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        PredicateAxioms prAx = new PredicateAxioms();
        ArithmeticAxioms arAx = new ArithmeticAxioms(arithmeticAxioms);
        Axioms ax = new Axioms(axioms);

        Annotator annotator = new Annotator(prAx, arAx, ax);

        annotator.annotate(proof, outPath);

        Deductor deductor = new Deductor(prAx, arAx, ax, deductionProofs, rulesString);

        List<Expression> deduct = deductor.deduct(proof);

        Proof deductionProof = new Proof();
        deductionProof.setAlphaStatement(proof.getAlphaStatement());
        deductionProof.setAssumptions(proof.getAssumptions());
        deductionProof.setProof(deduct);
        deductionProof.setFirstLine(proof.getFirstLine());
        deductionProof.setBetaStatement(proof.getBetaStatement());
        annotator.annotate(deductionProof, args[1]);
    }

}

