package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class AnnuaireSt extends PanacheEntity {
    public Long provinceId;
    public String province;
    //
    public Long provedId;
    public String proved;
    //
    public Long sousProvedId;
    public String sousProved;
    //
    public Long etablissementId;
    public String etablissement;
    //
    public String regimeGestion;
    public double tauxCouverture;
    //
    public String typeEnseignement;
    //
    public int repartitionEcoleMaternelENC;
    public int repartitionEcoleMaternelECC;
    public int repartitionEcoleMaternelECP;
    public int repartitionEcoleMaternelECK;
    public int repartitionEcoleMaternelECI;
    public int repartitionEcoleMaternelECS;
    public int repartitionEcoleMaternelECF;
    public int repartitionEcoleMaternelAUTRE;
    //
    public int repartitionClasseMaternelENC;
    public int repartitionClasseMaternelECC;
    public int repartitionClasseMaternelECP;
    public int repartitionClasseMaternelECK;
    public int repartitionClasseMaternelECI;
    public int repartitionClasseMaternelECS;
    public int repartitionClasseMaternelECF;
    public int repartitionClasseMaternelAUTRE;
    //
    public int repartitionEnfantMaternelENC;
    public int repartitionEnfantMaternelECC;
    public int repartitionEnfantMaternelECP;
    public int repartitionEnfantMaternelECK;
    public int repartitionEnfantMaternelECI;
    public int repartitionEnfantMaternelECS;
    public int repartitionEnfantMaternelECF;
    public int repartitionEnfantMaternelAUTRE;
    //
    public int repartitionEnfantFilleMaternelENC;
    public int repartitionEnfantFilleMaternelECC;
    public int repartitionEnfantFilleMaternelECP;
    public int repartitionEnfantFilleMaternelECK;
    public int repartitionEnfantFilleMaternelECI;
    public int repartitionEnfantFilleMaternelECS;
    public int repartitionEnfantFilleMaternelECF;
    public int repartitionEnfantFilleMaternelAUTRE;
    //
    public int repartitionEducateurMaternelENC;
    public int repartitionEducateurMaternelECC;
    public int repartitionEducateurMaternelECP;
    public int repartitionEducateurMaternelECK;
    public int repartitionEducateurMaternelECI;
    public int repartitionEducateurMaternelECS;
    public int repartitionEducateurMaternelECF;
    public int repartitionEducateurMaternelAUTRE;
    //
    public int repartitionEducateurFemmeMaternelENC;
    public int repartitionEducateurFemmeMaternelECC;
    public int repartitionEducateurFemmeMaternelECP;
    public int repartitionEducateurFemmeMaternelECK;
    public int repartitionEducateurFemmeMaternelECI;
    public int repartitionEducateurFemmeMaternelECS;
    public int repartitionEducateurFemmeMaternelECF;
    public int repartitionEducateurFemmeMaternelAUTRE;
    //
    public int repartitionEducateurQualificationMoinD4;
    public int repartitionEducateurQualificationEM;
    public int repartitionEducateurQualificationD4;
    public int repartitionEducateurQualificationP6;
    public int repartitionEducateurFemmeQualificationD6;
    public int repartitionEducateurAUTRE;
    public int repartitionEducateurQualificationG3A1;
    //
    public int proportionSaleActivite;
    public int proportionSaleActiviteBonEtat;
    public int proportionSaleActiviteEnDure;
    public int proportionSaleActiviteSemiDure;
    public int proportionSaleActiviteEnTerreBattu;
    public int proportionSaleActiviteEnPailleFeuillage;
    //
    //§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§
    public int repartitionEcolePrimairelENC;
    public int repartitionEcolePrimaireECC;
    public int repartitionEcolePrimaireECP;
    public int repartitionEcolePrimaireECK;
    public int repartitionEcolePrimaireECI;
    public int repartitionEcolePrimaireECS;
    public int repartitionEcolePrimaireECF;
    public int repartitionEcolePrimaireAUTRE;
    //
    public int repartitionClassePrimaireENC;
    public int repartitionClassePrimaireECC;
    public int repartitionClassePrimaireECP;
    public int repartitionClassePrimaireECK;
    public int repartitionClassePrimaireECI;
    public int repartitionClassePrimaireECS;
    public int repartitionClassePrimaireECF;
    public int repartitionClassePrimaireAUTRE;
    //
    public int repartitionEnfantPrimaireENC;
    public int repartitionEnfantPrimaireECC;
    public int repartitionEnfantPrimaireECP;
    public int repartitionEnfantPrimaireECK;
    public int repartitionEnfantPrimaireECI;
    public int repartitionEnfantPrimaireECS;
    public int repartitionEnfantPrimaireECF;
    public int repartitionEnfantPrimaireAUTRE;
    //
    public int nouveauEntrantPremierAgeRevoluMoins6;
    public int nouveauEntrantPremierAgeRevoluMoins7;
    public int nouveauEntrantPremierAgeRevoluMoins8;
    public int nouveauEntrantPremierAgeRevoluMoins9;
    public int nouveauEntrantPremierAgeRevoluPlus9;
    //
    public int nouveauInscritPremierENC;
    public int nouveauInscritPremierECC;
    public int nouveauInscritPremierECP;
    public int nouveauInscritPremierECK;
    public int nouveauInscritPremierECI;
    public int nouveauInscritPremierECS;
    public int nouveauInscritPremierECF;
    public int nouveauInscritPremierAUTRE;
    //
    public int repartitionInscritEleveENC;
    public int repartitionInscritEleveECC;
    public int repartitionInscritEleveECP;
    public int repartitionInscritEleveECK;
    public int repartitionInscritEleveECI;
    public int repartitionInscritEleveECS;
    public int repartitionInscritEleveECF;
    public int repartitionInscritEleveAUTRE;
    //
    public int repartitionFilleinscritENC;
    public int repartitionFilleinscritECC;
    public int repartitionFilleinscritECP;
    public int repartitionFilleinscritECK;
    public int repartitionFilleinscritECI;
    public int repartitionFilleinscritECS;
    public int repartitionFilleinscritECF;
    public int repartitionFilleinscritAUTRE;
    public int repartitionFilleinscritEPR;

    //

    public int repartitionpersonnelEnseignanENC;
    public int repartitionpersonnelEnseignanECC;
    public int repartitionpersonnelEnseignanECP;
    public int repartitionpersonnelEnseignanECK;
    public int repartitionpersonnelEnseignanECI;
    public int repartitionpersonnelEnseignanECS;
    public int repartitionpersonnelEnseignanECF;
    public int repartitionpersonnelEnseignanAUTRE;
    //
    public int participationpersonnelFemmeENC;
    public int participationpersonnelFemmeFemmeECC;
    public int participationpersonnelFemmeFemmeECP;
    public int participationpersonnelFemmeFemmeECK;
    public int participationpersonnelFemmeFemmeECI;
    public int participationpersonnelFemmeFemmeECS;
    public int participationpersonnelFemmeFemmeECF;
    public int participationpersonnelFemmeAUTRE;
    public int participationpersonnelFemmeEPR;
    //
    public int repartitionTitulaireEnseignanMoinD4;
    public int repartitionTitulaireEnseignanD4;
    public int repartitionTitulaireEnseignanD6;
    public int repartitionTitulaireEnseignanP6;
    public int repartitionTitulaireEnseignaAUTRE;
    //
    public int proportioncour;
    public int proportioncourbonetat;
    //
    public int proportioncourendur;
    //
    public int proportioncoursemidur;
    //
    public int proportioncourterrebattue;
    //
    public int proportioncourpaillefeuillage;
     //
    //§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§
    //
     public int repartitionEcoleSecondaireENC;
    public int repartitionEcoleSecondaireECC;
    public int repartitionEcoleSecondaireECP;
    public int repartitionEcoleSecondaireECK;
    public int repartitionEcoleSecondaireECI;
    public int repartitionEcoleSecondaireECS;
    public int repartitionEcoleSecondaireECF;
    public int repartitionEcoleSecondaireAUTRE;
    //
    public int repartitionClasseSecondaireENC;
    public int repartitionClasseSecondaireECC;
    public int repartitionClasseSecondaireECP;
    public int repartitionClasseSecondaireECK;
    public int repartitionClasseSecondaireECI;
    public int repartitionClasseSecondaireECS;
    public int repartitionClasseSecondaireECF;
    public int repartitionClasseSecondaireAUTRE;
    //
    public int repartitionEleveinscritSecondaireENC;
    public int repartitionEleveinscritSecondaireECC;
    public int repartitionEleveinscritSecondaireECP;
    public int repartitionEleveinscritSecondaireECK;
    public int repartitionEleveinscritSecondaireECI;
    public int repartitionEleveinscritSecondaireECS;
    public int repartitionEleveinscritSecondaireECF;
    public int repartitionEleveinscritSecondaireAUTRE;
    //
    public int repartitionEleveinscritSecondaireFilleENC;
    public int repartitionEleveinscritSecondaireFilleECC;
    public int repartitionEleveinscritSecondaireFilleECP;
    public int repartitionEleveinscritSecondaireFilleECK;
    public int repartitionEleveinscritSecondaireFilleECI;
    public int repartitionEleveinscritSecondaireFilleECS;
    public int repartitionEleveinscritSecondaireFilleECF;
    public int repartitionEleveinscritSecondaireFilleAUTRE;
    //
    public int repartitionEleveinscritTypenseignementGeneral;
    public int repartitionEleveinscritTypenseignementNormal;
    public int repartitionEleveinscritTypenseignemenTechnique;
    public int repartitionEleveinscritTypenseignementProfessionel;
    public int repartitionEleveinscritTypenseignementArtMetier;
     //

    //

    public int repartitionpersonnelEnseignanSecondaireENC;
    public int repartitionpersonnelEnseignanSecondaireECC;
    public int repartitionpersonnelEnseignanSecondaireECP;
    public int repartitionpersonnelEnseignanSecondaireECK;
    public int repartitionpersonnelEnseignanSecondaireECI;
    public int repartitionpersonnelEnseignanSecondaireECS;
    public int repartitionpersonnelEnseignanSecondaireECF;
    public int repartitionpersonnelEnseignanSecondaireAUTRE;
    //
    public int participationpersonnelFemmeSecondaireENC;
    public int participationpersonnelFemmeFemmeSecondaireECC;
    public int participationpersonnelFemmeFemmeSecondaireECP;
    public int participationpersonnelFemmeFemmeSecondaireECK;
    public int participationpersonnelFemmeFemmeSecondaireECI;
    public int participationpersonnelFemmeFemmeSecondaireECS;
    public int participationpersonnelFemmeFemmeSecondaireECF;
    public int participationpersonnelFemmeSecondaireAUTRE;

    //
    public int repartitionpersonnelenseignantqualificationMoinD6P6;
    public int repartitionpersonnelenseignantqualificationP6;
    public int repartitionpersonnelenseignantqualificationD6;
    public int repartitionpersonnelenseignantqualificationA1;
    public int repartitionpersonnelenseignantqualificationG3;
    public int repartitionpersonnelenseignantqualificationL2;
    public int repartitionpersonnelenseignantqualificationL2A;
    public int repartitionpersonnelenseignantqualificationLA;
    public int repartitionpersonnelenseignantqualificationIR;
    public int repartitionpersonnelenseignantqualificationDR;
    public int repartitionpersonnelenseignantqualificationAUTRE;
    //
    public int proportionSallecourSecondaire;
    public int proportionSallecourSecondairecourbonetat;
    //
    public int proportionSallecourSecondairecourendur;
    //
    public int proportionSallecourSecondairecoursemidur;
    //
    public int proportionSallecourSecondairecourterrebattue;
    //
    public int proportionSallecourSecondairecourpaillefeuillage;
    //

}
