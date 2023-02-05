package zoot.arbre.expressions;

import zoot.exceptions.TypeIncorrectException;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n);
    }

    @Override
    public void verifier() {
        if (!cste.matches("[0-9]+")) {
            throw new TypeIncorrectException("Type " + cste + " incorrect pour une constante entiere");
        }
    }

    @Override
    public String toMIPS() {
        return "\n# entier " + cste + "\n" +
                "li $v0, " + cste + "\n" ;
    }

}
