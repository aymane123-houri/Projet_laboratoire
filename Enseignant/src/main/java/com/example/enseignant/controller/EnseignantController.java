package com.example.enseignant.controller;

import com.example.enseignant.entities.Enseignant;
import com.example.enseignant.service.EnseignantService;
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
@RequestMapping("/Enseignants")
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Enseignants",
                description = " Gerer les opération des Enseignant",
                version = "1.0.0"
        ),

        servers = @Server(
                url = "http://localhost:8086/"
        )
)
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;

    // POST: Create a new Enseignant
    @Operation(
            summary = "Ajouter Un Enseignant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "Application/json",
                            schema = @Schema(implementation = Enseignant.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "ajouter par succéses",
                            content = @Content(
                                    mediaType = "Application/json",
                                    schema = @Schema(implementation = Enseignant.class))
                    ),

                    @ApiResponse(responseCode = "400",description = "erreur données"),
                    @ApiResponse(responseCode ="500", description = "erreur server")
            }
    )
    @PostMapping
    public ResponseEntity<Enseignant> addEnseignant(@RequestBody Enseignant enseignant) {
        Enseignant savedEnseignant = enseignantService.createEnseignant(enseignant);
        return ResponseEntity.ok(savedEnseignant);
    }





    // GET: Retrieve all Enseignants
    @Operation(
            summary="Recuprer Liste des Enseignants",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Enseignant.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Paramètre d'entrée non valide")
            }  )
    @GetMapping
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        return ResponseEntity.ok(enseignants);
    }






    // GET: Retrieve an Enseignant by ID
    @Operation(
            summary = "recuperer un Enseignant par son Id",
            parameters = @Parameter(
                    name = "id",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "bien recuperer",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Enseignant.class))
                    ),
                    @ApiResponse(responseCode = "404",description = "Enseignants pas trouvé ")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id) {
        Enseignant enseignant = enseignantService.getEnseignantById(id);
        if (enseignant != null) {
            return ResponseEntity.ok(enseignant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Update an Enseignant by ID
    @Operation(
            summary = "Mettre à jour un Enseignant par son ID",
            parameters = {
                    @Parameter(name = "id", required = true, description = "ID de l'enseignant à mettre à jour")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Enseignant.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Mise à jour réussie",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Enseignant.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Enseignant non trouvé"),
                    @ApiResponse(responseCode = "400", description = "Données invalides")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant updatedEnseignant) {
        try {
            Enseignant enseignant = enseignantService.updateEnseignant(id, updatedEnseignant);
            return ResponseEntity.ok(enseignant);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete an Enseignant by ID
    @Operation(
            summary = "Supprimer un Enseignant par son ID",
            parameters = @Parameter(name = "id", required = true, description = "ID de l'enseignant à supprimer"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Suppression réussie"),
                    @ApiResponse(responseCode = "404", description = "Enseignant non trouvé")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
        return ResponseEntity.noContent().build();
    }
}