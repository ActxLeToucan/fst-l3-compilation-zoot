package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.expressions.Expression;

public class AppelFonction extends Expression {
    private String nom;
    private ArbreAbstrait parametres;

    public AppelFonction(String nom, ArbreAbstrait parametres, int n) {
        super(n);
        this.nom = nom;
        this.parametres = parametres;
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
