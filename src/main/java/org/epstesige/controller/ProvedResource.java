package org.epstesige.controller;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Proved;

import java.time.LocalDateTime;
import java.util.List;

@Path("/proveds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProvedResource {

    @GET
    public List<Proved> getAll() {
        return Proved.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Proved proved = Proved.findById(id);
        if (proved == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(proved).build();
    }

    @POST
    @Transactional
    public Response create(Proved proved) {
        proved.createdAt = proved.createdAt != null ? proved.createdAt : LocalDateTime.now();
        proved.persist();
        return Response.status(Response.Status.CREATED).entity(proved).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Proved data) {
        Proved entity = Proved.findById(id);
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
        Proved proved = Proved.findById(id);
        if (proved == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        proved.delete();
        return Response.noContent().build();
    }
}
