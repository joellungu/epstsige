package org.epstesige.controller;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.epstesige.models.Etablissement;
import org.epstesige.models.Identification;

import java.util.HashMap;
import java.util.List;

@Path("/identifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IdentificationResource {

    @Inject
    EntityManager em;

    @GET
    public List<Identification> getAll() {
        return Identification.listAll();
        //Etablissement.listAll()
    }

    @GET
    @Path("updatetruc")
    @Transactional
    public Response updateTruc() {
        updateAddressesInBatches(100);

        return Response.ok().build();
        //Etablissement.listAll()
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Identification identification = Identification.find("etablissementId", id).firstResult();
        System.out.println("Identification: "+identification.nomEtab);
        if (identification == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(identification).build();
    }

    @GET
    @Path("etablissement_annuel")
    public Response getAllByIdAndAnnee(@QueryParam("sousProvedId") Long sous_proved_id,
                                       @QueryParam("anneeId") Long fk_annee_id) {
        //
        HashMap params = new HashMap();
        params.put("sousProvedId", sous_proved_id);
        params.put("anneeId", fk_annee_id);
         //
        List<Identification> identifications = Identification.find("sousProvedId =: sousProvedId and anneeId =: anneeId", params).list();
        if (identifications.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        /*
        identifications.forEach((i)->{
            //
            //
            List<Etablissement> etablissements = Etablissement.listAll();
            //
            etablissements.forEach((e)->{
                if(i.etablissementId.equals(e.id)){
                    //
                    i.nomEtab = e.libelle;
                    //
                    //i.persist();
                }
            });

        });
        */
        return Response.ok(identifications).build();
    }

    @POST
    @Transactional
    public Response create(Identification identification) {
        identification.persist();
        return Response.status(Response.Status.CREATED).entity(identification).build();
    }

    @GET
    @Path("/identifications")
    public Response getAllEtabByYear(@QueryParam("annee") String annee, @QueryParam("fkSousProvedId") Long fkSousProvedId) {
        //
        HashMap params = new HashMap();
        //
        params.put("fkAnneeId", annee);
        params.put("fkSousProvedId", fkSousProvedId);
        //
        List<Identification> identifications = Identification.find("fkAnneeId =: fkAnneeId and fkSousProvedId =: fkSousProvedId", params).list();
        //
        return Response.ok().entity(identifications).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Identification data) {
        Identification entity = Identification.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.anneeId = data.anneeId;
        entity.etablissementId = data.etablissementId;
        entity.centreRegionalId = data.centreRegionalId;
        entity.provinceId = data.provinceId;
        entity.provedId = data.provedId;
        entity.territoireId = data.territoireId;
        entity.villeId = data.villeId;
        entity.sousProvedId = data.sousProvedId;

        entity.adresse = data.adresse;
        entity.nomChefEtablissement = data.nomChefEtablissement;
        entity.latitude = data.latitude;
        entity.longitude = data.longitude;
        entity.milieu = data.milieu;
        entity.referenceJuridique = data.referenceJuridique;
        entity.numeroSecope = data.numeroSecope;
        entity.statutOccupationParcelle = data.statutOccupationParcelle;
        entity.etablissementEst = data.etablissementEst;
        entity.telephoneChefEtablissement = data.telephoneChefEtablissement;
        entity.secteurEnseignement = data.secteurEnseignement;
        entity.regimeGestion = data.regimeGestion;
        entity.typeEnseignement = data.typeEnseignement;

        entity.download = data.download;
        entity.submit = data.submit;
        entity.finished = data.finished;
        //entity.altitude = data.altitude;
        entity.releasedAt = data.releasedAt;
        entity.updatedAt = data.updatedAt;
        entity.slug = data.slug;
        entity.center = data.center;
        entity.userId = data.userId;
        entity.remplissage = data.remplissage;
        entity.codeCentreRegional = data.codeCentreRegional;

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Identification.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    public void updateAddressesInBatches(int batchSize) {
        long total = Identification.count(); // total d'éléments
        for (int i = 0; i < total; i += batchSize) {
            List<Identification> identifications = Identification.findAll().page(i / batchSize, batchSize).list();

            for (Identification identification : identifications) {
                identification.nomEtab = computeNewAddress(identification);
            }

            // force l’écriture en base + libère la mémoire
            em.flush();
            em.clear();
        }
    }

    private String computeNewAddress(Identification identification) {
        // Logique pour construire la nouvelle adresse
        Etablissement etablissement = Etablissement.findById(identification.etablissementId);
        return etablissement.nom;
    }
}
