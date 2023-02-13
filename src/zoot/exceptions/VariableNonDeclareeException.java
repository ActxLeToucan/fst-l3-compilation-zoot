package zoot.exceptions;

public class VariableNonDeclareeException extends AnalyseException {

    public VariableNonDeclareeException(String m) {
        super("ERREUR SEMANTIQUE : VARIABLE NON DECLAREE :\n\t" + m);
    }
}
