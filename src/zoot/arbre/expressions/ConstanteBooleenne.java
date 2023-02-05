package zoot.arbre.expressions;

import zoot.exceptions.TypeIncorrectException;

public class ConstanteBooleenne extends Constante {

    public ConstanteBooleenne(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public void verifier() {
        if (!cste.matches("true|false")) {
            throw new TypeIncorrectException("Type " + cste + " incorrect pour une constante booleenne");
        }
    }

    @Override
    public String toMIPS() {
        String value = cste.equals("vrai")? "1": "0";
        return "\n# booleen " + cste + "\n" +
                "li $v0, " + value + "\n" ;
    }

}
