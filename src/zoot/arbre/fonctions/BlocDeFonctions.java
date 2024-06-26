package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BlocDeFonctions extends ArbreAbstrait {
    protected ArrayList<Fonction> foncts;

    public BlocDeFonctions(int n) {
        super(n);
        foncts = new ArrayList<>();
    }

    public void ajouter(Fonction i) {
        foncts.add(i);
    }

    @Override
    public int verifier() {
        int nb_err = 0;
        for (Fonction fun : foncts) { // creation des fonctions
            try {
                nb_err += fun.creer();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                nb_err++;
            }
        }
        for (Fonction fun : foncts) { // verification des corps des fonctions
            try {
                nb_err += fun.verifier();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                nb_err++;
            }
        }
        return nb_err;
    }

    @Override
    public String toMIPS() {
        return foncts.stream().map(Fonction::toMIPS).collect(Collectors.joining(""));
    }
}
