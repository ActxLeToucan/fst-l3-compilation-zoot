package zoot.arbre.declarations;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.TableDesSymboles;
import zoot.exceptions.AnalyseException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BlocDeDeclaration extends ArbreAbstrait {
    protected ArrayList<Declaration> programme;

    public BlocDeDeclaration(int n) {
        super(n);
        programme = new ArrayList<>();
    }

    public BlocDeDeclaration() {
        super(0);
        programme = new ArrayList<>();
    }

    public void ajouter(Declaration i) {
        programme.add(i);
    }

    @Override
    public int verifier() {
        int nb_err = 0;
        for (Declaration decl : programme) {
            try {
                decl.verifier();
            } catch (AnalyseException e) {
                System.out.println(e.getMessage());
                nb_err++;
            }
        }
        return nb_err;
    }

    @Override
    public String toMIPS() {
        String declarations = programme.stream().map(Declaration::toMIPS).collect(Collectors.joining(""));

        int pos = TableDesSymboles.getInstance().getPositionTete();
        String suffix = "\n# On reserve l'espace pour " + TableDesSymboles.getInstance().getNbVariables() + " variables\n" +
                "addi $sp, $sp, " + pos + "\n";

        return declarations + suffix;
    }

    public int size() {
        return programme.size();
    }

    @Override
    public String toString() {
        return programme.toString();
    }

}
