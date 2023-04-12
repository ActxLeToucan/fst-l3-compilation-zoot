package zoot.arbre.expressions;

import zoot.Type;
import zoot.arbre.EntreeVariable;
import zoot.arbre.SymboleVariable;
import zoot.arbre.TableDesSymboles;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.VariableNonDeclareeException;

public class Variable extends Expression {
    String idf;
    SymboleVariable s;

    public Variable(String idf, int n) {
        super(n);
        this.idf = idf;
    }

    @Override
    public int verifier() {
        try {
            this.s = (SymboleVariable) TableDesSymboles.getInstance().identifier(new EntreeVariable(idf));
            setType(s.getType());
        } catch (AnalyseException e) {
            throw new VariableNonDeclareeException("Variable " + idf + " non déclarée (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        return "\n# On load " + idf + " dans $v0" +
                "\nlw $v0, " + s.getDeplacement() + "($" + s.getBase().getRegister() + ")\n";
    }

    @Override
    public String toString() {
        return idf + " : " + s.getType();
    }
}
