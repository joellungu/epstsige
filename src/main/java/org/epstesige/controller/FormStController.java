package org.epstesige.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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

}
