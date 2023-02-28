package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.declarations.Declaration;
import zoot.exceptions.AnalyseException;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> instrs;

    public BlocDInstructions(int n) {
        super(n);
        instrs = new ArrayList<>();
    }

    public void ajouter(Instruction i) {
        instrs.add(i);
    }

    @Override
    public int verifier() {
        int nb_err = 0;
        for (Instruction inst : instrs) {
            try {
                inst.verifier();
            } catch (AnalyseException e) {
                System.out.println(e.getMessage());
                nb_err++;
            }
        }
        return nb_err;
    }

    @Override
    public String toMIPS() {
        return instrs.stream().map(Instruction::toMIPS).collect(Collectors.joining(""));
    }

    @Override
    public String toString() {
        return instrs.toString();
    }

}
