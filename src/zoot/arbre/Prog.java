package zoot.arbre;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Prog extends ArbreAbstrait {

    ArbreAbstrait blocDeDeclaration;
    ArbreAbstrait blocDInstructions;

    public Prog(int n, ArbreAbstrait bd, ArbreAbstrait bi) {
        super(n);
        blocDeDeclaration = bd;
        blocDInstructions = bi;
    }

    @Override
    public void verifier() {
        blocDeDeclaration.verifier();
        blocDInstructions.verifier();
    }

    @Override
    public String toMIPS() {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("newLine", "\\n");
        dataMap.put("true", "vrai");
        dataMap.put("false", "faux");

        String data = ".data\n" +
                dataMap.entrySet().stream()
                        .map(entry -> entry.getKey() + ": .asciiz \"" + entry.getValue() + "\"\n")
                        .collect(Collectors.joining(""));

        // Header du programme (on a la valeur pour un retour a la ligne + l'etiquette main')
        String header = data +
                ".text\n" +
                "main:\n";
        String footer = "\nli $v0, 10\nsyscall";

        return header + blocDeDeclaration.toMIPS() + blocDInstructions.toMIPS() + footer;
    }
}
