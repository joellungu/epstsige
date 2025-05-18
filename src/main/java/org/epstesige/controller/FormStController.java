package org.epstesige.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Page;
import jakarta.persistence.criteria.From;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.epstesige.models.AnnuaireSt;
import org.epstesige.models.Enseignants;
import org.epstesige.models.Etablissement;
import org.epstesige.models.FormSt;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("/form")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormStController {


    @GET
    public List<FormSt> list(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("10") int size,
        @QueryParam("anneeId") Long anneeId
    ) {
        
        return FormSt.find("idannee", anneeId)
            .page(Page.of(page, size))
            .list();
    }

    @GET
    @Path("filtre")
    public Integer filtre(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("10") int size,
        @QueryParam("anneeId") Long anneeId
    ) {
        
        List<FormSt> formSts = new LinkedList<>();
        //
        // Format de la date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        // Date cible
        String targetDate = "2025-05-14";
        LocalDateTime start = LocalDateTime.of(2025, 5, 12, 1, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 18, 22, 0); 

        List<FormSt> ll = FormSt.find("idannee", anneeId)
        .page(Page.of(page, size))
        .list();
        //
        Integer i = 0;
        //
        for(FormSt f : ll){
            //infos_generales_etab
            if(f.formulaire.containsKey("infos_generales_etab")){
                //formSts.add(f);
                System.out.println("La taille: "+i);
                i++;
            }
        }
            //
        return i;
    }

    @GET
    @Path("quantite")
    public Response getQuantite(@QueryParam("anneeId") Long anneeId) {
        List<FormSt> formSts = FormSt.find("idannee", anneeId).list();
        return Response.ok(formSts.size()).build();
    }

    @POST
    @Transactional
    public FormSt createForm(FormSt form) {
        //
        //On persite note Formulaire
        //
        form.persist();
        //Script
        Map<String, Object> formlaire = form.formulaire;
        //

        //Notre table annuaire doit etre soit crée ou mise à jour
        AnnuaireSt annuaireSt = AnnuaireSt.find("sousProvedId", form.sousProvedId).firstResult();
        //
        if (annuaireSt != null){//S'il exite
            //Mise à jour
            //On crée
            if(form.type.toLowerCase().equals("ST1".toLowerCase())){
                //
                FormMajUtils.majST1(form);
                //
            } else if(form.type.toLowerCase().equals("ST2".toLowerCase())) {
                //
                FormMajUtils.majST2(form);
                //
            } else {
                //
                FormMajUtils.majST3(form);
                //
            }
        } else {
            //Sinon
            //On crée
            if(form.type.toLowerCase().equals("ST1".toLowerCase())){
                //
                FormUtils.saveST1(form);
                //
            } else if(form.type.toLowerCase().equals("ST2".toLowerCase())) {
                //
                FormUtils.saveST2(form);
                //
            } else {
                //
                FormUtils.saveST3(form);
                //
            }

        }
        //
        //annuaireSt.provedId = form.provinceId;
        //annuaireSt.typeEnseignement = form.type;
        //annuaireSt.proportionSallecourSecondaire = form.formlaire.get('');

        //
        // Long FormStId = form.id;
        // Enseignants enseignants = new Enseignants();
        // //
        // enseignants.FormStId = FormStId;
        // if(form.formulaire.get("Enseignant") != null) {
        //     enseignants.enseignants = (ArrayList) form.formulaire.get("Enseignant");
        // } else {
        //     enseignants.enseignants = new ArrayList();
        // }
        //
        return form;
    }

    @GET
    @Path("/{id}")
    public FormSt getForm(@PathParam("id") Long id) {
        return FormSt.findById(id);
    }


    @GET
    @Path("all")
    public List<FormSt> getFormAll(
        @QueryParam("provinceId") long provinceId,
        @QueryParam("provedId") long provedId,
        @QueryParam("sousProvedId") long sousProvedId,
        @QueryParam("idannee") long idannee,
        @QueryParam("type") String type,
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("10") int size
        ) {
            
        HashMap params = new HashMap();
        params.put("provinceId", provinceId);
        params.put("provedId", provedId);
        params.put("idannee", idannee);
        params.put("sousProvedId", sousProvedId);
        params.put("type", type);

        List<FormSt> formStList = FormSt.find("provinceId =: provinceId and provedId =: provedId and sousProvedId =: sousProvedId and idannee =: idannee and type =: type",params)
        .page(Page.of(page, size))
            .list();
        return formStList;
    }

    @GET
    @Path("check")
    public Response check(@QueryParam("idetablissement") long idetablissement,
                          @QueryParam("idannee") long idannee,
                          @QueryParam("type") String type) {
        HashMap params = new HashMap();
        params.put("idetablissement", idetablissement);
        params.put("idannee", idannee);
        params.put("type", type);

        FormSt form = (FormSt) FormSt.find("idetablissement =: idetablissement and idannee =: idannee and type =: type",params).firstResult();

        if(form == null) {
            return Response.status(404).build();
        }

        return Response.ok().entity(form).build();
    }

    @GET
    @Path("allproved")
    public List<FormSt> getFormAllProved(@QueryParam("provedId") long provedId,
                                         @QueryParam("idannee") long idannee,
        @QueryParam("sousProvedId") long sousProvedId,
                                         @QueryParam("type") String type,
                                        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("10") int size) {
        HashMap params = new HashMap();
        params.put("provedId", provedId);
        params.put("idannee", idannee);
        params.put("sousProvedId", sousProvedId);
        params.put("type", type);

        List<FormSt> formStList = FormSt.find("provedId =: provedId and sousProvedId =: sousProvedId and idannee =: idannee and type =: type",params)
        .page(Page.of(page, size))
            .list();
        return formStList;
    }

    @GET
    @Path("allprovince")
    public List<FormSt> getFormAllProvince(@QueryParam("provinceId") long provinceId,
                                           @QueryParam("idannee") long idannee,
        @QueryParam("sousProvedId") long sousProvedId,
                                           @QueryParam("type") String type,
                                           
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("10") int size
        ) {
        HashMap params = new HashMap();
        params.put("provinceId", provinceId);
        params.put("idannee", idannee);
        params.put("sousProvedId", sousProvedId);
        params.put("type", type);

        List<FormSt> formStList = FormSt.find("provinceId =: provinceId and sousProvedId =: sousProvedId and idannee =: idannee and type =: type",params)
        .page(Page.of(page, size))
            .list();
        return formStList;
    }

    @GET
    @Path("count")
    public long getCount() {
        HashMap params = new HashMap<>();
        params.put("idannee", 17);
        params.put("provedId", 6);
        params.put("type", "ST2");
        
        return FormSt.count("idannee =: idannee  AND provedId =: provedId AND type =: type",params);
    }

    @Transactional
    @Path("supp")
    public Response deleteInvalidFormsts() {
        HashMap params = new HashMap<>();
        //params.put("ids", null);
        params.put("nomEtab", "CS. LES LEADERS");
        params.put("type", "ST1");
        FormSt.delete("nomEtab =: nomEtab and type =: type", params);
        return Response.ok().entity(null).build();
    }

    @GET
    @Path("vue")
    public Response getInvalidFormsts() {

        HashMap params = new HashMap<>();
        //params.put("ids", null);
        params.put("nomEtab", "CS. LES LEADERS");
        params.put("type", "ST1");
        
        FormSt formts = (FormSt) FormSt.find("nomEtab =: nomEtab and type =: type", params).firstResult();
        if(formts != null){
            System.out.println("Date: "+formts.date);
            return Response.ok().entity(formts).build();
        } else {
            //ids =: ids and 
            System.out.println("Valeur null ");
            return Response.ok().entity(null).build();
        }
        
    }

    //long count = Formst.count("idannee = ?1 AND provinceId = ?2 AND type = ?3", 17, 2, "ST2");



}
