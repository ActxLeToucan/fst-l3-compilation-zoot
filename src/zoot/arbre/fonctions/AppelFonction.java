package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.EntreeFonction;
import zoot.arbre.SymboleFonction;
import zoot.arbre.TableDesSymboles;
import zoot.arbre.declarations.BlocDeDeclaration;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.ListeExpressions;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.FonctionNonDeclareeException;

public class AppelFonction extends Expression {
    private String idf;
    private boolean erreur;
    private ListeExpressions parametres;

    public AppelFonction(String idf, ListeExpressions parametres, int n) {
        super(n);
        this.idf = idf;
        this.parametres = parametres;

        this.erreur = false;
        try {
            SymboleFonction sf = (SymboleFonction) TableDesSymboles.getInstance().identifier(
                    new EntreeFonction(idf, parametres.size())
            );
            if (sf == null) {
                this.erreur = true;
            } else {
                this.setType(sf.getType());
            }
        } catch (AnalyseException e) { this.erreur = true; }
    }

    @Override
    public int verifier() {
        if (this.erreur) {
            throw new FonctionNonDeclareeException("Variable " + idf + " non déclarée (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        return "";
    }
}
