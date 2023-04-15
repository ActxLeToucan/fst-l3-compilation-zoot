package zoot.arbre.expressions;

import zoot.Type;
import zoot.exceptions.TypeInvalideException;

public class Inversion extends Expression {
    Expression exp;

    public Inversion(Expression e, int n) {
        super(n);
        this.exp = e;
        setType(Type.BOOLEEN);
    }

    @Override
    public int verifier() {
        exp.verifier();
        if (exp.getType() != Type.BOOLEEN) {
            throw new TypeInvalideException("Type invalide pour l'opérateur non : " + exp.getType() + " au lieu de BOOLEEN (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        return exp.toMIPS() +
                "\n# On inverse le booléen" +
                "\nsub $v0, $zero, $v0" +
                "\naddi $v0, $v0, 1";
    }
}
