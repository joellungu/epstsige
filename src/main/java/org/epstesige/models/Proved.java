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
@Table(name = "proved")
public class Proved extends PanacheEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // public Long id;

    @Column(name = "fk_province_id")
    public Long fkProvinceId;

    @Column(nullable = false)
    public String libelle;

    @Column(nullable = false, unique = true)
    public String slug;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    // Relation ManyToOne optionnelle (si vous voulez lier directement à l'entité Province)
    @ManyToOne
    @JoinColumn(name = "fk_province_id", insertable = false, updatable = false)
    public Province province;

    // Constructeurs
    public Proved() {
    }

    public Proved(String libelle, String slug, Long fkProvinceId) {
        this.libelle = libelle;
        this.slug = slug;
        this.fkProvinceId = fkProvinceId;
    }

    // Méthodes utilitaires Panache
    public static Proved findBySlug(String slug) {
        return find("slug", slug).firstResult();
    }

    public static List<Proved> findByProvinceId(Long provinceId) {
        return list("fkProvinceId", provinceId);
    }

    // Getters et Setters si nécessaire
    // (optionnel car les champs sont publics avec PanacheEntityBase)
}
