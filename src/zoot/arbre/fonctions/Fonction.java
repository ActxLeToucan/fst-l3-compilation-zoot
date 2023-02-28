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
    String type, idf;
    EntreeFonction entree;
    BlocDeDeclaration parametres;
    BlocDInstructions instructions;

    public Fonction(String type, String idf, BlocDeDeclaration parametres, BlocDInstructions instructions, int n) {
        super(n);
        this.idf = idf;
        this.type = type;
        this.parametres = parametres;
        this.instructions = instructions;
        this.entree = new EntreeFonction(this.idf, this.parametres.size());

        boolean erreur = false;
        try {
            TableDesSymboles.getInstance().ajouter(
                    this.entree,
                    new SymboleFonction(
                            TableDesSymboles.getInstance().getPositionTete(),
                            this.type.equals("entier") ? Type.ENTIER : Type.BOOLEEN,
                            this.parametres.size()
                    )
            );
        } catch (DoubleDeclarationException e) {
            erreur = true;
        }
    }

    @Override
    public int verifier() {
        return instructions.verifier();
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
