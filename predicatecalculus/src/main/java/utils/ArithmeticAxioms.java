package utils;

import expression.Expression;

import java.util.List;

public class ArithmeticAxioms {

    public final static String arithmeticAxiomsPath = "predicatecalculus/src/main/java/arithmeticaxioms";

    private List<Expression> arithmeticAxioms;

    public ArithmeticAxioms(List<Expression> arithmeticAxioms) {
        this.arithmeticAxioms = arithmeticAxioms;
    }

    int isAxiom(Expression expression) {
        for (int i = 0; i < arithmeticAxioms.size(); i++) {
            if (expression.equals(arithmeticAxioms.get(i))) {
                return i + 1;
            }
        }
        return -1;
    }
}
