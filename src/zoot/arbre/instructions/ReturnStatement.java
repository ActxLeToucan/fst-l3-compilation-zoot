package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.expressions.Expression;

public class ReturnStatement extends Instruction {
    private Expression expression;

    public ReturnStatement(Expression e, int n) {
        super(n);
        this.expression = e;
    }

    public ReturnStatement(int n) {
        super(n);
    }

    @Override
    public int verifier() {
        return 0;
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
