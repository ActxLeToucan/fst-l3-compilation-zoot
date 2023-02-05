package zoot.arbre.expressions;

import zoot.Type;
import zoot.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {
    private Type type;

    protected Expression(int n) {
        super(n) ;
    }

    protected Expression(int n, Type t) {
        super(n) ;
        this.type = t;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
