package org.epstesige.controller;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Ville;

import java.time.LocalDateTime;
import java.util.List;

@Path("/villes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VilleResource {

    @GET
    public List<Ville> getAll() {
        return Ville.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Ville ville = Ville.findById(id);
        if (ville == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ville).build();
    }

    @POST
    @Transactional
    public Response create(Ville ville) {
        ville.createdAt = ville.createdAt != null ? ville.createdAt : LocalDateTime.now();
        ville.persist();
        return Response.status(Response.Status.CREATED).entity(ville).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Ville data) {
        Ville entity = Ville.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.fkProvinceId = data.fkProvinceId;
        entity.libelle = data.libelle;
        entity.slug = data.slug;
        entity.createdAt = data.createdAt != null ? data.createdAt : entity.createdAt;

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Ville ville = Ville.findById(id);
        if (ville == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ville.delete();
        return Response.noContent().build();
    }
}
