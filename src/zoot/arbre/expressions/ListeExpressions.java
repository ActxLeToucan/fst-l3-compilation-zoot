package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;

import java.util.ArrayList;

public class ListeExpressions extends ArbreAbstrait {
    protected ArrayList<Expression> params;

    public ListeExpressions(int n) {
        super(n);
        params = new ArrayList<>();
    }

    public ListeExpressions() {
        super(0);
        params = new ArrayList<>();
    }

    public void ajouter(Expression i) {
        params.add(i);
    }

    public int size() {
        return params.size();
    }

    @Override
    public int verifier() {
        return 0;
    }

    @Override
    public String toMIPS() {
        return "";
    }
}
