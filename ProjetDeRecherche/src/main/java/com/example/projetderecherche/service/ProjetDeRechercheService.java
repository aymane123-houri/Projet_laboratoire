package com.example.projetderecherche.service;


import com.example.projetderecherche.Repository.ProjetDeRechercheRepository;
import com.example.projetderecherche.entities.ProjetDeRecherche;
import com.example.projetderecherche.modele.Chercheur;
import com.example.projetderecherche.modele.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjetDeRechercheService {


    private ProjetDeRechercheRepository projetDeRechercheRepository;
    private RestTemplate restTemplate ;

    public ProjetDeRechercheService(ProjetDeRechercheRepository projetDeRechercheRepository) {
        this.projetDeRechercheRepository = projetDeRechercheRepository;
        this.restTemplate = new RestTemplate();
    }

    public ProjetDeRecherche createProjet(ProjetDeRecherche projet) {
        return projetDeRechercheRepository.save(projet);
    }

    public List<ProjetDeRecherche> getAllProjets() {

        List<ProjetDeRecherche> projetDeRechercheList= projetDeRechercheRepository.findAll();
        if (projetDeRechercheList != null){

            for (ProjetDeRecherche p : projetDeRechercheList){
                Enseignant e = restTemplate.getForObject("http://localhost:8086/Enseignants/"+p.getId_e(),Enseignant.class);
                p.setEnseignant(e);

                Chercheur c = restTemplate.getForObject("http://localhost:8081/chercheurs/"+p.getId_c(),Chercheur.class);
                p.setChercheur(c);
            }

        }

        return projetDeRechercheList;
    }

    public ProjetDeRecherche getProjetById(Long id) {

        ProjetDeRecherche projetDeRecherche = projetDeRechercheRepository.findById(id).orElse(null);
        if(projetDeRecherche!= null){
            projetDeRecherche.setEnseignant( restTemplate.getForObject("http://localhost:8086/Enseignants/"+ projetDeRecherche.getId_e(), Enseignant.class) );
            projetDeRecherche.setChercheur( restTemplate.getForObject("http://localhost:8081/chercheurs/"+ projetDeRecherche.getId_c(), Chercheur.class) );
        }
        return  projetDeRecherche;
    }

    public ProjetDeRecherche updateProjet(Long id, ProjetDeRecherche updatedProjet) {
        return projetDeRechercheRepository.findById(id).map(projet -> {
            projet.setTitre(updatedProjet.getTitre());
            projet.setDescription(updatedProjet.getDescription());
            projet.setId_e(updatedProjet.getId_e());
            projet.setId_c(updatedProjet.getId_c());
            return projetDeRechercheRepository.save(projet);
        }).orElseThrow(() -> new RuntimeException("Projet non trouvé"));
    }

    public void deleteProjet(Long id) {
        projetDeRechercheRepository.deleteById(id);
    }
}
