import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujetJoueur;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.example.model.Regles.*;
import org.example.model.Joueur;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class InterpreteurPiece_Test {

    private List<Piece> allpieces;
    private InterpreteurSujetJoueur interpretj;
    private OrdonnanceurDeJeu ord;

    @BeforeEach
    public void init_alljoueurs(){

        Jeton.PIECE.setBorne(8);

        allpieces = new ArrayList<>();

        allpieces.add(new Piece("shrek", null, 10, -1, null, null, null));
        allpieces.add(new Piece("shrek", null, 10, -1, null, null, null));
        allpieces.add(new Piece("lesbienne", null, 10, -1, null, null, null));
        allpieces.add(new Piece("lesbienne", null, 10, -1, null, null, null));
        allpieces.add(new Piece("lesbienne", null, 10, -1, null, null, null));
        allpieces.add(new Piece("lesbienne", null, 10, -1, null, null, null));
        allpieces.add(new Piece("chien", null, 10, -1, null, null, null));
        allpieces.add(new Piece("sans", null, 10, -1, null, null, null));

        this.ord = new OrdonnanceurDeJeu(null, allpieces, null);
    }

}
