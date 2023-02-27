package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.declarations.BlocDeDeclaration;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.ListeExpressions;

public class AppelFonction extends Expression {
    private String nom;
    private ListeExpressions parametres;

    public AppelFonction(String nom, ListeExpressions parametres, int n) {
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
