package zoot.arbre.expressions;

import zoot.Type;
import zoot.exceptions.AnalyseException;

public class Operation extends Expression {
    private final Expression gauche;
    private final Expression droite;
    private final Operateur op;

    public Operation(Expression d, Operateur o, Expression g, int n) {
        super(n);
        this.gauche = g;
        this.droite = d;
        this.op = o;
        makeType();
    }

    @Override
    public int verifier() {
        int errNb = 0;
        try {
            this.gauche.verifier();
        } catch (AnalyseException e) {
            System.out.println(e.getMessage());
            errNb++;
        }
        try {
            this.droite.verifier();
        } catch (AnalyseException e) {
            System.out.println(e.getMessage());
            errNb++;
        }
        return errNb;
    }

    @Override
    public String toMIPS() {
        return "\n# Operation " + op.toString() +
                "\n# Calcul expression gauche" +
                gauche.toMIPS() +
                "\n# Stockage expression gauche" +
                "\nsw $v0, 0($sp)" +
                "\naddi $sp, $sp, -4" +
                "\n# Calcul expression droite" +
                droite.toMIPS() +
                "\n# Recuperation expression gauche" +
                "\naddi $sp, $sp, 4" +
                "\nlw $v1, 0($sp)" +
                "\n# Calcul final" +
                calculate();
    }

    private void makeType() {
        switch (op) {
            case PLUS:
            case FOIS: setType(Type.ENTIER); break;
            case EGAL:
            case DIFFERENT:
            case INFERIEUR:
            case ET:
            case OU: setType(Type.BOOLEEN); break;
        }
    }

    private String calculate() {
        switch (op) {
            case PLUS: {
                return "\nadd $v0, $v0, $v1";
            }
            case FOIS: {
                return "\nmult $v0, $v1" +
                        "\nmflo $v0";
            }
            case EGAL: {
                String nonEQ = genererLabel("nonEQ");
                String finEQ = genererLabel("finEQ");
                return "\nbne $v1, $v0, " + nonEQ +
                        "\nli $v0, 1" +
                        "\nj " + finEQ +
                        "\n" + nonEQ + ":" +
                        "\nli $v0, 0" +
                        "\n" + finEQ + ":";
            }
            case DIFFERENT: {
                String nonEQ = genererLabel("nonEQ");
                String finEQ = genererLabel("finEQ");
                return "\nbne $v1, $v0, " + nonEQ +
                        "\nli $v0, 0" +
                        "\nj " + finEQ +
                        "\n" + nonEQ + ":" +
                        "\nli $v0, 1" +
                        "\n" + finEQ + ":";
            }
            case INFERIEUR: {
                String estLow = genererLabel("estLow");
                String finLow = genererLabel("finLow");
                return "\nblt $v0, $v1, " + estLow +
                        "\nli $v0, 0" +
                        "\nj " + finLow +
                        "\n" + estLow + ":" +
                        "\nli $v0, 1" +
                        "\n" + finLow + ":";
            }
            case ET: {
                return "\nand $v0, $v1, $v0";
            }
            case OU: {
                return "\nor $v0, $v1, $v0";
            }
            default: return "";
        }
    }
}
