package zoot.exceptions;

public class TypeInvalideException extends AnalyseException {
    public TypeInvalideException(String m) {
        super("ERREUR TYPE INVALIDE :\n\t" + m) ;
    }
}
