package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User extends PanacheEntity {

    @Column(name = "is_deleted")
    public boolean isDeleted;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "slug")
    public String slug;

    @Column(name = "about", columnDefinition = "TEXT")
    public String about;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "name")
    public String name;

    @Column(name = "active")
    public boolean active;

    @Column(name = "phone")
    public String phone;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;

    @Column(name = "roles")
    public String roles; // Ou utilisez une collection si c'est une relation many-to-many

    @Column(name = "username")
    public String username;

    @Column(name = "fk_territoire_id")
    public Long territoireId;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "picture_id")
    public Long pictureId;

    @Column(name = "fk_sous_proved_id")
    public Long sousProvedId;

    @Column(name = "fk_proved_id")
    public Long provedId;

    @Column(name = "fk_province_id")
    public Long provinceId;

    @Column(name = "mdp")
    public String mdp;

    // Getters et setters si nécessaire
    // Méthodes personnalisées du repository
}