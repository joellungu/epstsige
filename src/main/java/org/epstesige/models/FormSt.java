package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;

import java.util.HashMap;
import java.util.Map;

@Entity
public class FormSt extends PanacheEntity {

    public String type;

    public String date;

    public String ids;

    @Convert(converter = HashMapConverter.class)
    @Column(columnDefinition = "TEXT") // ou "JSONB" si vous utilisez PostgreSQL
    public Map<String, Object> formulaire = new HashMap();

}
