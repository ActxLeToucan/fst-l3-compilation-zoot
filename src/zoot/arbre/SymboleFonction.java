package zoot.arbre;

import zoot.Type;

import java.util.List;

public class SymboleFonction extends Symbole {
    private final Type type;
    private final int nbParams;
    private final List<Type> parametres;

    public SymboleFonction(int deplacement, Type t, int nbParametres, List<Type> types) {
        super(deplacement);
        this.nbParams = nbParametres;
        this.type = t;
        this.parametres = types;
    }

    public int getNbParams() {
        return nbParams;
    }

    public Type getType() {
        return type;
    }

    public List<Type> getParametres() {
        return parametres;
    }
}
