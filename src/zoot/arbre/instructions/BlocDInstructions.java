package zoot.arbre.instructions;

import zoot.Type;
import zoot.arbre.ArbreAbstrait;
import zoot.arbre.conditions.Condition;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.ReturnException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> instrs;

    public BlocDInstructions(int n) {
        super(n);
        instrs = new ArrayList<>();
    }

    public void ajouter(Instruction i) {
        instrs.add(i);
    }

    /**
     * Vérifie que les instructions 'retourne' sont correctes
     *
     * @param typeAttendu type attendu
     * @return si tout est correct, la première instruction 'retourne' rencontrée interrompant l'exécution
     * @throws ReturnException si le type de retour n'est pas celui attendu
     */
    public ReturnStatement checkReturn(Type typeAttendu) {
        List<ReturnStatement> returnsThatInterruptExecution = new ArrayList<>();

        for (Instruction inst : instrs) {
            if (inst.isReturn()) {
                ReturnStatement returnStatement = (ReturnStatement) inst;
                if (typeAttendu != null && returnStatement.getType() != typeAttendu) {
                    throw new ReturnException("Type de 'retourne' incorrect. Attendu: " + typeAttendu + ", obtenu: " + returnStatement.getType() + " (ligne " + noLigne + ")");
                }
                returnsThatInterruptExecution.add(returnStatement);
            } else if (inst.isCondition()) {
                Condition condition = (Condition) inst;
                ReturnStatement returnStatement = condition.checkReturn(typeAttendu);
                if (typeAttendu != null && returnStatement != null) {
                    if (returnStatement.getType() != typeAttendu) {
                        throw new ReturnException("Type de 'retourne' incorrect. Attendu: " + typeAttendu + ", obtenu: " + returnStatement.getType() + " (ligne " + noLigne + ")");
                    }
                    if (condition.isReturnAlwaysInterruptingExecution()) {
                        returnsThatInterruptExecution.add(returnStatement);
                    }
                }
            }
        }

        return returnsThatInterruptExecution.size() > 0
                ? returnsThatInterruptExecution.get(0)
                : null;
    }

    @Override
    public int verifier() {
        int nb_err = 0;
        for (Instruction inst : instrs) {
            try {
                nb_err += inst.verifier();
            } catch (AnalyseException e) {
                System.out.println(e.getMessage());
                nb_err++;
            }
        }

        return nb_err;
    }

    @Override
    public String toMIPS() {
        return instrs.stream().map(Instruction::toMIPS).collect(Collectors.joining(""));
    }

    @Override
    public String toString() {
        return instrs.toString();
    }

}
