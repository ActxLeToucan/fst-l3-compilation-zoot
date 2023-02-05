package zoot.arbre;

public class EntreeVariable extends Entree {

    private final String nom;

    public EntreeVariable(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntreeVariable)) return false;

        EntreeVariable that = (EntreeVariable) o;

        return nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}
