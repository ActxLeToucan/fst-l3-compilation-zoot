package zoot.exceptions;

public class IdentifiantNonDeclareException extends AnalyseException {
    public IdentifiantNonDeclareException(String m) {
        super("ERREUR IDENTIFIANT NON DECLARE :\n\t" + m) ;
    }
}
