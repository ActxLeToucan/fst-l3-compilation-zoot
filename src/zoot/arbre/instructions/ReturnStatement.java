package zoot.arbre.instructions;

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
        expression.verifier();
        return 0;
    }

    @Override
    public String toMIPS() {
        return expression.toMIPS() +
               "\n# On retourne la valeur de l'expression\n" +
               "sw $v0, 8($s7)" +
               "\n# Effacement des variables locales " +
               "\nmove $sp, $s7" +
               "\n# Retour au programme precedent " +
               "\njr $ra";
    }
}
