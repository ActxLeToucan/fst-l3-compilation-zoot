package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toMIPS() {
        return "\n# On ecrit la valeur contenue dans $v0\n" +
                "move $a0, $v0\n" +
                "li $v0, 1\n" +
                "syscall\n";
    }

}
