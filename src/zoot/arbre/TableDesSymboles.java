package zoot.arbre;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.IdentifiantNonDeclareException;

import java.util.HashMap;
import java.util.Map;

public class TableDesSymboles {
    private static TableDesSymboles instance = null;
    private final Map<Entree, Symbole> table;

    private TableDesSymboles() {
        table = new HashMap<Entree, Symbole>();
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
        table.put(e, s);
    }

    public Symbole identifier(Entree e) throws IdentifiantNonDeclareException {
        if (!table.containsKey(e)) {
            throw new IdentifiantNonDeclareException(e.toString());
        }
        return table.get(e);
    }

    public int getTailleZoneVariables() {
        return table.size();
    }
}
