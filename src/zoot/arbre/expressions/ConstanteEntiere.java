package zoot.arbre.expressions;

import zoot.exceptions.TypeInvalideException;

public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int n) {
        super(texte, n);
    }

    @Override
    public int verifier() {
        if (!cste.matches("[0-9]+")) {
            throw new TypeInvalideException("Type " + cste + " incorrect pour une constante entiere (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        return "\n# entier " + cste + "\n" +
                "li $v0, " + cste + "\n";
    }

}
