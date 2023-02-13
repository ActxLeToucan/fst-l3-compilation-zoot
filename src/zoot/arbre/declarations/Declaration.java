package zoot.arbre.declarations;

import zoot.Type;
import zoot.arbre.ArbreAbstrait;
import zoot.arbre.EntreeVariable;
import zoot.arbre.SymboleVariable;
import zoot.arbre.TableDesSymboles;
import zoot.exceptions.DoubleDeclarationException;

public class Declaration extends ArbreAbstrait {
    String idf, type;
    boolean erreur;

    public Declaration(String type, String idf, int n) {
        super(n);
        this.idf = idf;
        this.type = type;

        try {
            erreur = false;
            TableDesSymboles.getInstance().ajouter(
                    new EntreeVariable(idf),
                    new SymboleVariable(
                            TableDesSymboles.getInstance().getPositionTete(),
                            this.type.equals("entier") ? Type.ENTIER : Type.BOOLEEN
                    )
            );
        } catch (DoubleDeclarationException e) {
            erreur = true;
        }
    }

    public int verifier() {
        if (erreur) {
            throw new DoubleDeclarationException("Variable " + idf + " déjà déclarée (ligne " + noLigne + ")");
        }
        return 0;
    }

    public String toMIPS() {
        return "\n# Declaration de la variable " + idf + "\n";
    }
}
