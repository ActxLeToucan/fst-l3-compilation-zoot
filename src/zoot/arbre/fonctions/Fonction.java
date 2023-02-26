package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.declarations.BlocDeDeclaration;
import zoot.arbre.declarations.Declaration;
import zoot.arbre.instructions.BlocDInstructions;

public class Fonction extends ArbreAbstrait {
    Declaration declaration;
    BlocDeDeclaration parametres;
    ArbreAbstrait instructions;

    public Fonction(Declaration declaration, BlocDeDeclaration parametres, ArbreAbstrait instructions, int n) {
        super(n);
        this.declaration = declaration;
        this.parametres = parametres;
        this.instructions = instructions;
    }

    // TODO: verifier
    @Override
    public int verifier() {
        return 0;
    }

    // TODO: toMIPS
    @Override
    public String toMIPS() {
        return "";
    }
}
