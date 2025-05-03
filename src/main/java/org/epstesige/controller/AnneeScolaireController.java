package org.epstesige.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.AnneeScolaire;

import java.util.List;

@Path("/annees-scolaires")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnneeScolaireController {

    @GET
    public List<AnneeScolaire> getAll() {
        return AnneeScolaire.listAll();
    }

    @GET
    @Path("/actives")
    public List<AnneeScolaire> getActiveYears() {
        return AnneeScolaire.list("active", true);
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        AnneeScolaire annee = AnneeScolaire.findById(id);
        return annee != null
                ? Response.ok(annee).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response create(AnneeScolaire newAnnee) {
        if (newAnnee.libAnneeScolaire == null || newAnnee.libAnneeScolaire.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le libellé est obligatoire").build();
        }

        newAnnee.persist();
        return Response.status(Response.Status.CREATED).entity(newAnnee).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, AnneeScolaire updatedAnnee) {
        AnneeScolaire existing = AnneeScolaire.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Mise à jour des champs modifiables
        existing.libAnneeScolaire = updatedAnnee.libAnneeScolaire;
        existing.active = updatedAnnee.active;
        existing.valid = updatedAnnee.valid;
        existing.generate = updatedAnnee.generate;
        existing.open = updatedAnnee.open;

        return Response.ok(existing).build();
    }

    @PATCH
    @Path("/{id}/activate")
    @Transactional
    public Response activate(@PathParam("id") Long id) {
        AnneeScolaire annee = AnneeScolaire.findById(id);
        if (annee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        annee.active = true;
        return Response.ok(annee).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = AnneeScolaire.deleteById(id);
        return deleted
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/search")
    public Response findByLibelle(@QueryParam("libelle") String libelle) {
        AnneeScolaire annee = AnneeScolaire.find("libAnneeScolaire", libelle).firstResult();
        return annee != null
                ? Response.ok(annee).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
