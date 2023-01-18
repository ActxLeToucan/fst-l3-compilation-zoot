package zoot.tests;

import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.ConstanteEntiere;
import zoot.arbre.instructions.Ecrire;

import static org.junit.jupiter.api.Assertions.*;

public class TestEcrire {
    @Test
    public void testEcrire() {
        Ecrire ecrire = new Ecrire(new ConstanteEntiere("1234", 1), 0);
        assertTrue(ecrire.toMIPS().contains("move $a0, $v0\n" +
                "li $v0, 1\n" +
                "syscall"));
    }
}
