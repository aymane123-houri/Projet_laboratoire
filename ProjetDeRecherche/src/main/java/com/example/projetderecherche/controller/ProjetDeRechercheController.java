package com.example.projetderecherche.controller;


import com.example.projetderecherche.entities.ProjetDeRecherche;
import com.example.projetderecherche.service.ProjetDeRechercheService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Chercheurs",
                description = " Gerer les opération de Chercheurs",
                version = "1.0.0"
        ),

        servers = @Server(
                url = "http://localhost:8083/"
        )
)
public class ProjetDeRechercheController {

    @Autowired
    private ProjetDeRechercheService projetDeRechercheService;

    // POST: Create a new Projet
    @Operation(
            summary = "Ajouter un Projet de Recherche",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetDeRecherche.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Projet ajouté avec succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetDeRecherche.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Erreur données"),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur")
            }
    )
    @PostMapping
    public ResponseEntity<ProjetDeRecherche> addProjet(@RequestBody ProjetDeRecherche projet) {
        ProjetDeRecherche savedProjet = projetDeRechercheService.createProjet(projet);
        return ResponseEntity.ok(savedProjet);
    }

    // GET: Retrieve all Projets
    @Operation(
            summary = "Récupérer la liste des Projets de Recherche",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetDeRecherche.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Paramètre d'entrée non valide")
            }
    )
    @GetMapping
    public ResponseEntity<List<ProjetDeRecherche>> getAllProjets() {
        List<ProjetDeRecherche> projets = projetDeRechercheService.getAllProjets();
        return ResponseEntity.ok(projets);
    }

    // GET: Retrieve a Projet by ID
    @Operation(
            summary = "Récupérer un Projet de Recherche par son ID",
            parameters = @Parameter(
                    name = "id",
                    required = true,
                    description = "ID du projet à récupérer"
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Bien récupéré",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetDeRecherche.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Projet non trouvé")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProjetDeRecherche> getProjetById(@PathVariable Long id) {
        ProjetDeRecherche projet = projetDeRechercheService.getProjetById(id);
        if (projet != null) {
            return ResponseEntity.ok(projet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Update a Projet by ID
    @PutMapping("/{id}")
    @Operation(
            summary = "Mettre à jour un Projet de Recherche par son ID",
            parameters = @Parameter(name = "id", required = true, description = "ID du projet à mettre à jour"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetDeRecherche.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Mise à jour réussie",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetDeRecherche.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Projet non trouvé"),
                    @ApiResponse(responseCode = "400", description = "Données invalides")
            }
    )
    public ResponseEntity<ProjetDeRecherche> updateProjet(@PathVariable Long id, @RequestBody ProjetDeRecherche updatedProjet) {
        try {
            ProjetDeRecherche projet = projetDeRechercheService.updateProjet(id, updatedProjet);
            return ResponseEntity.ok(projet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a Projet by ID
    @Operation(
            summary = "Supprimer un Projet de Recherche par son ID",
            parameters = @Parameter(name = "id", required = true, description = "ID du projet à supprimer"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Suppression réussie"),
                    @ApiResponse(responseCode = "404", description = "Projet non trouvé")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        projetDeRechercheService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }
}
