package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurCibleAliasCaseParam extends InterpreteurCible<GroupCases>{
    /*Classe Interpreteur permettant de modéliser un Interpreteur Cible CaseParam ayant un alias sur sa premiere case*/
    private Interpreteur_Alias_Cible<GroupCases> interpreteurCaseAlias;    //Interpreteur représentant l'alias sur la Case
    private String groupCase;                                              //Chaine de caractère représentant la seconde Case de CaaseParam

    public InterpreteurCibleAliasCaseParam(Interpreteur_Alias_Cible<GroupCases> interpreteurCaseAlias, String groupCase) {
        this.interpreteurCaseAlias = interpreteurCaseAlias;
        this.groupCase = groupCase;
    }

    @Override
    public List<GroupCases> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        InterpreteurCible<GroupCases> interpreteur = new InterpreteurCibleCase(groupCase);
        List<GroupCases> listeCases = interpreteur.recupererTout(ord);

        List<GroupCases> casesARetourner = new ArrayList<>();
        GroupCases group = new GroupCases("GroupeCaseParam",null);
        Plateau p = null;

        //Pour chaque groupe de cases issus de l'alias
        for (GroupCases groupCaseAlias : interpreteurCaseAlias.recupererTout(ord)){
            p = groupCaseAlias.getPlateau();
            //Pour chacune des cases Absolues  des groupes de cases issus de l'alias
            for (Case caseAlias : groupCaseAlias.getCasesAbsolue()){
                //On va parcourir les GroupCases de la seconde Case de CaseParam
                //pour récupérer leur cases relatives en les paramétrant par les cases absolues de l'alias
                for (GroupCases g : listeCases){
                    for (Case c : g.getCasesRelatives(caseAlias.getPosition())){
                        group.addCasesAbsolue(c);
                    }
                }
            }
        }

        group.setPlateau(p);
        casesARetourner.add(group);
        return casesARetourner;
    }
}
