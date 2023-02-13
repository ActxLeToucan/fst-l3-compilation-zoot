package zoot.exceptions;

public class TypeInvalideException extends AnalyseException {
    public TypeInvalideException(String m) {
        super("ERREUR SEMANTIQUE : TYPE INVALIDE :\n\t" + m);
    }
}
