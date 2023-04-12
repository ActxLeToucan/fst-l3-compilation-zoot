package zoot.arbre.conditions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.instructions.BlocDInstructions;

public class Si extends Condition {
    private final BlocDInstructions siBloc;

    public Si(Expression cond, BlocDInstructions b1, int n) {
        super(n, cond);
        this.siBloc = b1;
    }

    @Override
    public int verifier() {
         int nbErrCond = super.verifier();
         int nbErrBloc = siBloc.verifier();
         return nbErrCond + nbErrBloc;
    }

    @Override
    public String toMIPS() {
        String finSi = genererLabel("finSi");

        return "\n# Condition Si ... alors ..." +
                "\n# On calcul l'expression" +
                condition.toMIPS() +
                "\n# On compare le resultat avec 0" +
                "\nbeq $v0, 0, " + finSi +
                "\n# On execute le bloc d'instructions si 1" +
                siBloc.toMIPS() +
                "\n# Fin de la condition" +
                "\n" + finSi + ":";
    }
}
