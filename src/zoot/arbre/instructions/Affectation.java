package zoot.arbre.instructions;

import zoot.arbre.EntreeVariable;
import zoot.arbre.SymboleVariable;
import zoot.arbre.TableDesSymboles;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.TypeIncompatibleException;

public class Affectation extends Instruction {

    protected String idf ;
    protected Expression exp ;
    private SymboleVariable s;

    public Affectation (String i, Expression e, int n) {
        super(n) ;
        idf = i ;
        exp = e ;
        s = (SymboleVariable) TableDesSymboles.getInstance().identifier(new EntreeVariable(idf));
    }

    @Override
    public void verifier() {
        exp.verifier();
        if (s.getType() != exp.getType()) {
            throw new TypeIncompatibleException("Affectation de type "+exp.getType()+" à une variable de type "+s.getType());
        }
    }

    @Override
    public String toMIPS() {
        return exp.toMIPS() +
                "\n# On affecte la valeur contenue dans $v0 à la variable " + idf +
                "\nsw $v0, " + s.getDeplacement() + "($s7)\n";
    }
}
