import utils.ProofAnnotator;

public class Task1 {

    /**
     * @param args first arg should contain the input path, second arg should contain the path to output file
     */
    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            throw new IllegalArgumentException("Wrong args: first arg should contain the input path, second arg should contain the path to output file");
        ProofAnnotator proofAnnotator = new ProofAnnotator(args[0], args[1]);
        proofAnnotator.annotate();
    }
}
