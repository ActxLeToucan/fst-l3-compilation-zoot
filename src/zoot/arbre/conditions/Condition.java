package zoot.arbre.conditions;

import zoot.Type;
import zoot.arbre.expressions.Expression;
import zoot.arbre.instructions.Instruction;
import zoot.arbre.instructions.ReturnStatement;
import zoot.exceptions.TypeInvalideException;

public abstract class Condition extends Instruction {
    protected final Expression condition;

    public Condition(int n, Expression cond) {
        super(n);
        this.condition = cond;
    }

    @Override
    public int verifier() {
        try {
            this.condition.verifier();
            if (!this.condition.getType().equals(Type.BOOLEEN)) {
                throw new TypeInvalideException("La condition doit être de type booléen (ligne " + this.getNoLigne() + ")");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return 0;
    }

    public abstract ReturnStatement checkReturn(Type typeAttendu);
    public abstract boolean isReturnAlwaysInterruptingExecution();

    @Override
    public boolean isCondition() {
        return true;
    }
}
