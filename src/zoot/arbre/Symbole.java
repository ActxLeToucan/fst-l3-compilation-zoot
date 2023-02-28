package zoot.arbre;

public abstract class Symbole {
    private int deplacement;
    private Base base;

    public Symbole(int deplacement) {
        this.deplacement = deplacement;
    }
    public Symbole(int deplacement, Base base) {
        this.deplacement = deplacement;
        this.base = base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public Base getBase() {
        return this.base;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }
}
