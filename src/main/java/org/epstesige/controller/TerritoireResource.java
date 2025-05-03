package org.epstesige.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Territoire;

import java.time.LocalDateTime;
import java.util.List;

@Path("/territoires")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TerritoireResource {

    @GET
    public List<Territoire> getAll() {
        return Territoire.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Territoire territoire = Territoire.findById(id);
        if (territoire == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(territoire).build();
    }

    @POST
    @Transactional
    public Response create(Territoire territoire) {
        territoire.createdAt = territoire.createdAt != null ? territoire.createdAt : LocalDateTime.now();
        territoire.persist();
        return Response.status(Response.Status.CREATED).entity(territoire).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Territoire data) {
        Territoire entity = Territoire.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.fkProvedId = data.fkProvedId;
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
        Territoire territoire = Territoire.findById(id);
        if (territoire == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        territoire.delete();
        return Response.noContent().build();
    }
}
