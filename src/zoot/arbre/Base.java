package zoot.arbre;

public class Base {
    public static final Base GLOBALE = new Base("s3");
    public static final Base LOCALE = new Base("s7");

    private final String register;
    private Base(String register) {
        this.register = register;
    }

    public String getRegister() {
        return register;
    }
}
