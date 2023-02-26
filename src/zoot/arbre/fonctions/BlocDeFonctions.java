package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;

import java.util.ArrayList;

public class BlocDeFonctions extends ArbreAbstrait {
    protected ArrayList<Fonction> programme;

    public BlocDeFonctions(int n) {
        super(n);
        programme = new ArrayList<>();
    }

    public void ajouter(Fonction i) {
        programme.add(i);
    }

    @Override
    public int verifier() {
        int nb_err = 0;
        for (Fonction fun : programme) {
            try {
                fun.verifier();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                nb_err++;
            }
        }
        return nb_err;
    }

    // TODO: toMIPS
    @Override
    public String toMIPS() {
        return "";
    }
}
