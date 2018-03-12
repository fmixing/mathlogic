package utils;

import expression.*;

import java.util.*;
import java.util.stream.Collectors;

import static expression.ClassName.*;

public class Prover {

    private Map<String, Integer> namesToIndex = new HashMap<>();

    private Map<String, Proof> lemmas;

    private Map<BitSet, List<Expression>> bitSetToProof = new HashMap<>();

    private Map<Integer, String> indexToName = new HashMap<>();

    private final ProofDeductor proofDeductor;

    public Prover(Map<String, Proof> lemmas, Axioms axioms, Map<String, Proof> deductionProofs) {
        this.lemmas = lemmas;
        proofDeductor = new ProofDeductor(axioms, deductionProofs);
    }

    public List<Expression> prove(Expression expressionToProve) {
        // Получаем из выражения все переменные, в мапе хранится Имя -> "индекс" (то есть переменные упорядочены по порядку
        // их появления в выражении)
        getVariables(namesToIndex, expressionToProve);

        indexToName = namesToIndex.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        // Число разных наборов переменных всего
        if ((1 << namesToIndex.size()) > Integer.MAX_VALUE) {
            throw new IllegalStateException("Too much variables to process: " + namesToIndex.size());
        }

        int size = 1 << namesToIndex.size();

        for (int i = 0; i < size; i++) {
            // Переводит текущий long в битмаску
            BitSet bitSet = BitSet.valueOf(new long[]{i});

            List<Expression> expressions = proveInternal(bitSet, expressionToProve);

            bitSetToProof.put(bitSet, expressions);

            if (expressions.isEmpty()) {
                System.out.println("Высказывание ложно при ");
                for (int j = 0; j < indexToName.size(); j++) {
                    System.out.println(indexToName.get(j) + "=" + (bitSet.get(j) ? "И" : "Л"));
                }
                return Collections.emptyList();
            }
        }

        // Теперь, имея доказательства для всевозможных наборов переменных
        // (которые мы строили из предположений набора переменных для конкретного доказательства),
        // мы хотим получить доказательства без каких-либо предположений в итоге.
        // Для этого воспользуемся леммой об исключении допущения: пусть Г, р |- a, Г, !р |- a => Г |- а

        // 1. По теореме о дедукции Г, а |- b => Г |- a -> b
        // Таким образом Г, р |- a, Г, !р |- a => Г |- а ---> (4) Г |- p -> a, (5) Г |- !р -> a
        // 2. По правилу исключенного третьего forall a . |- a || !a (или Г |- p || !p)
        // 3. 9 аксиома: (A->C)->(B->C)->(A|B->C)
        // Подставим в 9 аксиому (6) Г |- (p -> a) -> (!р -> a) -> ((p || !p) -> a)
        // Теперь MP (4, 6); (5, 6); (2, 6), и получим, что Г |- a,
        // То есть избавились от p из доказательства.

        return generateProof(0, new BitSet(), expressionToProve);
    }

    private List<Expression> generateProof(int h, BitSet bitSet, Expression expressionToProve) {
        String var = indexToName.get(h);

        if (indexToName.size() == h) {
            return bitSetToProof.get(bitSet);
        }

        List<Expression> expressionProof = new ArrayList<>();

        Proof proof = new Proof();
        proof.setProof(generateProof(h + 1, bitSet, expressionToProve));
        proof.setAlphaStatement(new Negation(new Variable(var)));

        List<Expression> assumptions =  getAssumptions(h + 1, bitSet);
        proof.setAssumptions(assumptions);

        expressionProof.addAll(proofDeductor.deduct(proof));

        bitSet.flip(h);

        proof.setProof(generateProof(h + 1, bitSet, expressionToProve));
        proof.setAlphaStatement(new Variable(var));

        assumptions = getAssumptions(h + 1, bitSet);
        proof.setAssumptions(assumptions);

        expressionProof.addAll(proofDeductor.deduct(proof));

        bitSet.flip(h);

        expressionProof.addAll(lemmas.get("|-a|!a").
                getProofWithSubstitution(Collections.singletonMap("a", new Variable(var))));

        Map<String, Expression> substitution = new HashMap<>();
        substitution.put("a", expressionToProve);
        substitution.put("b", new Variable(var));

        expressionProof.addAll(lemmas.get("exclusion").getProofWithSubstitution(substitution));

        return expressionProof;
    }

    private List<Expression> getAssumptions(int h, BitSet bitSet) {
        List<Expression> assumptions = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            if (bitSet.get(i)) {
                assumptions.add(new Variable(indexToName.get(i)));
            }
            else {
                assumptions.add(new Negation(new Variable(indexToName.get(i))));
            }
        }
        return assumptions;
    }

    private List<Expression> proveInternal(BitSet bitSet, Expression expressionToProve) {
        // Проверка высказывания на общезначность
        if (!evaluate(bitSet, expressionToProve)) {
            return Collections.emptyList();
        }

        List<Expression> collect = namesToIndex.entrySet().stream().map(entry ->
                bitSet.get(entry.getValue()) ? new Variable(entry.getKey()) : new Negation(new Variable(entry.getKey())))
                .collect(Collectors.toList());

        return constructProof(collect, bitSet, expressionToProve);
    }

    /**
     * Строим из предположения {@param collect} с использованием таблиц истинности для конкретной связки (например, a&b)
     * доказательство
     */
    private List<Expression> constructProof(List<Expression> collect, BitSet bitSet, Expression expressionToProve) {
        List<Expression> result = new ArrayList<>();

        if (expressionToProve.getClassName() == CONJUNCTION) {
            getConjunctionProof(collect, bitSet, (Conjunction) expressionToProve, result);
        } else if (expressionToProve.getClassName() == DISJUNCTION) {
            getDisjunctionProof(collect, bitSet, (Disjunction) expressionToProve, result);
        } else if (expressionToProve.getClassName() == IMPLICATION) {
            getImplicationProof(collect, bitSet, (Implication) expressionToProve, result);
        } else if (expressionToProve.getClassName() == NEGATION) {
            getNegationProof(collect, bitSet, (Negation) expressionToProve, result);
        }

        if (collect.contains(expressionToProve)) {
            result.add(expressionToProve);
        }

        return result;
    }

    private void getNegationProof(List<Expression> collect, BitSet bitSet, Negation expressionToProve, List<Expression> result) {
        Expression expression = expressionToProve.getExpression();

        if (expression.getClassName() == CONJUNCTION) {
            getConjunctionForNegationProof(collect, bitSet, result, (Conjunction) expression);
        } else if (expression.getClassName() == DISJUNCTION) {
            getDisjunctionFornegationProof(collect, bitSet, result, (Disjunction) expression);
        } else if (expression.getClassName() == IMPLICATION) {
            getImplicationForNegationProof(collect, bitSet, result, (Implication) expression);
        } else if (expression.getClassName() == NEGATION) {
            getDoubleNegationProof(collect, bitSet, result, (Negation) expression);
        }
    }

    private void getDoubleNegationProof(List<Expression> collect, BitSet bitSet, List<Expression> result, Negation expression) {
        Map<String, Expression> substitutionMap = new HashMap<>();
        Expression negated = expression.getExpression();

        substitutionMap.put("a", negated);

        result.addAll(constructProof(collect, bitSet, negated));
        result.addAll(lemmas.get("a|-!!a").getProofWithSubstitution(substitutionMap));
    }

    private void getImplicationForNegationProof(List<Expression> collect, BitSet bitSet, List<Expression> result, Implication expression) {
        Map<String, Expression> substitutionMap = new HashMap<>();
        boolean evaluateLeft = evaluate(bitSet, expression.getLeft());
        boolean evaluateRight = evaluate(bitSet, expression.getRight());

        assert evaluateLeft && !evaluateRight;

        substitutionMap.put("a", expression.getLeft());
        substitutionMap.put("b", expression.getRight());

        result.addAll(constructProof(collect, bitSet, expression.getLeft()));
        result.addAll(constructProof(collect, bitSet, new Negation(expression.getRight())));
        result.addAll(lemmas.get("a,!b|-!(a->b)").getProofWithSubstitution(substitutionMap));
    }

    private void getDisjunctionFornegationProof(List<Expression> collect, BitSet bitSet, List<Expression> result, Disjunction expression) {
        Map<String, Expression> substitutionMap = new HashMap<>();
        boolean evaluateLeft = evaluate(bitSet, expression.getLeft());
        boolean evaluateRight = evaluate(bitSet, expression.getRight());

        assert !(evaluateLeft && evaluateRight);

        substitutionMap.put("a", expression.getLeft());
        substitutionMap.put("b", expression.getRight());

        result.addAll(constructProof(collect, bitSet, new Negation(expression.getLeft())));
        result.addAll(constructProof(collect, bitSet, new Negation(expression.getRight())));
        result.addAll(lemmas.get("!a,!b|-!(a|b)").getProofWithSubstitution(substitutionMap));
    }

    private void getConjunctionForNegationProof(List<Expression> collect, BitSet bitSet, List<Expression> result, Conjunction expression) {
        Map<String, Expression> substitutionMap = new HashMap<>();
        boolean evaluateLeft = evaluate(bitSet, expression.getLeft());
        boolean evaluateRight = evaluate(bitSet, expression.getRight());

        assert !(evaluateLeft && evaluateRight);

        substitutionMap.put("a", expression.getLeft());
        substitutionMap.put("b", expression.getRight());

        if (!evaluateLeft && evaluateRight) {
            result.addAll(constructProof(collect, bitSet, new Negation(expression.getLeft())));
            result.addAll(constructProof(collect, bitSet, expression.getRight()));
            result.addAll(lemmas.get("!a,b|-!(a&b)").getProofWithSubstitution(substitutionMap));
        } else //noinspection ConstantConditions
            if (evaluateLeft && !evaluateRight) {
            result.addAll(constructProof(collect, bitSet, expression.getLeft()));
            result.addAll(constructProof(collect, bitSet, new Negation(expression.getRight())));
            result.addAll(lemmas.get("a,!b|-!(a&b)").getProofWithSubstitution(substitutionMap));
        } else //noinspection ConstantConditions
                if (!evaluateLeft && !evaluateRight) {
            result.addAll(constructProof(collect, bitSet, new Negation(expression.getLeft())));
            result.addAll(constructProof(collect, bitSet, new Negation(expression.getRight())));
            result.addAll(lemmas.get("!a,!b|-!(a&b)").getProofWithSubstitution(substitutionMap));
        }
    }

    private void getImplicationProof(List<Expression> collect, BitSet bitSet, Implication expressionToProve, List<Expression> result) {
        Map<String, Expression> substitutionMap = new HashMap<>();
        boolean evaluateLeft = evaluate(bitSet, expressionToProve.getLeft());
        boolean evaluateRight = evaluate(bitSet, expressionToProve.getRight());

        assert !evaluateLeft || evaluateRight;

        substitutionMap.put("a", expressionToProve.getLeft());
        substitutionMap.put("b", expressionToProve.getRight());

        //noinspection ConstantConditions
        if (evaluateLeft && evaluateRight) {
            result.addAll(constructProof(collect, bitSet, expressionToProve.getLeft()));
            result.addAll(constructProof(collect, bitSet, expressionToProve.getRight()));
            result.addAll(lemmas.get("a,b|-(a->b)").getProofWithSubstitution(substitutionMap));
        } else //noinspection ConstantConditions
            if (!evaluateLeft && evaluateRight) {
            result.addAll(constructProof(collect, bitSet, new Negation(expressionToProve.getLeft())));
            result.addAll(constructProof(collect, bitSet, expressionToProve.getRight()));
            result.addAll(lemmas.get("!a,b|-a->b").getProofWithSubstitution(substitutionMap));
        } else {
            result.addAll(constructProof(collect, bitSet, new Negation(expressionToProve.getLeft())));
            result.addAll(constructProof(collect, bitSet, new Negation(expressionToProve.getRight())));
            result.addAll(lemmas.get("!a,!b|-a->b").getProofWithSubstitution(substitutionMap));
        }
    }

    private void getDisjunctionProof(List<Expression> collect, BitSet bitSet, Disjunction expressionToProve, List<Expression> result) {
        Map<String, Expression> substitutionMap = new HashMap<>();
        boolean evaluateLeft = evaluate(bitSet, expressionToProve.getLeft());
        boolean evaluateRight = evaluate(bitSet, expressionToProve.getRight());

        assert evaluateLeft || evaluateRight;

        substitutionMap.put("a", expressionToProve.getLeft());
        substitutionMap.put("b", expressionToProve.getRight());

        if (evaluateLeft && evaluateRight)
        {
            result.addAll(constructProof(collect, bitSet, expressionToProve.getLeft()));
            result.addAll(constructProof(collect, bitSet, expressionToProve.getRight()));
            result.addAll(lemmas.get("a,b|-a|b").getProofWithSubstitution(substitutionMap));
        }
        else if (evaluateLeft) {
            result.addAll(constructProof(collect, bitSet, expressionToProve.getLeft()));
            result.addAll(lemmas.get("a,!b|-a|b").getProofWithSubstitution(substitutionMap));
        } else {
            result.addAll(constructProof(collect, bitSet, expressionToProve.getRight()));
            result.addAll(lemmas.get("!a,b|-a|b").getProofWithSubstitution(substitutionMap));
        }
    }

    private void getConjunctionProof(List<Expression> collect, BitSet bitSet, Conjunction expressionToProve, List<Expression> result) {
        Map<String, Expression> substitutionMap = new HashMap<>();

        boolean evaluateLeft = evaluate(bitSet, expressionToProve.getLeft());
        boolean evaluateRight = evaluate(bitSet, expressionToProve.getRight());

        assert evaluateLeft && evaluateRight;

        substitutionMap.put("a", expressionToProve.getLeft());
        substitutionMap.put("b", expressionToProve.getRight());

        result.addAll(constructProof(collect, bitSet, expressionToProve.getLeft()));
        result.addAll(constructProof(collect, bitSet, expressionToProve.getRight()));
        result.addAll(lemmas.get("a,b|-a&b").getProofWithSubstitution(substitutionMap));
    }


    private boolean evaluate(BitSet bitSet, Expression expression) {
        switch (expression.getClassName()) {
            case VARIABLE:
                return bitSet.get(namesToIndex.get(((Variable) expression).getName()));
            case DISJUNCTION:
                return evaluate(bitSet, ((Disjunction) expression).getLeft())
                        || evaluate(bitSet, ((Disjunction) expression).getRight());
            case CONJUNCTION:
                return evaluate(bitSet, ((Conjunction) expression).getLeft())
                        && evaluate(bitSet, ((Conjunction) expression).getRight());
            case IMPLICATION:
                return !evaluate(bitSet, ((Implication) expression).getLeft())
                        || evaluate(bitSet, ((Implication) expression).getRight());
            case NEGATION:
                return !evaluate(bitSet, ((Negation) expression).getExpression());
        }
        throw new IllegalStateException("Something went wrong: expression class " + expression.getClass() +
                " should be equal to one of Conjunction, Disjunction, Implication, Negation or Variable classes");
    }


    private void getVariables(Map<String, Integer> varNames, Expression expression) {
        switch (expression.getClassName()) {
            case VARIABLE:
                String name = ((Variable) expression).getName();
                if (!varNames.containsKey(name)) {
                    varNames.put(((Variable) expression).getName(), varNames.size());
                }
                break;
            case NEGATION:
                getVariables(varNames, ((Negation) expression).getExpression());
                break;
            case IMPLICATION:
                getVariables(varNames, ((Implication) expression).getLeft());
                getVariables(varNames, ((Implication) expression).getRight());
                break;
            case CONJUNCTION:
                getVariables(varNames, ((Conjunction) expression).getLeft());
                getVariables(varNames, ((Conjunction) expression).getRight());
                break;
            case DISJUNCTION:
                getVariables(varNames, ((Disjunction) expression).getLeft());
                getVariables(varNames, ((Disjunction) expression).getRight());
                break;
        }
    }
}
