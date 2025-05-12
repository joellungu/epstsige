package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "territoire")
public class Territoire extends PanacheEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // public Long id;

    @Column(name = "fk_proved_id", nullable = false)
    public Long fkProvedId;

    @Column(name = "fk_province_id", nullable = false)
    public Long fkProvinceId;

    @Column(nullable = false)
    public String libelle;

    @Column(nullable = false, unique = true)
    public String slug;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    // Relations ManyToOne optionnelles
    @ManyToOne
    @JoinColumn(name = "fk_proved_id", insertable = false, updatable = false)
    public Proved proved;

    @ManyToOne
    @JoinColumn(name = "fk_province_id", insertable = false, updatable = false)
    public Province province;

    // Constructeurs
    public Territoire() {
    }

    public Territoire(String libelle, String slug, Long fkProvedId, Long fkProvinceId) {
        this.libelle = libelle;
        this.slug = slug;
        this.fkProvedId = fkProvedId;
        this.fkProvinceId = fkProvinceId;
    }

    // Méthodes utilitaires Panache
    public static Territoire findBySlug(String slug) {
        return find("slug", slug).firstResult();
    }

    public static List<Territoire> findByProvedId(Long provedId) {
        return list("fkProvedId", provedId);
    }

    public static List<Territoire> findByProvinceId(Long provinceId) {
        return list("fkProvinceId", provinceId);
    }

    // Getters et Setters si nécessaire
    // (optionnel avec PanacheEntityBase)

}
