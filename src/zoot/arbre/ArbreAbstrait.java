package zoot.arbre;

import java.util.UUID;

public abstract class ArbreAbstrait {

    // numéro de ligne du début de l'instruction
    protected int noLigne;

    protected ArbreAbstrait(int n) {
        noLigne = n;
    }

    public int getNoLigne() {
        return noLigne;
    }

    public abstract int verifier();

    public abstract String toMIPS();

    /**
     * Génère un label unique
     *
     * @param prefix préfixe du label
     * @return label unique
     */
    public String genererLabel(String prefix) {
        return prefix + "_" + UUID.randomUUID().toString().replace("-", "");
    }

}
