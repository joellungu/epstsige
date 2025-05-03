package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "sous_proved")
public class SousProved extends PanacheEntity {

    public Long fkTerritoireId;
    public Long fkProvedId;
    public String libelle;
    public String lieuImplantation;
    public String slug;
    public LocalDateTime createdAt;
}
