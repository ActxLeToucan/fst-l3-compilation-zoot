package zoot.exceptions;

public class ReturnException extends AnalyseException {

    public ReturnException(String m) {
        super("ERREUR SEMANTIQUE : RETOURNE :\n\t" + m);
    }
}
