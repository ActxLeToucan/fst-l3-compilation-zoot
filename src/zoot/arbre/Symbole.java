package zoot.arbre;

public abstract class Symbole {
    private int deplacement;

    public Symbole(int deplacement) {
        this.deplacement = deplacement;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }
}
