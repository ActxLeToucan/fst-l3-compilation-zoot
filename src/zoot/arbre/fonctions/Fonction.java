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
    BlocDeDeclaration parametres;
    BlocDInstructions instructions;

    public Fonction(String type, String idf, BlocDeDeclaration parametres, BlocDInstructions instructions, int n) {
        super(n);
        this.idf = idf;
        this.type = type;
        this.parametres = parametres;
        this.instructions = instructions;

        boolean erreur = false;
        try {
            TableDesSymboles.getInstance().ajouter(
                    new EntreeFonction(this.idf, this.parametres.size()),
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

    // TODO: verifier
    @Override
    public int verifier() {
        return 0;
    }

    // TODO: toMIPS
    @Override
    public String toMIPS() {
        return "";
    }
}
