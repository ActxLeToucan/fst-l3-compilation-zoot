package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;

import java.util.ArrayList;

public class ListeExpressions extends ArbreAbstrait {
    protected ArrayList<Expression> programme;

    public ListeExpressions(int n) {
        super(n);
        programme = new ArrayList<>();
    }

    public ListeExpressions() {
        super(0);
        programme = new ArrayList<>();
    }

    public void ajouter(Expression i) {
        programme.add(i);
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
