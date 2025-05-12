package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "etablissement")
public class Etablissement extends PanacheEntity {

    @Column(name = "code_etablissement", unique = true, length = 50)
    public String codeEtablissement;

    @Column(name = "is_active")
    public boolean isActive;

    @Column(name = "fk_centre_reg_id")
    public Long centreRegId;

    @Column(name = "fk_ville_id")
    public Long villeId;

    @Column(name = "center", length = 100)
    public String center;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "annee_id")
    public Long anneeId;

    @Column(name = "libelle", length = 191)
    public String libelle;

    @Column(name = "slug", length = 100)
    public String slug;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "is_deleted")
    public boolean isDeleted;

    @Column(name = "code_admin", length = 50)
    public String codeAdmin;

    @Column(name = "prefix", length = 20)
    public String prefix;

    @Column(name = "type_enseignement", length = 50)
    public String typeEnseignement;

    @Column(name = "ref_etab_mongodb", length = 50)
    public String refEtabMongodb;

    @Column(name = "ref_iden_mongodb", length = 50)
    public String refIdenMongodb;

    @Column(name = "arreteMinisteriel", length = 100)
    public String arreteMinisteriel;

    @Column(name = "matriculeCecope", length = 50)
    public String matriculeCecope;

    @Column(name = "nom", length = 191)
    public String nom;

    @Column(name = "proved", length = 100)
    public String proved;

    @Column(name = "province", length = 100)
    public String province;

    @Column(name = "citeChefferieVillage", length = 100)
    public String citeChefferieVillage;

    @Column(name = "sousproved", length = 100)
    public String sousproved;

    @Column(name = "territoireCommuneVille", length = 100)
    public String territoireCommuneVille;

    // Méthodes de repository
    public static Etablissement findByCode(String code) {
        return find("codeEtablissement", code).firstResult();
    }

    public static List<Etablissement> findByVille(Long villeId) {
        return list("villeId", villeId);
    }
}
