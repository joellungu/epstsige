package org.epstesige.models;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "ville")
public class Ville extends PanacheEntity {

    public Long fkProvinceId;
    public String libelle;
    public String slug;
    public LocalDateTime createdAt;
}
