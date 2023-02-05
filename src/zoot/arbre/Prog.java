package zoot.arbre;

public class Prog extends ArbreAbstrait {

    ArbreAbstrait blocDeDeclaration;
    ArbreAbstrait blocDInstructions;

    public Prog(int n, ArbreAbstrait bd, ArbreAbstrait bi) {
        super(n);
        System.out.println("+> Prog()");
        blocDeDeclaration = bd;
        blocDInstructions = bi;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        // Header du programme (on a la valeur pour un retour a la ligne + l'etiquette main')
        String header = ".data\n" +
                "newLine: .asciiz \"\\n\"\n" +
                ".text\n" +
                "main:\n";
        String footer = "\nli $v0, 10\nsyscall";

        return header + blocDeDeclaration.toMIPS() + blocDInstructions.toMIPS() + footer;
    }
}
