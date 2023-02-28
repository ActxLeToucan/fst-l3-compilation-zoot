package zoot.arbre;

import zoot.arbre.declarations.BlocDeDeclaration;
import zoot.arbre.fonctions.BlocDeFonctions;
import zoot.arbre.instructions.BlocDInstructions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Prog extends ArbreAbstrait {

    BlocDeDeclaration blocDeDeclaration;
    BlocDeFonctions blocDeFonction;
    BlocDInstructions blocDInstructions;

    public Prog(int n, BlocDeDeclaration bd, BlocDeFonctions bf, BlocDInstructions bi) {
        super(n);
        blocDeDeclaration = bd;
        blocDeFonction = bf;
        blocDInstructions = bi;
    }

    @Override
    public int verifier() {
        int nb_err_decl = blocDeDeclaration.verifier();
        int nb_err_inst = blocDInstructions.verifier();
        int nb_err_fonc = blocDeFonction.verifier();
        return nb_err_decl + nb_err_fonc + nb_err_inst;
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
        String footer = "\nli $v0, 10\nsyscall\n";

        String prefix = "\n# On mets sp dans s7" +
                "\nmove $s7, $sp\n" +
                "\n# On mets s7 dans s3" +
                "\nmove $s3, $s7\n";

        return header
                + prefix
                + blocDeDeclaration.toMIPS()
                + blocDInstructions.toMIPS()
                + footer
                + blocDeFonction.toMIPS();
    }
}
