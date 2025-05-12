package org.epstesige.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Etablissement;
import org.epstesige.models.Proved;
import org.epstesige.models.Province;
import org.epstesige.models.Territoire;
import org.apache.commons.text.similarity.LevenshteinDistance;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/regions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegionResource {

    // 1. Liste toutes les provinces avec leurs Proved
    @GET
    @Path("/provinces")
    public Response getAllProvincesWithProved() {
        List<Province> provinces = Province.listAll();
        
        List<Map<String, Object>> result = provinces.stream()
            .map(province -> {
                Map<String, Object> provinceMap = new HashMap<>();
                provinceMap.put("id", province.id);
                provinceMap.put("libelle", province.libelle);
                provinceMap.put("chefLieu", province.chefLieu);
                
                // Récupère les Proved de cette province
                List<Proved> provedList = Proved.list("fkProvinceId", province.id);
                provinceMap.put("proveds", provedList);
                
                return provinceMap;
            })
            .collect(Collectors.toList());
            
        return Response.ok(result).build();
    }

    // 2. Liste tous les Proved avec leurs territoires
    @GET
    @Path("/proveds")
    public Response getAllProvedsWithTerritoires() {
        List<Proved> provedList = Proved.listAll();
        
        List<Map<String, Object>> result = provedList.stream()
            .map(proved -> {
                Map<String, Object> provedMap = new HashMap<>();
                provedMap.put("id", proved.id);
                provedMap.put("libelle", proved.libelle);
                
                // Récupère les territoires de ce Proved
                List<Territoire> territoires = Territoire.list("fkProvedId", proved.id);
                provedMap.put("territoires", territoires);
                
                return provedMap;
            })
            .collect(Collectors.toList());
            
        return Response.ok(result).build();
    }

    // 3. Version complète imbriquée (Provinces → Proved → Territoires)
    @GET
    @Path("/full-hierarchy")
    public Response getFullHierarchy() {
        List<Province> provinces = Province.listAll();
        
        List<Map<String, Object>> result = provinces.stream()
            .map(province -> {
                Map<String, Object> provinceMap = new HashMap<>();
                provinceMap.put("province", province);
                
                // Récupère les Proved de cette province
                List<Proved> provedList = Proved.list("fkProvinceId", province.id);
                
                // Pour chaque Proved, récupère ses territoires
                List<Map<String, Object>> provedWithTerritoires = provedList.stream()
                    .map(proved -> {
                        Map<String, Object> provedMap = new HashMap<>();
                        provedMap.put("proved", proved);
                        
                        List<Territoire> territoires = Territoire.list("fkProvedId", proved.id);
                        provedMap.put("territoires", territoires);
                        
                        return provedMap;
                    })
                    .collect(Collectors.toList());
                
                provinceMap.put("proveds", provedWithTerritoires);
                return provinceMap;
            })
            .collect(Collectors.toList());
            
        return Response.ok(result).build();
    }

    // Optionnel: Endpoint pour récupérer une province spécifique avec sa hiérarchie
    @GET
    @Path("/provinces/{id}")
    public Response getProvinceHierarchy(@PathParam("id") Long id) {
        Province province = Province.findById(id);
        if (province == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("province", province);
        
        List<Proved> provedList = Proved.list("fkProvinceId", id);
        
        List<Map<String, Object>> provedWithTerritoires = provedList.stream()
            .map(proved -> {
                Map<String, Object> provedMap = new HashMap<>();
                provedMap.put("proved", proved);
                
                List<Territoire> territoires = Territoire.list("fkProvedId", proved.id);
                provedMap.put("territoires", territoires);
                
                return provedMap;
            })
            .collect(Collectors.toList());
        
        result.put("proveds", provedWithTerritoires);
        
        return Response.ok(result).build();
    }
}
