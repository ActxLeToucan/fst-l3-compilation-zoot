package zoot.arbre.expressions;

import zoot.arbre.EntreeVariable;
import zoot.arbre.TableDesSymboles;

public class Variable extends Expression {
    String idf;
    public Variable(String idf, int n) {
        super(n) ;
        this.idf = idf;
    }

    @Override
    public void verifier() {
        // TODO : Vérifier que la variable est déclarée
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toMIPS() {
        // TODO : Générer le code MIPS pour accéder à la variable (grace a la TDS)
        int deplacement = TableDesSymboles.getInstance().identifier(new EntreeVariable(idf)).getDeplacement();
        return "\n# On load " + idf + " dans $v0" +
                "\nlw $v0, " + deplacement + "($s7)\n";
    }
}
