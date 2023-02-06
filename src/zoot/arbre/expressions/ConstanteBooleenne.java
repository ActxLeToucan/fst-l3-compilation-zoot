package zoot.arbre.expressions;

import zoot.exceptions.TypeInvalideException;

public class ConstanteBooleenne extends Constante {

    public ConstanteBooleenne(String texte, int n) {
        super(texte, n);
    }

    @Override
    public void verifier() {
        if (!cste.matches("vrai|faux")) {
            throw new TypeInvalideException("Type " + cste + " incorrect pour une constante booleenne (ligne: " + noLigne + ")");
        }
    }

    @Override
    public String toMIPS() {
        String value = cste.equals("vrai") ? "1" : "0";
        return "\n# booleen " + cste + "\n" +
                "li $v0, " + value + "\n";
    }

}
