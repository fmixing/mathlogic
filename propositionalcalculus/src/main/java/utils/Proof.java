package utils;

import expression.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Proof {

    private List<Expression> assumptions = new ArrayList<>();

    private List<Expression> proof = new ArrayList<>();

    private Expression alphaStatement;

    private Expression betaStatement;

    private String firstLine;

    public Proof() {}

    public Proof(List<Expression> proof) {
        this.proof = proof;
    }

    List<Expression> getProofWithSubstitution(Map<String, Expression> substitutionMap) {
        return proof.stream().map(expr -> NameChanger.changeNames(expr, substitutionMap))
                .collect(Collectors.toList());
    }

    public List<Expression> getAssumptions() {
        return assumptions;
    }

    List<Expression> getProof() {
        return proof;
    }

    public Expression getAlphaStatement() {
        return alphaStatement;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setAssumptions(List<Expression> assumptions) {
        this.assumptions = assumptions;
    }

    public void setProof(List<Expression> proof) {
        this.proof = proof;
    }

    public void setAlphaStatement(Expression alphaStatement) {
        this.alphaStatement = alphaStatement;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public Expression getBetaStatement() {
        return betaStatement;
    }

    public void setBetaStatement(Expression betaStatement) {
        this.betaStatement = betaStatement;
    }
}
