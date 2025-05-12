package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "province")
public class Province extends PanacheEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // public Long id;

    @Column(name = "user_id")
    public Long userId;

    @Column(nullable = false)
    public String libelle;

    @Column(name = "chef_lieu")
    public String chefLieu;

    @Column(nullable = false, unique = true)
    public String slug;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "is_deleted")
    public Boolean isDeleted = false;

    // Constructeurs
    public Province() {
    }

    public Province(String libelle, String chefLieu, String slug, Long userId) {
        this.libelle = libelle;
        this.chefLieu = chefLieu;
        this.slug = slug;
        this.userId = userId;
    }

    // Méthodes utilitaires Panache
    public static Province findBySlug(String slug) {
        return find("slug", slug).firstResult();
    }

    public static List<Province> findNotDeleted() {
        return list("isDeleted", false);
    }

    // Getters et Setters si nécessaire
    // (optionnel car les champs sont publics avec PanacheEntityBase)

}
