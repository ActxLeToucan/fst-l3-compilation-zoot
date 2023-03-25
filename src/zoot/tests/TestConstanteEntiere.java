package zoot.tests;

import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.ConstanteEntiere;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstanteEntiere {
    @Test
    public void testConstanteValeur() {
        ConstanteEntiere cste = new ConstanteEntiere("42", 1);
        assertEquals("42", cste.toString());
    }
    @Test
    public void testConstanteMips() {
        ConstanteEntiere cste = new ConstanteEntiere("8", 1);
        String mips = cste.toMIPS();
        String[] lines = mips.split("\n");
        boolean valide = false;
        for (String line : lines) {
            if (line.contains("li $v0, 8")) {
                valide = true;
                break;
            }
        }
        assertTrue(valide, "La ligne 'li $v0, 8' n'a pas été trouvée dans le code MIPS généré");
    }
}
