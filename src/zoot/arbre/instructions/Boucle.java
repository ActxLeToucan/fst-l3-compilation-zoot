package zoot.arbre.instructions;

import zoot.Type;
import zoot.arbre.conditions.Condition;
import zoot.arbre.expressions.Expression;

public class Boucle extends Condition {
    private final BlocDInstructions insts;

    public Boucle(BlocDInstructions i, Expression cond, int n) {
        super(n, cond);
        this.insts = i;
    }

    @Override
    public int verifier() {
         int nbErrCond = super.verifier();
         int nbErrBloc = insts.verifier();
         return nbErrCond + nbErrBloc;
    }

    @Override
    public ReturnStatement checkReturn(Type typeAttendu) {
        return insts.checkReturn(typeAttendu);
    }

    @Override
    public boolean isReturnAlwaysInterruptingExecution() {
        return true;
    }

    @Override
    public String toMIPS() {
        String boucle = genererLabel("boucle");

        return "\n# Boucle repeter ... jusqua ..." +
                "\n# Debut de la boucle" +
                "\n" + boucle + ":" +
                "\n# On execute le bloc d'instructions" +
                insts.toMIPS() +
                "\n# On calcul l'expression" +
                condition.toMIPS() +
                "\n# On compare le resultat avec 1" +
                "\nbeq $v0, 0, " + boucle +
                "\n# Fin de la boucle";
    }
}
