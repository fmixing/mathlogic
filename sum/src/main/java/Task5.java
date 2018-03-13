import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Task5 {

    private final static String basePath = "sum/src/main/java/proofs/base";

    private final static String outPath = "sum/src/test/java/HW5/test.out";

    private final static String rightInductionPath = "sum/src/main/java/proofs/inductionstep_right";

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            throw new RuntimeException("Not enough arguments: required 2");

        }

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        List<String> rightInduction = new ArrayList<>();

        try (Scanner in = new Scanner(new File(rightInductionPath))) {
            while (in.hasNext()) {
                rightInduction.add(in.next());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String aLit = intToLiteral(a);

        try (PrintWriter out = new PrintWriter(new File(outPath))) {
            try (Scanner in = new Scanner(new File(basePath))) {
                while (in.hasNext()) {
                    String next = in.next();
                    out.println(next.replace("z", aLit));
                }

                for (int i = 0; i < b; i++) {
                    String bLit = intToLiteral(i);
                    String cLit = intToLiteral(i + a);
                    for (String s : rightInduction) {
                        for (int j = 0; j < s.length(); j++) {
                            switch (s.charAt(j)) {
                                case 'd':
                                    out.write(aLit);
                                    break;
                                case 'e':
                                    out.write(bLit);
                                    break;
                                case 'f':
                                    out.write(cLit);
                                    break;
                                default:
                                    out.write(s.charAt(j));
                                    break;
                            }
                        }
                        out.println();
                    }
                }
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String intToLiteral(int i) {
        if (i < 0) {
            throw new IllegalStateException();
        }

        StringBuilder sb = new StringBuilder("0");
        for (int j = 0; j < i; j++)
        {
            sb.append("'");
        }
        return sb.toString();
    }
}