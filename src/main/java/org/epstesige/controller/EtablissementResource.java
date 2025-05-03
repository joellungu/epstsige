package org.epstesige.controller;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Etablissement;

import java.util.List;

@Path("/etablissements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EtablissementResource {

    @GET
    public List<Etablissement> getAll() {
        return Etablissement.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Etablissement school = Etablissement.findById(id);
        if (school == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(school).build();
    }

    @POST
    @Transactional
    public Response create(Etablissement school) {
        school.persist();
        return Response.status(Response.Status.CREATED).entity(school).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Etablissement updated) {
        Etablissement etablissement = Etablissement.findById(id);
        if (etablissement == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        etablissement.nom = updated.nom;
        etablissement.territoireCommuneVille = updated.territoireCommuneVille;
        etablissement.province = updated.province;
        etablissement.proved = updated.province;
        etablissement.sousproved = updated.sousproved;
        etablissement.arreteMinisteriel = updated.arreteMinisteriel;
        etablissement.createdAt = updated.createdAt;
        etablissement.matriculeCecope = updated.matriculeCecope;
        etablissement.citeChefferieVillage = updated.citeChefferieVillage;
        etablissement.territoireCommuneVille = updated.territoireCommuneVille;

        return Response.ok(etablissement).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Etablissement.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
