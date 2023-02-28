package zoot.arbre;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.VariableNonDeclareeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TableDesSymboles {
    private static TableDesSymboles instance = null;
    private final List<Map<Entree, Symbole>> tables;

    private final int ELEMENT_SIZE = 4; // taille d'un element (en octets)

    private TableDesSymboles() {
        tables = new ArrayList<>();
        tables.add(new HashMap<>()); // programme principal
    }

    /**
     * Retourne la table des symboles du bloc courant
     * @return table des symboles du bloc courant
     */
    private Map<Entree, Symbole> getBlocTable() {
        return tables.get(tables.size() - 1);
    }

    /**
     * Retourne la table des symboles du bloc courant + shift
     * @param shift décalage par rapport au bloc courant (-1 => un bloc en dessous)
     * @return table des symboles du bloc courant + shift
     */
    private Map<Entree, Symbole> getBlocTable(int shift) {
        if (shift >= tables.size()) { return null; } // plus de blocs a ce niveau
        return tables.get(tables.size() - 1 + shift);
    }

    public static TableDesSymboles getInstance() {
        if (instance == null) {
            instance = new TableDesSymboles();
        }
        return instance;
    }

    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationException {
        Map<Entree, Symbole> blocTable = getBlocTable();
        if (blocTable.containsKey(e)) {
            throw new DoubleDeclarationException(e.toString());
        }

        // On deplace le symbole a la position du pointeur (qui est la place libre)
        // = Nombre d'elements deja presents * taille d'un element
        s.setDeplacement(blocTable.size() * ELEMENT_SIZE);
        s.setBase(tables.size() > 1 ? Base.LOCALE : Base.GLOBALE);

        blocTable.put(e, s);
    }

    public Symbole identifier(Entree e) throws VariableNonDeclareeException {
        Symbole res = null;

        int level = 0;
        while (res == null) {
            Map<Entree, Symbole> blocTable = getBlocTable(level);
            if (blocTable == null) { break; } // on est sorti du programme principal (pas de blocs en dessous)

            res = blocTable.get(e);
            level--;
        }

        if (res == null) { throw new VariableNonDeclareeException(e.toString()); }
        return res;
    }

    public void entreeBloc() {
        tables.add(new HashMap<>()); // on ajoute un nouveau bloc
    }

    public void sortieBloc() {
        tables.remove(tables.size() - 1); // on supprime le dernier bloc
    }

    /**
     * Retourne la position de la tete de pile (premiere place libre)
     * @return position (décalage négatif) de la tete de pile par rapport au $sp
     */
    public int getPositionTete() {
        return -getNbVariables() * ELEMENT_SIZE;
    }

    public int getNbVariables() {
        int total = 0;
        Map<Entree, Symbole> blocTable = getBlocTable();
        for (Symbole s : blocTable.values()) {
            if (s instanceof SymboleVariable) {
                total++;
            }
        }
        return total;
    }

    public int getNbFonctions() {
        int total = 0;
        Map<Entree, Symbole> blocTable = getBlocTable();
        for (Symbole s : blocTable.values()) {
            if (s instanceof SymboleFonction) {
                total++;
            }
        }
        return total;
    }
}
