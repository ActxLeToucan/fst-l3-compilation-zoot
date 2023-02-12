package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> programme;

    public BlocDInstructions(int n) {
        super(n);
        programme = new ArrayList<>();
    }

    public void ajouter(Instruction i) {
        programme.add(i);
    }

    @Override
    public void verifier() {
        programme.forEach(Instruction::verifier);
    }

    @Override
    public String toMIPS() {
        return programme.stream().map(Instruction::toMIPS).collect(Collectors.joining(""));
    }

    @Override
    public String toString() {
        return programme.toString();
    }

}
