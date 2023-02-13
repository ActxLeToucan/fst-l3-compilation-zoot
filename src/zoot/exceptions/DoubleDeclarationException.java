package zoot.exceptions;

public class DoubleDeclarationException extends AnalyseException {
    public DoubleDeclarationException(String m) {
        super("ERREUR SEMANTIQUE : DOUBLE DECLARATION :\n\t" + m);
    }
}
