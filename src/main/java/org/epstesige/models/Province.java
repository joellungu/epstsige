package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "province")
public class Province extends PanacheEntity {

    public Long userId;
    public String libelle;
    public String chefLieu;
    public String slug;
    public LocalDateTime createdAt;
    public Boolean isDeleted = false;
}
