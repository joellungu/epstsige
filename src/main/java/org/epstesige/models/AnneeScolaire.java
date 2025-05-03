package org.epstesige.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "annee_scolaire")
public class AnneeScolaire extends PanacheEntity {

    @Column(name = "valid")
    public boolean valid;

    @Column(name = "active")
    public boolean active;

    @Column(name = "generate")
    public boolean generate;

    @Column(name = "open")
    public boolean open;

    @Column(name = "lib_annee_scolaire")
    public String libAnneeScolaire;

    // Getters et setters si nécessaire
    // Méthodes personnalisées du repository

    // Exemple de méthode de repository
    public static AnneeScolaire findByLibelle(String libelle) {
        return find("libAnneeScolaire", libelle).firstResult();
    }
}
