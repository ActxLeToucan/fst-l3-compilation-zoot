package zoot.exceptions;

public class FonctionNonDeclareeException extends AnalyseException {

    public FonctionNonDeclareeException(String m) {
        super("ERREUR SEMANTIQUE : FONCTION NON DECLAREE :\n\t" + m);
    }
}
