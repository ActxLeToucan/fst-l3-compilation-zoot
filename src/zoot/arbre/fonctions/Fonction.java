package zoot.arbre.fonctions;

import zoot.Type;
import zoot.arbre.ArbreAbstrait;
import zoot.arbre.EntreeFonction;
import zoot.arbre.SymboleFonction;
import zoot.arbre.TableDesSymboles;
import zoot.arbre.declarations.BlocDeDeclaration;
import zoot.arbre.instructions.BlocDInstructions;
import zoot.exceptions.DoubleDeclarationException;

public class Fonction extends ArbreAbstrait {
    boolean erreur;
    Type type;
    String idf;
    BlocDeDeclaration parametres;
    BlocDeDeclaration variables;
    EntreeFonction entree;
    BlocDInstructions instructions;

    public Fonction(Type type, String idf, BlocDeDeclaration parametres, BlocDeDeclaration variables, BlocDInstructions instructions, int n) {
        super(n);
        this.type = type;
        this.idf = idf;
        this.parametres = parametres;
        this.variables = variables;
        this.instructions = instructions;
        this.entree = new EntreeFonction(this.idf, this.parametres.size());
    }

    public int ajouter() {
        try {
            TableDesSymboles.getInstance().ajouter(
                    this.entree,
                    new SymboleFonction(
                            TableDesSymboles.getInstance().getPositionTete(),
                            this.type,
                            this.parametres.size()
                    )
            );
        } catch (DoubleDeclarationException e) {
            throw new DoubleDeclarationException("Fonction " + idf + " déjà déclarée (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public int verifier() {
        TableDesSymboles.getInstance().entreeBloc();
        int resParams = parametres.verifier();
        int resVars = variables.verifier();
        int resInsts = instructions.verifier();
        TableDesSymboles.getInstance().sortieBloc();
        return resParams + resVars + resInsts;
    }

    @Override
    public String toMIPS() {
        String params = this.parametres.toMIPS();
        String variables = this.variables.toMIPS();
        String instrs = instructions.toMIPS();
        String etiquette = this.entree.getLabel();

        return "\n# <=== DEBUT Fonction " + idf + " ===>" +
                "\n" + etiquette + ":\n" +
                params +
                variables +
                instrs +
                "\n# Retour au programme precedent " +
                "\njr $ra" +
                "\n# <=== FIN Fonction " + idf + " ===>";
    }
}
