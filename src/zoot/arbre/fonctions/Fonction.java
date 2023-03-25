package zoot.arbre.fonctions;

import zoot.Type;
import zoot.arbre.ArbreAbstrait;
import zoot.arbre.EntreeFonction;
import zoot.arbre.SymboleFonction;
import zoot.arbre.TableDesSymboles;
import zoot.arbre.declarations.BlocDeDeclaration;
import zoot.arbre.instructions.BlocDInstructions;
import zoot.exceptions.DoubleDeclarationException;

public class Fonction extends ArbreAbstrait {
    Type type;
    String idf;
    boolean erreur;
    EntreeFonction entree;
    BlocDeDeclaration parametres;
    BlocDInstructions instructions;

    public Fonction(Type type, String idf, BlocDeDeclaration parametres, BlocDInstructions instructions, int n) {
        super(n);
        this.idf = idf;
        this.type = type;
        this.parametres = parametres;
        this.instructions = instructions;
        this.entree = new EntreeFonction(this.idf, this.parametres.size());

        this.erreur = false;
        try {
            TableDesSymboles.getInstance().ajouter(
                    this.entree,
                    new SymboleFonction(
                            TableDesSymboles.getInstance().getPositionTete(),
                            this.type,
                            this.parametres.size()
                    )
            );
        } catch (DoubleDeclarationException e) {
            this.erreur = true;
        }
    }

    @Override
    public int verifier() {
        if (this.erreur)
            throw new DoubleDeclarationException("Fonction " + idf + " déjà déclarée (ligne " + noLigne + ")");
        return instructions.verifier() + parametres.verifier();
    }

    @Override
    public String toMIPS() {
        String instrs = instructions.toMIPS();
        String etiquette = this.entree.getLabel();
        return "\n# <=== DEBUT Fonction " + idf + " ===>" +
                "\n" + etiquette + ":\n" +
                "\n# variables (pas encore dans zoot2)" +
                instrs +
                "\n# Retour au programme precedent " +
                "\njr $ra" +
                "\n# <=== FIN Fonction " + idf + " ===>";
    }
}
