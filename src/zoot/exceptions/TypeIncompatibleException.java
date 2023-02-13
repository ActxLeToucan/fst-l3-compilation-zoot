package zoot.exceptions;

public class TypeIncompatibleException extends AnalyseException {

    public TypeIncompatibleException(String m) {
        super("ERREUR SEMANTIQUE : TYPE INCOMPATIBLE :\n\t" + m);
    }
}
