package org.epstesige.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Identification;
import org.epstesige.models.User;

import io.quarkus.panache.common.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    @GET
    public Response getUsers(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size,
            @QueryParam("sort") @DefaultValue("name") String sortField,
            @QueryParam("direction") @DefaultValue("asc") String direction,
            @QueryParam("search") String searchTerm,
            @QueryParam("active") Boolean active,
            @QueryParam("role") String role,
            @QueryParam("provinceId") Long provinceId,
            @QueryParam("provedId") Long provedId) {

        // Validation des paramètres
        if (page < 0) page = DEFAULT_PAGE;
        if (size <= 0 || size > 100) size = DEFAULT_PAGE_SIZE;
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) 
            ? Sort.Direction.Descending 
            : Sort.Direction.Ascending;
        Sort sort = Sort.by(sortField, sortDirection);

        // Construction de la requête avec filtres
        StringBuilder queryBuilder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (searchTerm != null && !searchTerm.isBlank()) {
            queryBuilder.append("(name LIKE :search OR email LIKE :search OR phone LIKE :search)");
            params.put("search", "%" + searchTerm + "%");
        }

        if (active != null) {
            if (!queryBuilder.isEmpty()) queryBuilder.append(" AND ");
            queryBuilder.append("active = :active");
            params.put("active", active);
        }

        if (role != null && !role.isBlank()) {
            if (!queryBuilder.isEmpty()) queryBuilder.append(" AND ");
            queryBuilder.append("roles LIKE :role");
            params.put("role", "%" + role + "%");
        }

        if (provinceId != null) {
            if (!queryBuilder.isEmpty()) queryBuilder.append(" AND ");
            queryBuilder.append("fk_province_id = :provinceId");
            params.put("provinceId", provinceId);
        }

        if (provedId != null) {
            if (!queryBuilder.isEmpty()) queryBuilder.append(" AND ");
            queryBuilder.append("fk_proved_id = :provedId");
            params.put("provedId", provedId);
        }

        // Ajout de la condition isDeleted = false si nécessaire
        if (!queryBuilder.isEmpty()) queryBuilder.append(" AND ");
        queryBuilder.append("is_deleted = false");

        String query = queryBuilder.toString();

        // Exécution de la requête paginée
        var userQuery = params.isEmpty() 
            ? User.findAll(sort) 
            : User.find(query, sort, params);

        var users = userQuery.page(page, size).list();
        long totalCount = userQuery.count();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        // Construction de la réponse
        Map<String, Object> response = new HashMap<>();
        response.put("content", users);
        response.put("page", page);
        response.put("size", size);
        response.put("totalElements", totalCount);
        response.put("totalPages", totalPages);
        response.put("sort", sortField + "," + direction);

        return Response.ok(response).build();
    }

    @GET
    @Path("/by-territoire/{territoireId}")
    public Response getByTerritoire(
            @PathParam("territoireId") Long territoireId,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

        var userQuery = User.find("fk_territoire_id = ?1 AND is_deleted = false", territoireId);
        var users = userQuery.page(page, size).list();
        long totalCount = userQuery.count();

        Map<String, Object> response = new HashMap<>();
        response.put("content", users);
        response.put("page", page);
        response.put("size", size);
        response.put("totalElements", totalCount);
        response.put("totalPages", (int) Math.ceil((double) totalCount / size));

        return Response.ok(response).build();
    }

    // @GET
    // @Transactional
    // public List<User> listAll() {
    //     //List<User> listUsers = User.listAll();

    //     return User.listAll();
    // }

    @GET
    @Path("/{id}")
    public User get(@PathParam("id") Long id) {
        return User.findById(id);
    }

    @POST
    @Path("/login")
    public Response login(HashMap us) {
        //@QueryParam("mdp") String password, @QueryParam("username") String username
        HashMap params = new HashMap();
        //
        params.put("username", us.get("username"));
        params.put("mdp", us.get("password"));
         //
        User user = (User) User.find("username =: username and mdp =: mdp", params).firstResult();
        if(user == null){
            return Response.status(404).build();
        }
        //

        //HashMap reponse = new HashMap();
        //reponse.put("user", user);

        /*
        //user.fkSousProvedId
        //Faire une requete dans la table: Identification
        List<Identification> identifications = Identification.find("sousProvedId", user.sousProvedId).list();
        identifications.forEach((e)->{
            System.out.println("Etablissement: Nom "+e.nomChefEtablissement+" ::");
        });
        */

        //System.out.println("Taille: "+identifications.size());

        //Set ets = identifications
        //        .stream().collect(Collectors.toSet());;
        //
        //reponse.put("")
        //reponse.put("identifications", identifications);
        //
        return Response.ok().entity(user).build();
    }

    @POST
    @Transactional
    public Response create(User user) {
        user.persist();
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, User updatedUser) {
        User user = User.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        user.firstName = updatedUser.firstName;
        user.name = updatedUser.name;
        user.email = updatedUser.email;
        user.phone = updatedUser.phone;
        user.active = updatedUser.active;
        user.about = updatedUser.about;
        user.password = updatedUser.password;
        user.roles = updatedUser.roles;
        user.username = updatedUser.username;
        user.slug = updatedUser.slug;
        user.updatedAt = updatedUser.updatedAt;
        user.isDeleted = updatedUser.isDeleted;
        user.userId = updatedUser.userId;
        user.pictureId = updatedUser.pictureId;

        //user.fkTerritoireId = updatedUser.fkTerritoireId;
        //user.fkProvinceId = updatedUser.fkProvinceId;
        //user.fkProvedId = updatedUser.fkProvedId;
        //user.fkSousProvedId = updatedUser.fkSousProvedId;

        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = User.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
