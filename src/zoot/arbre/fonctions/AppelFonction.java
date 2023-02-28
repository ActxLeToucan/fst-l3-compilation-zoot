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
    private EntreeFonction entree;
    private boolean erreur;
    private ListeExpressions parametres;

    public AppelFonction(String idf, ListeExpressions parametres, int n) {
        super(n);
        this.idf = idf;
        this.parametres = parametres;
        this.entree = new EntreeFonction(idf, parametres.size());
    }

    @Override
    public int verifier() {
        try {
            SymboleFonction sf = (SymboleFonction) TableDesSymboles.getInstance().identifier(this.entree);
            this.setType(sf.getType());
        } catch (AnalyseException e) {
            throw new FonctionNonDeclareeException("Fonction " + idf + " non déclarée (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        // Ordre des valeurs stockees:
        // - Variable resultat de la fonction
        // - Adresse retour de la fonction
        // - Parametres de la fonction
        // - Variables locales de la fonction

        // avant
        String setup = "\n# Appel de fonction (" + idf + ")" +
                "\n# Resultat de fonction\naddi $sp, $sp, -4" +
                "\n# Adresse de retour\naddi $sp, $sp, -4\nsw $ra, 4($sp)" +
                //"\n# Parametres de la fonction\nmove $s7, $sp\n" + parametres.toMIPS() +
                //"\n# Variables de la fonction\nmove $s7, $sp\n"
                "\n# On mets s7 au debut des variables locales de la fonction\nmove $s7, $sp" +
                "";
        // appel
        String call = "\n# Appel de la fonction " + idf + "\njal " + this.entree.getLabel() ;
        // apres
        String teardown = "\n# Retour de la fonction " + idf +
                // "\n# On skip les parametres de la fonction\n" +
                "\n# Recuperation de l'adresse de retour\nlw $ra, 4($s7)\naddi $sp, $sp, 4" +
                "\n# Recuperation de la valeur de retour\nlw $v0, 8($s7)\naddi $sp, $sp, 4" +
                "\n# On remets s7 a sa position precedente\nmove $s7, $sp";

        return setup + call + teardown + "\n";
    }
}
