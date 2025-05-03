package org.epstesige.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.SousProved;
import java.time.LocalDateTime;
import java.util.List;

@Path("/sous-proveds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SousProvedResource {

    @GET
    public List<SousProved> getAll() {
        return SousProved.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        SousProved sousProved = SousProved.findById(id);
        if (sousProved == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(sousProved).build();
    }

    @POST
    @Transactional
    public Response create(SousProved sousProved) {
        sousProved.createdAt = sousProved.createdAt != null ? sousProved.createdAt : LocalDateTime.now();
        sousProved.persist();
        return Response.status(Response.Status.CREATED).entity(sousProved).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, SousProved data) {
        SousProved entity = SousProved.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.fkTerritoireId = data.fkTerritoireId;
        entity.fkProvedId = data.fkProvedId;
        entity.libelle = data.libelle;
        entity.lieuImplantation = data.lieuImplantation;
        entity.slug = data.slug;
        entity.createdAt = data.createdAt != null ? data.createdAt : entity.createdAt;

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        SousProved sousProved = SousProved.findById(id);
        if (sousProved == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        sousProved.delete();
        return Response.noContent().build();
    }
}
