package com.example.enseignant.service;

import com.example.enseignant.Repository.EnseignantRepository;
import com.example.enseignant.entities.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    public Enseignant createEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    public Enseignant getEnseignantById(Long id) {
        return enseignantRepository.findById(id).orElse(null);
    }

    public Enseignant updateEnseignant(Long id, Enseignant updatedEnseignant) {
        return enseignantRepository.findById(id).map(enseignant -> {
            enseignant.setNom(updatedEnseignant.getNom());
            enseignant.setPrenom(updatedEnseignant.getPrenom());
            enseignant.setCne(updatedEnseignant.getCne());
            enseignant.setEmail(updatedEnseignant.getEmail());
            enseignant.setPassword(updatedEnseignant.getPassword());
            enseignant.setThematique(updatedEnseignant.getThematique());
            return enseignantRepository.save(enseignant);
        }).orElseThrow(() -> new RuntimeException("Enseignant non trouvé"));
    }

    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }
}