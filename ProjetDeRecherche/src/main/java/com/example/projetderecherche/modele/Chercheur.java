package com.example.projetderecherche.modele;

import jakarta.persistence.*;

public class Chercheur {

    private Long id;

    private String nom;

    private String prenom;

    private String Num_Insc ;

    private String email;

    public Chercheur(Long id, String nom, String prenom, String num_Insc, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        Num_Insc = num_Insc;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNum_Insc() {
        return Num_Insc;
    }

    public void setNum_Insc(String num_Insc) {
        Num_Insc = num_Insc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
