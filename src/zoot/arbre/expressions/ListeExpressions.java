package zoot.arbre.expressions;

import zoot.Type;
import zoot.arbre.ArbreAbstrait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Type> getTypes() {
        return params.stream().map(Expression::getType).collect(Collectors.toList());
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
