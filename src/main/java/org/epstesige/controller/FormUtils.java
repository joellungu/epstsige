package org.epstesige.controller;

import java.util.HashMap;
import java.util.List;

import org.epstesige.models.AnnuaireSt;
import org.epstesige.models.Etablissement;
import org.epstesige.models.FormSt;

public class FormUtils {

    public static void saveST1(FormSt formSt) {
        AnnuaireSt annuaireSt = new AnnuaireSt();
        //Cette ligne nous aide à recupèrer un établissement si on en a besoin
        //
        //Etablissement etablissement = Etablissement.findById(formSt.idetablissement);
        //
        annuaireSt.etablissementId = formSt.idetablissement;
        annuaireSt.etablissement = formSt.nomEtab;
        //
        annuaireSt.nouveauEntrantPremierAgeRevoluMoins6 = (int) formSt.formulaire.get("");
        //
        //Enregistrement de l'annuaire
        annuaireSt.persist();
    }
    //
    public static void saveST2(FormSt formSt) {

    }
    //
    public static void saveST3(FormSt formSt) {
        AnnuaireSt annuaireSt = new AnnuaireSt();
        //Cette ligne nous aide à recupèrer un établissement si on en a besoin
        //
        //Etablissement etablissement = Etablissement.findById(formSt.idetablissement);
        //
        annuaireSt.etablissementId = formSt.idetablissement;
        annuaireSt.etablissement = formSt.nomEtab;
        //
        annuaireSt.regimeGestion = (String) formSt.formulaire.get("regime_gestion");
        //Ex:
        List<HashMap<String, Object>> filiereList = (List<HashMap<String, Object>>) formSt.formulaire.get("filiereList");
        //
        annuaireSt.nouveauInscritPremierAUTRE = (int) filiereList.get(0).get("EffectifElevesG7");
        //
        //Enregistrement de l'annuaire
        annuaireSt.persist();
    }
    //
    
}
