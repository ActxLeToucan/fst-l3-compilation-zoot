package zoot.arbre.fonctions;

import zoot.Type;
import zoot.arbre.ArbreAbstrait;
import zoot.arbre.EntreeFonction;
import zoot.arbre.SymboleFonction;
import zoot.arbre.TableDesSymboles;
import zoot.arbre.declarations.BlocDeDeclaration;
import zoot.arbre.instructions.BlocDInstructions;
import zoot.arbre.instructions.ReturnStatement;
import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.ReturnException;

public class Fonction extends ArbreAbstrait {
    boolean erreur;
    Type type;
    String idf;
    int TDSBlocId;
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

    public int creer() {
        SymboleFonction sf;
        try {
            sf = new SymboleFonction(
                    TableDesSymboles.getInstance().getPositionTete(),
                    this.type,
                    this.parametres.size(),
                    this.parametres.getTypes()
            );
            TableDesSymboles.getInstance().ajouter(this.entree, sf);
        } catch (DoubleDeclarationException e) {
            throw new DoubleDeclarationException("Fonction " + idf + " déjà déclarée (ligne " + noLigne + ")");
        }
        return 0;
    }

    @Override
    public int verifier() {
        TDSBlocId = TableDesSymboles.getInstance().entreeBloc();
        int resParams = parametres.verifier();
        int resVars = variables.verifier();
        int resInsts = instructions.verifier();

        SymboleFonction sf = (SymboleFonction) TableDesSymboles.getInstance().identifier(this.entree);
        ReturnStatement returnStatement = instructions.checkReturn(type);
        String entete = idf + sf;
        if (returnStatement == null) {
            throw new ReturnException(entete + "\n\t -> Fonction sans 'retourne' (ligne " + noLigne + ")");
        }
        return resParams + resVars + resInsts;
    }

    @Override
    public String toMIPS() {
        TableDesSymboles.getInstance().utiliserBloc(TDSBlocId);
        String params = this.parametres.toMIPS();
        String variables = this.variables.toMIPS();
        String instrs = instructions.toMIPS();
        String etiquette = this.entree.getLabel();
        TableDesSymboles.getInstance().sortieBloc();

        return "\n# <=== DEBUT Fonction " + idf + " ===>" +
                "\n" + etiquette + ":\n" +
                "\n# ==> Variables " +
                // les parametres sont intialises avant l'appel de fonction
                "\n# On reserve l'espace pour " + this.variables.size() + " variables\n" +
                "addi $sp, $sp, " + ( - this.variables.size() * TableDesSymboles.ELEMENT_SIZE) + "\n" +
                "\n# ==> Instructions " +
                instrs +
                "\n# <=== FIN Fonction " + idf + " ===>";
    }
}
