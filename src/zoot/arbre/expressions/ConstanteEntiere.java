package zoot.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        return "\n# entier " + cste + "\n" +
                "li $v0, " + cste + "\n" ;
    }

}
