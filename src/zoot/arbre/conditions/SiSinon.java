package zoot.arbre.conditions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.instructions.BlocDInstructions;

public class SiSinon extends Condition {
    private final BlocDInstructions siBloc;
    private final BlocDInstructions sinonBloc;

    public SiSinon(Expression cond, BlocDInstructions b1, BlocDInstructions b2, int n) {
        super(n, cond);
        this.siBloc = b1;
        this.sinonBloc = b2;
    }

    @Override
    public int verifier() {
        int nbErrCond = super.verifier();
        int nbErrBloc1 = siBloc.verifier();
        int nbErrBloc2 = sinonBloc.verifier();
        return nbErrCond + nbErrBloc1 + nbErrBloc2;
    }

    @Override
    public String toMIPS() {
        String finSi = genererLabel("finSi");
        String sinon = genererLabel("sinon");

        return "\n# Condition Si ... alors ... sinon ..." +
                "\n# On calcul l'expression" +
                condition.toMIPS() +
                "\n# On compare le resultat avec 0" +
                "\nbeq $v0, 0, " + sinon +
                "\n# On execute le bloc d'instructions si 1" +
                siBloc.toMIPS() +
                "\n# On saute le bloc d'instructions sinon" +
                "\nj " + finSi +
                "\n# Bloc d'instructions sinon" +
                "\n" +sinon + ":" +
                sinonBloc.toMIPS() +
                "\n# Fin de la condition" +
                "\n" + finSi + ":";
    }
}
