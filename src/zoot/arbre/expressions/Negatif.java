package zoot.arbre.expressions;

import zoot.Type;
import zoot.exceptions.TypeInvalideException;

public class Negatif extends Expression {
    Expression exp;

    public Negatif(Expression e, int n) {
        super(n);
        this.exp = e;
        setType(Type.ENTIER);
    }

    @Override
    public int verifier() {
        exp.verifier();
        if (exp.getType() != Type.ENTIER) {
            throw new TypeInvalideException("Type invalide pour l'opérateur - : " + exp.getType() + " au lieu de ENTIER (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        return exp.toMIPS() +
                "\n# On inverse le signe" +
                "\nsub $v0, $zero, $v0\n";
    }
}
