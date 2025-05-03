package org.epstesige.controller;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Province;

import java.time.LocalDateTime;
import java.util.List;

@Path("/provinces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProvinceResource {

    @GET
    public List<Province> getAll() {
        return Province.list("isDeleted", false);
    }

    @GET
    @Path("/by-user/{userId}")
    public List<Province> getByUser(@PathParam("userId") Long userId) {
        return Province.list("userId = ?1 and isDeleted = false", userId);
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Province province = Province.findById(id);
        if (province == null || Boolean.TRUE.equals(province.isDeleted)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(province).build();
    }

    @POST
    @Transactional
    public Response create(Province province) {
        province.createdAt = province.createdAt != null ? province.createdAt : LocalDateTime.now();
        province.persist();
        return Response.status(Response.Status.CREATED).entity(province).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Province data) {
        Province entity = Province.findById(id);
        if (entity == null || Boolean.TRUE.equals(entity.isDeleted)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.userId = data.userId;
        entity.libelle = data.libelle;
        entity.chefLieu = data.chefLieu;
        entity.slug = data.slug;
        entity.createdAt = data.createdAt != null ? data.createdAt : entity.createdAt;
        entity.isDeleted = data.isDeleted;

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Province province = Province.findById(id);
        if (province == null || Boolean.TRUE.equals(province.isDeleted)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        province.isDeleted = true;
        return Response.noContent().build();
    }
}
