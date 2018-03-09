import expression.Expression;
import utils.Axioms;
import utils.ExpressionsParser;
import utils.Proof;
import utils.ProofAnnotator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Task0 {

    /**
     * @param args first arg should contain the input path, second arg should contain the path to output file
     */
    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            throw new IllegalArgumentException("Wrong args: first arg should contain the input path, second arg should contain the path to output file");

        List<Expression> expressions = ExpressionsParser.parse(args[0]);
        try (PrintWriter out = new PrintWriter(new File(args[1]))) {
            expressions.forEach(expression -> out.println(expression.toTree()));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
