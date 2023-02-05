package zoot.exceptions;

public class TypeIncompatibleException extends AnalyseException {

    public TypeIncompatibleException(String m) {
        super("ERREUR TYPE INCOMPATIBLE :\n\t" + m) ;
    }
}
