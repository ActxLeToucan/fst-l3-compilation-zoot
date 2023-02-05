package zoot.arbre.expressions;

import zoot.arbre.EntreeVariable;
import zoot.arbre.SymboleVariable;
import zoot.arbre.TableDesSymboles;
import zoot.exceptions.VariableNonDeclareeException;

public class Variable extends Expression {
    String idf;
    SymboleVariable s;
    public Variable(String idf, int n) {
        super(n) ;
        this.idf = idf;
        try {
            this.s = (SymboleVariable) TableDesSymboles.getInstance().identifier(new EntreeVariable(idf));
            setType(s.getType());
        } catch (Exception e) { this.s = null; }
    }

    @Override
    public void verifier() {
        if (s == null) {
            throw new VariableNonDeclareeException("Variable " + idf + " non déclarée");
        }
    }

    @Override
    public String toMIPS() {
        return "\n# On load " + idf + " dans $v0" +
                "\nlw $v0, " + s.getDeplacement() + "($s7)\n";
    }
}
