package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.instructions.Instruction;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<Instruction> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(Instruction i) {
        programme.add(i) ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verifier non définie ") ;
    }
    
    @Override
    public String toMIPS() {
        return programme.stream().map(Instruction::toMIPS).reduce("", String::concat) ;
    }

    @Override
    public String toString() {
        return programme.toString() ;
    }

}
