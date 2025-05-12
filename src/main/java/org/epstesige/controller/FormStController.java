package org.epstesige.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Enseignants;
import org.epstesige.models.FormSt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/form")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormStController {

    @POST
    @Transactional
    public FormSt createForm(FormSt form) {
        form.persist();
        //
        Long FormStId = form.id;
        Enseignants enseignants = new Enseignants();
        //
        enseignants.FormStId = FormStId;
        if(form.formulaire.get("Enseignant") != null) {
            enseignants.enseignants = (ArrayList) form.formulaire.get("Enseignant");
        } else {
            enseignants.enseignants = new ArrayList();
        }
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
    public List<FormSt> getFormAll() {
        return FormSt.listAll();
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
                                         @QueryParam("type") String type) {
        HashMap params = new HashMap();
        params.put("provedId", provedId);
        params.put("idannee", idannee);
        params.put("type", type);

        List<FormSt> formStList = FormSt.find("provedId =: provedId and idannee =: idannee and type =: type",params).list();
        return formStList;
    }

    @GET
    @Path("allprovince")
    public List<FormSt> getFormAllProvince(@QueryParam("provinceId") long provinceId,
                                           @QueryParam("idannee") long idannee,
                                           @QueryParam("type") String type) {
        HashMap params = new HashMap();
        params.put("provinceId", provinceId);
        params.put("idannee", idannee);
        params.put("type", type);

        List<FormSt> formStList = FormSt.find("provinceId =: provinceId and idannee =: idannee and type =: type",params).list();
        return formStList;
    }



}
