package zoot.arbre;

import zoot.Type;

public class SymboleFonction extends Symbole {
    private final Type type;
    private final int nbParams;

    public SymboleFonction(int deplacement, Type t, int nbParametres) {
        super(deplacement);
        this.nbParams = nbParametres;
        this.type = t;
    }

    public int getNbParams() {
        return nbParams;
    }

    public Type getType() {
        return type;
    }
}
