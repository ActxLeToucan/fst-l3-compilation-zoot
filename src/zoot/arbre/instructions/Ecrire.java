package zoot.arbre.instructions;

import zoot.Type;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.TypeInvalideException;

public class Ecrire extends Instruction {

    protected Expression exp;

    public Ecrire(Expression e, int n) {
        super(n);
        exp = e;
    }

    @Override
    public int verifier() {
        exp.verifier();

        if (exp.getType() != Type.ENTIER && exp.getType() != Type.BOOLEEN) {
            throw new TypeInvalideException("Le type devrait etre entier ou booleen (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public String toMIPS() {
        String ecrire;
        Type t = exp.getType();

        switch (t) {
            case ENTIER:
                ecrire = "move $a0, $v0\n" +
                        "li $v0, 1\n" +
                        "syscall\n";
                break;
            case BOOLEEN:
                String alors = genererLabel("alors");
                String finSi = genererLabel("finSi");

                // si $v0 == 0 alors on affiche faux sinon on affiche vrai
                ecrire = "beq $v0, 0, " + alors + "\n" +
                        "la $a0, true\n" +
                        "j " + finSi + "\n" +
                        alors + ":\n" +
                        "la $a0, false\n" +
                        finSi + ":\n" +
                        "li $v0, 4\n" +
                        "syscall\n";
                break;
            default:
                throw new TypeInvalideException("Type de l'expression inconnu : " + t);
        }

        return exp.toMIPS() +
                "\n# On ecrit la valeur contenue dans $v0\n" +
                ecrire +
                "# retour chariot\n" +
                "la $a0, newLine\n" +
                "li $v0, 4\n" +
                "syscall\n";
    }

}
