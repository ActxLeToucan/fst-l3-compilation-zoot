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
    public String toMIPS() { // execution expression, copie du resultat + decalage de $sp pour le prochain parametre
        String str = "";
        for (Expression param : params) {
            str += "# Gestion du parametre " + param + "\n";
            str += param.toMIPS() + "\n";
            str += "sw $v0, 0($sp)\n";
            str += "addi $sp, $sp, -4\n";
        }
        return str;
    }
}
