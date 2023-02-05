package zoot.arbre;

import zoot.Type;

public class SymboleVariable extends Symbole {
    private Type type;

    public SymboleVariable(int deplacement, Type t) {
        super(deplacement);
        this.type = t;
    }

    public Type getType() {
        return type;
    }
}
