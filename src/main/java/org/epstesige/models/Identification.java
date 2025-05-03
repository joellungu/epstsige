package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "identification")
public class Identification extends PanacheEntity {

    @Column(name = "fk_annee_id", nullable = true)
    public Long anneeId;

    @Column(name = "fk_etab_id", nullable = true)
    public Long etablissementId;

    @Column(name = "fk_centre_reg_id", nullable = true)
    public Long centreRegionalId;

    @Column(name = "fk_province_id", nullable = true)
    public Long provinceId;

    @Column(name = "fk_proved_id", nullable = true)
    public Long provedId;

    @Column(name = "fk_territoire_id", nullable = true)
    public Long territoireId;

    @Column(name = "fk_ville_id", nullable = true)
    public Long villeId;

    @Column(name = "sous_proved_id", nullable = true)
    public Long sousProvedId;

    @Column(name = "adresse", nullable = true, length = 191)
    public String adresse;

    @Column(name = "nom_chef_etab", nullable = true, length = 191)
    public String nomChefEtablissement;

    @Column(name = "latitude", nullable = true, precision = 10)
    public BigDecimal latitude;

    @Column(name = "longitude", nullable = true, precision = 10)
    public BigDecimal longitude;

    @Column(name = "milieu", nullable = true, length = 191)
    public String milieu;

    @Column(name = "ref_juridique", nullable = true, length = 191)
    public String referenceJuridique;

    @Column(name = "num_secope", nullable = true, length = 191)
    public String numeroSecope;

    @Column(name = "stat_occup_parcel", nullable = true, length = 191)
    public String statutOccupationParcelle;

    @Column(name = "etab_est", nullable = true, length = 191)
    public String etablissementEst;

    @Column(name = "tel_chef_etab", nullable = true, length = 150)
    public String telephoneChefEtablissement;

    @Column(name = "secteur_enseignement", nullable = true, length = 150)
    public String secteurEnseignement;

    @Column(name = "regime_gestion", nullable = true)
    public String regimeGestion;

    @Column(name = "type_enseignement", nullable = true)
    public String typeEnseignement;

    @Column(name = "download", nullable = true)
    public Boolean download;

    @Column(name = "submit",nullable = true)
    public Boolean submit;

    @Column(name = "finished", nullable = true)
    public Boolean finished;

    @Column(name = "released_at")
    public LocalDateTime releasedAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Column(name = "slug")
    public String slug;

    @Column(name = "center")
    public String center;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "remplissage")
    public Integer remplissage;

    @Column(name = "code_centre_reg")
    public String codeCentreRegional;

    @Column(name = "nom_etab")
    public String nomEtab;

    // Méthodes personnalisées
    public static List<Identification> findByEtablissementId(Long etabId) {
        return list("etablissementId", etabId);
    }

    public static List<Identification> findByAnneeId(Long anneeId) {
        return list("anneeId", anneeId);
    }
}
