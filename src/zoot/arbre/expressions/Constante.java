package zoot.arbre.expressions;

import zoot.Type;

public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String texte, int n) {
        super(n);
        cste = texte;
        setType(cste.matches("[0-9]+")? Type.ENTIER : Type.BOOLEEN);
    }

    @Override
    public String toString() {
        return cste ;
    }

}
