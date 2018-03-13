import expression.Expression;
import utils.Axioms;
import utils.ExpressionsParser;
import utils.Proof;
import utils.ProofAnnotator;

import java.util.List;

public class Task1 {

    /**
     * @param args first arg should contain the input path, second arg should contain the path to output file
     */
    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            throw new IllegalArgumentException("Wrong args: first arg should contain the input path, second arg should contain the path to output file");

        List<Expression> axiomExpressions = ExpressionsParser.parse(Axioms.axiomsPath);

        Axioms axioms = new Axioms(axiomExpressions);

        Proof proof = ExpressionsParser.parseProof(args[0], false);

        ProofAnnotator proofAnnotator = new ProofAnnotator(axioms);
        proofAnnotator.annotate(proof, args[1]);
    }
}
