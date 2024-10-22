package com.example.projetderecherche.entities;

import com.example.projetderecherche.modele.Chercheur;
import com.example.projetderecherche.modele.Enseignant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjetDeRecherche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", length = 255)
    private String titre;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "id_c")
    private Long id_c;

    @Transient
    private Chercheur chercheur;
    @Column(name = "id_e")
    private Long id_e;

    @Transient
    private Enseignant enseignant;

}