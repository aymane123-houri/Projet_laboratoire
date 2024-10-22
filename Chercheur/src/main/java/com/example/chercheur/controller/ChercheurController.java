package com.example.chercheur.controller;

import com.example.chercheur.entities.Chercheur;
import com.example.chercheur.service.ChercheurService;
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
@RequestMapping("/chercheurs")
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Chercheurs",
                description = " Gerer les opération de Chercheurs",
                version = "1.0.0"
        ),

        servers = @Server(
                url = "http://localhost:8081/"
        )
)
public class ChercheurController {
    @Autowired
    ChercheurService chercheurService;

    @Operation(
            summary = "Ajouter Un Chercheur",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "Application/json",
                            schema = @Schema(implementation = Chercheur.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "ajouter par succéses",
                            content = @Content(
                                    mediaType = "Application/json",
                                    schema = @Schema(implementation = Chercheur.class))
                    ),

                    @ApiResponse(responseCode = "400",description = "erreur données"),
                    @ApiResponse(responseCode ="500", description = "erreur server")
            }
    )
    @PostMapping
    public ResponseEntity <Chercheur> add(@RequestBody Chercheur chercheur){
        Chercheur chercheur1=chercheurService.CreateChercheur(chercheur);
        return ResponseEntity.ok(chercheur1);
    }


    @Operation(
            summary="Recuprer Liste des Chercheurs",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Chercheur.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Paramètre d'entrée non valide")
            }  )
    @GetMapping
    public ResponseEntity<List<Chercheur>> getAllChercheurs() {
        List<Chercheur> chercheurs = chercheurService.GetAllChercheur();
        return ResponseEntity.ok(chercheurs);
    }

    // GET: Retrieve a researcher by ID
    @Operation(
            summary = "recuperer un Chercheur par son Id",
            parameters = @Parameter(
                    name = "id",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "bien recuperer",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Chercheur.class))
                    ),
                    @ApiResponse(responseCode = "404",description = "Enseignants pas trouvé ")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Chercheur> getChercheurById(@PathVariable Long id) {
        Chercheur chercheur = chercheurService.GetByIdChercheur(id);
        if (chercheur != null) {
            return ResponseEntity.ok(chercheur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Update a researcher by ID
    @PutMapping("/{id}")
    public ResponseEntity<Chercheur> updateChercheur(@PathVariable Long id, @RequestBody Chercheur updatedChercheur) {
        try {
            Chercheur updated = chercheurService.UpdateCompte(id, updatedChercheur);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a researcher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChercheur(@PathVariable Long id) {
        chercheurService.DeleteChercheur(id);
        return ResponseEntity.noContent().build();
    }
}


