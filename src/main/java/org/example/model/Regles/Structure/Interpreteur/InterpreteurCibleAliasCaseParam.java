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
        System.out.println("\033[42m" + "RECUPERER TOUT INTERPRETEUR CIBLE CASE ALIAS + CASE :" + "\033[0m " + groupCase);

        System.out.println("\u001B[31m" + "Récupération entre : " + interpreteurCaseAlias + " et " + groupCase + "\u001B[0m");


        InterpreteurCible<GroupCases> interpreteur = new InterpreteurCibleCase(groupCase);
        List<GroupCases> listeCases = interpreteur.recupererTout(ord);

        System.out.println("\u001B[31m" + "Récupération entre : " + interpreteurCaseAlias.recupererTout(ord) + " et " + listeCases + "\u001B[0m");

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

    @Override
    public String toString(){
        return "[InterpreteurCibleAliasCaseParam: interpret Case:" + this.interpreteurCaseAlias
                + " String GroupCases : " + this.groupCase + "]";
    }
}
