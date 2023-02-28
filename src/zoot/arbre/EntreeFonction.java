package zoot.arbre;

import java.util.Objects;

public class EntreeFonction extends Entree {

    private final String nom;
    private final int nbParametres;

    public EntreeFonction(String nom, int nbParametres) {
        this.nom = nom;
        this.nbParametres = nbParametres;
    }

    public String getNom() {
        return nom;
    }

    public String getLabel() {
        return toString();
    }

    public String toString() {
        return nom + "_" + nbParametres;
    }

    public int getNbParametres() {
        return nbParametres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntreeFonction)) return false;

        EntreeFonction that = (EntreeFonction) o;

        if (nbParametres != that.nbParametres) return false;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + nbParametres;
        return result;
    }
}
