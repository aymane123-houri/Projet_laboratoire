package com.example.chercheur.service;


import com.example.chercheur.Repository.ChercheurRepository;
import com.example.chercheur.entities.Chercheur;
import com.example.chercheur.modele.Enseigant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChercheurService {


    private ChercheurRepository chercheurRepository;

    private RestTemplate restTemplate ;

    public ChercheurService(ChercheurRepository chercheurRepository) {
        this.chercheurRepository = chercheurRepository;
        this.restTemplate = new RestTemplate();
    }

    public Chercheur CreateChercheur (Chercheur chercheur){
        return chercheurRepository.save(chercheur);
    }

    public List <Chercheur> GetAllChercheur(){

        List<Chercheur>  chercheurList = chercheurRepository.findAll();
        if (chercheurList != null){

            for (Chercheur e : chercheurList){
                Enseigant E = restTemplate.getForObject("http://localhost:8086/Enseignants/"+e.getId_e(),Enseigant.class);
                e.setEnseigant(E);
            }
        }

        return chercheurList;
    }

    public Chercheur GetByIdChercheur(Long id){
        Chercheur chercheur= chercheurRepository.findById(id).orElse(null);
        if(chercheur!= null){
            chercheur.setEnseigant( restTemplate.getForObject("http://localhost:8086/Enseignants/"+ chercheur.getId_e(), Enseigant.class) );
        }
        return  chercheur;
    }



    public void DeleteChercheur(Long id){
        chercheurRepository.deleteById(id);
    }

    public Chercheur UpdateCompte(Long id, Chercheur New){

        return chercheurRepository.findById(id).map(chercheur -> {
            chercheur.setNom(New.getNom());
            chercheur.setPrenom(New.getPrenom());
            chercheur.setNum_Insc(New.getNum_Insc());
            chercheur.setEmail(New.getEmail());
            chercheur.setId_e(New.getId_e());
            chercheur.setPrenom(New.getPrenom());
            return  chercheurRepository.save(chercheur);
        }).orElseThrow(() -> new RuntimeException("non trouvé"));

    }

}
