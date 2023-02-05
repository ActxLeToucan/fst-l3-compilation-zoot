package zoot.arbre.declarations;

import zoot.Type;
import zoot.arbre.ArbreAbstrait;
import zoot.arbre.EntreeVariable;
import zoot.arbre.SymboleVariable;
import zoot.arbre.TableDesSymboles;

public class Declaration extends ArbreAbstrait {
    String idf, type;

    public Declaration(String type, String idf, int n) {
        super(n);
        this.idf = idf;
        this.type = type;

        TableDesSymboles.getInstance().ajouter(
                new EntreeVariable(idf),
                new SymboleVariable(
                        TableDesSymboles.getInstance().getPositionTete(),
                        this.type.equals("entier") ? Type.ENTIER : Type.BOOLEEN
                )
        );
    }

    public void verifier() { // TODO : Verification de la déclaration (nom variable déjà utilisé, etc.)
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    public String toMIPS() {
        // TODO : Générer le code MIPS pour la déclaration
        return "";
    }
}
