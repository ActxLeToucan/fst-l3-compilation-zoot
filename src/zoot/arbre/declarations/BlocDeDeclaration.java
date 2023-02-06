package zoot.arbre.declarations;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.TableDesSymboles;
import zoot.arbre.declarations.Declaration;
import zoot.arbre.instructions.Instruction;

import java.util.ArrayList;

public class BlocDeDeclaration extends ArbreAbstrait {
    protected ArrayList<Declaration> programme ;

    public BlocDeDeclaration(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(Declaration i) {
        programme.add(i) ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verifier non définie ") ;
    }
    
    @Override
    public String toMIPS() {
        String prefix = "\n# On mets sp dans s7" +
                        "\nmove $s7, $sp\n";

        String declarations = programme.stream().map(Declaration::toMIPS).reduce("", String::concat);

        int pos = TableDesSymboles.getInstance().getPositionTete();
        String suffix = "\n# On reserve l'espace pour " + TableDesSymboles.getInstance().getNbElements() + " variables\n" +
                        "addi $sp, $sp, " + pos + "\n";

        return prefix + declarations + suffix ;
    }

    @Override
    public String toString() {
        return programme.toString() ;
    }

}
