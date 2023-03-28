package zoot.arbre.fonctions;

import zoot.Type;
import zoot.arbre.EntreeFonction;
import zoot.arbre.SymboleFonction;
import zoot.arbre.TableDesSymboles;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.ListeExpressions;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.FonctionNonDeclareeException;
import zoot.exceptions.ParametresFonctionException;

import java.util.List;
import java.util.stream.Collectors;

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
        SymboleFonction sf;
        try {
            sf = (SymboleFonction) TableDesSymboles.getInstance().identifier(this.entree);
            this.setType(sf.getType());
        } catch (AnalyseException e) {
            String entete = idf + "(" + parametres.getTypes().stream().map(Type::toString).collect(Collectors.joining(", ")) + ")";
            throw new FonctionNonDeclareeException(entete + " non déclarée (ligne " + noLigne + ")");
        }
        List<Type> typesSymbole = sf.getParametres();
        List<Type> typesAppel = parametres.getTypes();
        String entete = idf + sf;
        if (typesSymbole.size() != typesAppel.size()) {
            throw new ParametresFonctionException(entete + "\n\t -> Nombre de paramètres incorrect (ligne " + noLigne + ")");
        }
        String erreur = "";
        for (int i = 0; i < typesSymbole.size(); i++) {
            if (typesSymbole.get(i) != typesAppel.get(i)) {
                erreur += "\n\t -> Type du paramètre " + (i + 1) + " incorrect. Attendu : "
                        + typesSymbole.get(i) + ", obtenu : " + typesAppel.get(i);
            }
        }
        if (!erreur.isEmpty()) {
            throw new ParametresFonctionException(entete + " (ligne " + noLigne + ")" + erreur);
        }

        return 0;
    }

    @Override
    public String toMIPS() {
        // Ordre des valeurs stockees :
        // - Variable resultat de la fonction
        // - Adresse retour de la fonction
        // === appel de fonction ===
        // - Parametres de la fonction
        // - Variables locales de la fonction
        // === retour de fonction ===

        // avant
        String setup = "\n# Appel de fonction (" + idf + ")" +
                "\n# Resultat de fonction\naddi $sp, $sp, -4" +
                "\n# Adresse de retour\naddi $sp, $sp, -4\nsw $ra, 4($sp)" +
                "\n# On mets s7 au debut des variables locales de la fonction\nmove $s7, $sp" +
                "\n# Parametres de la fonction (" + parametres.size() + ")\n" +
                parametres.toMIPS() +
                "";
        // appel
        String call = "\n# Appel de la fonction " + idf + "\njal " + this.entree.getLabel();
        // apres
        String teardown = "\n# Retour de la fonction " + idf +
                // "\n# On skip les parametres de la fonction\n" +
                "\n# Recuperation de l'adresse de retour\nlw $ra, 4($sp)\naddi $sp, $sp, 4" +
                "\n# Recuperation de la valeur de retour\nlw $v0, 4($sp)\naddi $sp, $sp, 4" +
                "\n# On remets s7 a sa position precedente" +
                "\nmove $s7, $sp # s7 en haut des variables locales de la fonction" +
                "\naddi $s7, $s7, " + (-TableDesSymboles.getInstance().getPositionTete()) + " # s7 en bas des variables locales de la fonction";

        return setup + call + teardown + "\n";
    }
}
