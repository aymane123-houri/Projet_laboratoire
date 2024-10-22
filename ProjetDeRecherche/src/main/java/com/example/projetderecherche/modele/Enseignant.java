package com.example.projetderecherche.modele;

public class Enseignant {
    private Long id;
    private String nom;
    private String prenom;
    private String CNE;
    private String email;
    private String password;
    private String thematique;

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

    public String getCNE() {
        return CNE;
    }

    public Enseignant(Long id, String nom, String prenom, String CNE, String email, String password, String thematique) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.CNE = CNE;
        this.email = email;
        this.password = password;
        this.thematique = thematique;
    }
}