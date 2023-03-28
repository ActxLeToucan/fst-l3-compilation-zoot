package zoot.arbre;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.VariableNonDeclareeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TableDesSymboles {
    private static TableDesSymboles instance = null;
    private final Map<Integer, Map<Entree, Symbole>> tables;

    public static final int ELEMENT_SIZE = 4; // taille d'un element (en octets)
    public static final int BASE_TABLE = 0; // base programme principal
    private int currentTable = BASE_TABLE;
    private int TABLE_ID_COUNTER = 1;

    private TableDesSymboles() {
        tables = new HashMap<>();
        tables.put(BASE_TABLE, new HashMap<>()); // programme principal, table 0
    }

    /**
     * Retourne la table des symboles du bloc courant
     * @return table des symboles du bloc courant
     */
    private Map<Entree, Symbole> getBlocTable() {
        return tables.get(currentTable);
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

        // on cherche dans le bloc courant
        Map<Entree, Symbole> blocTable = getBlocTable();
        if (blocTable != null)
            res = blocTable.get(e);

        // si non trouve, on cherche dans le bloc parent
        if (res == null)
            res = tables.get(0).get(e);

        if (res == null) { throw new VariableNonDeclareeException(e.toString()); }
        return res;
    }

    public int entreeBloc() {
        int idTable = TABLE_ID_COUNTER++;
        tables.put(idTable, new HashMap<>()); // on ajoute un nouveau bloc
        currentTable = idTable;
        return idTable;
    }

    public void utiliserBloc(int idTable) {
        currentTable = idTable;
        if (!tables.containsKey(idTable))
            throw new RuntimeException("Bloc " + idTable + " n'existe pas");
    }

    public void sortieBloc() {
        tables.remove(currentTable); // on supprime le bloc courant
        currentTable = BASE_TABLE; // on revient au bloc parent
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
