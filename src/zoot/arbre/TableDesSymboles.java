package zoot.arbre;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.VariableNonDeclareeException;

import java.util.HashMap;
import java.util.Map;

public class TableDesSymboles {
    private static TableDesSymboles instance = null;
    private final Map<Entree, Symbole> table;

    private int positionTete;

    private TableDesSymboles() {
        table = new HashMap<Entree, Symbole>();
        positionTete = 0;
    }

    public static TableDesSymboles getInstance() {
        if (instance == null) {
            instance = new TableDesSymboles();
        }
        return instance;
    }

    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationException {
        if (table.containsKey(e)) {
            throw new DoubleDeclarationException(e.toString());
        }

        s.setDeplacement(positionTete); // on deplace le symbole a la position du pointeur (qui est la place libre)
        positionTete -= 4; // on decale le pointeur de 4 (taille d'un element)

        table.put(e, s);
    }

    public Symbole identifier(Entree e) throws VariableNonDeclareeException {
        if (!table.containsKey(e)) {
            throw new VariableNonDeclareeException(e.toString());
        }
        return table.get(e);
    }

    /**
     * Retourne la position de la tete de pile (premiere place libre)
     *
     * @return position (décalage négatif) de la tete de pile par rapport au $sp
     */
    public int getPositionTete() {
        return positionTete;
    }

    public int getNbElements() {
        return table.size();
    }
}
