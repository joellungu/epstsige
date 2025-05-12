package org.epstesige.controller;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Etablissement;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import org.apache.commons.text.similarity.LevenshteinDistance;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/etablissements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EtablissementResource {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final double SIMILARITY_THRESHOLD = 0.7;

    @GET
    public Response getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

        if (page < 0) page = DEFAULT_PAGE;
        if (size <= 0 || size > 100) size = DEFAULT_PAGE_SIZE;

        PanacheQuery<Etablissement> query = Etablissement.findAll();
        List<Etablissement> etablissements = query.page(page, size).list();
        long totalCount = query.count();

        Map<String, Object> response = new HashMap<>();
        response.put("content", etablissements);
        response.put("page", page);
        response.put("size", size);
        response.put("totalElements", totalCount);
        response.put("totalPages", (int) Math.ceil((double) totalCount / size));

        return Response.ok(response).build();
    }

    @GET
    @Path("province/{province}")
    public Response getAllEtabsProvince(
            @PathParam("province") String input,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

        if (page < 0) page = DEFAULT_PAGE;
        if (size <= 0 || size > 100) size = DEFAULT_PAGE_SIZE;

        String normalizedInput = TextUtils.normalize(input);
        LevenshteinDistance ld = new LevenshteinDistance();

        // Récupération de tous les éléments (peut être optimisé)
        List<Etablissement> allEtablissements = Etablissement.listAll();
        
        // Filtrage avec similarité
        List<Etablissement> filtered = allEtablissements.stream()
            .filter(e -> {
                String province = e.province == null ? "" : e.province;
                String normalizedProvince = TextUtils.normalize(province);
                int distance = ld.apply(normalizedInput, normalizedProvince);
                double similarity = 1.0 - (double) distance / 
                    Math.max(normalizedInput.length(), normalizedProvince.length());
                return similarity > SIMILARITY_THRESHOLD;
            })
            .collect(Collectors.toList());

        // Pagination manuelle
        int totalCount = filtered.size();
        int totalPages = (int) Math.ceil((double) totalCount / size);
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalCount);
        
        if (fromIndex > totalCount) {
            fromIndex = 0;
            page = 0;
        }

        List<Etablissement> paginatedList = filtered.subList(fromIndex, toIndex);

        Map<String, Object> response = new HashMap<>();
        response.put("content", paginatedList);
        response.put("page", page);
        response.put("size", size);
        response.put("totalElements", totalCount);
        response.put("totalPages", totalPages);

        return Response.ok(response).build();
    }

    @GET
    @Path("sousproved/{sousproved}")
    public Response getAllEtabsSousProved(
            @PathParam("sousproved") String input,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

        if (page < 0) page = DEFAULT_PAGE;
        if (size <= 0 || size > 100) size = DEFAULT_PAGE_SIZE;

        String normalizedInput = TextUtils.normalize(input);
        LevenshteinDistance ld = new LevenshteinDistance();

        List<Etablissement> filtered = Etablissement.listAll();
        filtered.stream()
            .filter(e -> {
                String sousproved = e.sousproved == null ? "" : e.sousproved;
                String normalizedSousproved = TextUtils.normalize(sousproved);
                int distance = ld.apply(normalizedInput, normalizedSousproved);
                double similarity = 1.0 - (double) distance / 
                    Math.max(normalizedInput.length(), normalizedSousproved.length());
                return similarity > SIMILARITY_THRESHOLD;
            })
            .collect(Collectors.toList());

        // Pagination manuelle
        int totalCount = filtered.size();
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalCount);
        
        if (fromIndex > totalCount) {
            fromIndex = 0;
            page = 0;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", filtered.subList(fromIndex, toIndex));
        response.put("page", page);
        response.put("size", size);
        response.put("totalElements", totalCount);
        response.put("totalPages", (int) Math.ceil((double) totalCount / size));

        return Response.ok(response).build();
    }

    @GET
    @Path("proved/{proved}")
    public Response getAllEtabsProved(
            @PathParam("proved") String input,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

        if (page < 0) page = DEFAULT_PAGE;
        if (size <= 0 || size > 100) size = DEFAULT_PAGE_SIZE;

        String normalizedInput = TextUtils.normalize(input);
        LevenshteinDistance ld = new LevenshteinDistance();

        List<Etablissement> filtered =  Etablissement.listAll();

            filtered.stream().filter(e -> {
                String proved = e.proved == null ? "" : e.proved;
                String normalizedProved = TextUtils.normalize(proved);
                int distance = ld.apply(normalizedInput, normalizedProved);
                double similarity = 1.0 - (double) distance / 
                    Math.max(normalizedInput.length(), normalizedProved.length());
                return similarity > SIMILARITY_THRESHOLD;
            })
            .collect(Collectors.toList());

        // Pagination manuelle
        int totalCount = filtered.size();
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalCount);
        
        if (fromIndex > totalCount) {
            fromIndex = 0;
            page = 0;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", filtered.subList(fromIndex, toIndex));
        response.put("page", page);
        response.put("size", size);
        response.put("totalElements", totalCount);
        response.put("totalPages", (int) Math.ceil((double) totalCount / size));

        return Response.ok(response).build();
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
