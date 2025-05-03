package org.epstesige.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Identification;
import org.epstesige.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    @Transactional
    public List<User> listAll() {
        //List<User> listUsers = User.listAll();

        return User.listAll();
    }

    @GET
    @Path("/{id}")
    public User get(@PathParam("id") Long id) {
        return User.findById(id);
    }

    @GET
    @Path("/login")
    public Response login(@QueryParam("mdp") String password, @QueryParam("username") String username) {
        //
        HashMap params = new HashMap();
        //
        params.put("username",username);
        params.put("mdp",password);
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
