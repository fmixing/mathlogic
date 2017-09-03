import HW1.FirstTaskLexer;
import HW1.FirstTaskParser;
import expression.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.*;
import java.util.*;

public class Main {

    static String pathname = "src/axioms";

    public static void main(String[] args) {

        try (Scanner in = new Scanner(new File(pathname)); PrintWriter out = new PrintWriter(new File("axioms.out"))) {
            List<Expression> proof = new ArrayList<>();
            ANTLRInputStream is;
            while (in.hasNext()) {
                String statement = in.next();
                is = new ANTLRInputStream(statement);
                FirstTaskLexer lexer = new FirstTaskLexer(is);
                TokenStream ts = new CommonTokenStream(lexer);
                FirstTaskParser parser = new FirstTaskParser(ts);
                proof.add(parser.expression().expr);
            }
            proof.forEach(System.out::println);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
