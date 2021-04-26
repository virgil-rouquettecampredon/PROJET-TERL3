import org.example.model.*;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurCibleCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class InterpreteurCaseRelative_Test {

    private InterpreteurCibleCase interpretc;
    private OrdonnanceurDeJeu ord;
    private Plateau p;

    //TODO:tester position négatives

    @BeforeEach
    public final void init_allcasesgroupes(){
        p = new Plateau(6,6);

        //Cases absolues
        ArrayList<Case> lc_abs1 = new ArrayList<>();
        lc_abs1.add(p.getCase(new Position(0,0)));
        lc_abs1.add(p.getCase(new Position(1,0)));
        lc_abs1.add(p.getCase(new Position(2,0)));
        lc_abs1.add(p.getCase(new Position(3,0)));
        lc_abs1.add(p.getCase(new Position(4,0)));
        lc_abs1.add(p.getCase(new Position(5,0)));
        GroupCases g = new GroupCases("Cases du haut", p);
        g.setCasesAbsolue(lc_abs1);

        ArrayList<Case> lc_abs2 = new ArrayList<>();
        lc_abs2.add(p.getCase(new Position(3,3)));
        lc_abs2.add(p.getCase(new Position(4,3)));
        lc_abs2.add(p.getCase(new Position(3,4)));
        lc_abs2.add(p.getCase(new Position(4,4)));
        GroupCases g2 = new GroupCases("Cases centrales", p);
        g2.setCasesAbsolue(lc_abs2);

        //Cases relatives
        ArrayList<Position> lc_rel1 = new ArrayList<>();
        lc_rel1.add(new Position(0, -2));
        GroupCases g3 = new GroupCases("Case -2y", p);
        g3.setPositionsRelatives(lc_rel1);

        ArrayList<Position> lc_rel2 = new ArrayList<>();
        lc_rel2.add(new Position(1, 0));
        lc_rel2.add(new Position(1, 1));
        GroupCases g4 = new GroupCases("Cases 1x et 1xy", p);
        g4.setPositionsRelatives(lc_rel2);

        //Groupe vide
        ArrayList<Position> lc_rel3 = new ArrayList<>();
        GroupCases g5 = new GroupCases("Groupe vide", p);
        g5.setPositionsRelatives(lc_rel3);

    }

    @Test
    public final void test_testMauvais(){

    }

}
