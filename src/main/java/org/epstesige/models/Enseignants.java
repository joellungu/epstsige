package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Entity
public class Enseignants extends PanacheEntity {
    //
    public Long FormStId;

    @Convert(converter = HashMapConverter.class)
    @Column(columnDefinition = "TEXT") // ou "JSONB" si vous utilisez PostgreSQL
    public ArrayList enseignants = new ArrayList();
}
