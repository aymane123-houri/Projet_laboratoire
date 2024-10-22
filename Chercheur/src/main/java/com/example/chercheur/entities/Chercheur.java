package com.example.chercheur.entities;

import com.example.chercheur.modele.Enseigant;
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

public class Chercheur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom", length = 255)
    private String nom;

    @Column(name = "prenom", length = 255)
    private String prenom;

    @Column(name = "Num_Insc" ,length = 255)
    private String Num_Insc ;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "id_e")
    private Long id_e;

    @Transient
    private Enseigant enseigant;


}
