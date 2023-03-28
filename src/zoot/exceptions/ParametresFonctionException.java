package zoot.exceptions;

public class ParametresFonctionException extends AnalyseException {
        public ParametresFonctionException(String m) {
            super("ERREUR SEMANTIQUE : PARAMETRES FONCTION INCOMPATIBLES :\n\t" + m);
        }
}
