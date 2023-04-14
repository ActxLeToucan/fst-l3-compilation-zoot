package zoot.arbre.expressions;

import zoot.exceptions.TypeInvalideException;

public class OperationBinaire extends Expression {
    private final Expression gauche;
    private final Expression droite;
    private final Operateur operateur;

    public OperationBinaire(Expression gauche, Operateur operateur, Expression droite, int n) {
        super(n);
        this.gauche = gauche;
        this.droite = droite;
        this.operateur = operateur;
    }

    @Override
    public int verifier() {
        gauche.verifier();
        droite.verifier();
        if (gauche.getType() == droite.getType()) {
            setType(gauche.getType());
        } else {
            throw new TypeInvalideException("Les deux expressions ne sont pas du même type : " + gauche.getType() + " et " + droite.getType() + " (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        return "\n# Operation " + operateur.toString() + " // TODO";
    }
}
