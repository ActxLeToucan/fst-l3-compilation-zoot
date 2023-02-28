package zoot.arbre.instructions;

import zoot.arbre.EntreeVariable;
import zoot.arbre.SymboleVariable;
import zoot.arbre.TableDesSymboles;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.TypeIncompatibleException;
import zoot.exceptions.VariableNonDeclareeException;

public class Affectation extends Instruction {

    protected String idf;
    protected Expression exp;
    private SymboleVariable s;

    public Affectation(String i, Expression e, int n) {
        super(n);
        idf = i;
        exp = e;
        try {
            this.s = (SymboleVariable) TableDesSymboles.getInstance().identifier(new EntreeVariable(idf));
        } catch (AnalyseException e1) {
            this.s = null;
        }
    }

    @Override
    public int verifier() {
        exp.verifier();

        if (s == null) {
            throw new VariableNonDeclareeException("Variable " + idf + " non déclarée (ligne " + noLigne + ")");
        }

        if (s.getType() != exp.getType()) {
            throw new TypeIncompatibleException("Affectation de type " + exp.getType() + " à une variable de type " + s.getType() + " (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        return exp.toMIPS() +
                "\n# On affecte la valeur contenue dans $v0 à la variable " + idf +
                "\nsw $v0, " + s.getDeplacement() + "($" + s.getBase().getRegister() + ")\n";
    }
}
