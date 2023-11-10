package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.Client;
import SoutenanceApp.EasyPark.Modele.Paiement;
import SoutenanceApp.EasyPark.Service.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/paiement")
public class PaiementController {
    @Autowired
    private final PaiementService paiementService;

    @PostMapping("/create")
    @Operation(summary = "Effectuer Nouvelle Paiement")
    public ResponseEntity<Paiement> create(@Valid @RequestBody Paiement paiement){
        return new ResponseEntity<>(paiementService.create(paiement), HttpStatus.OK);
    }

    @GetMapping("/read")
    @Operation(summary = "Liste des clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Paiement.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    public ResponseEntity<List<Paiement>> getAll(){
        return new ResponseEntity<>(paiementService.getAllPaiement(), HttpStatus.OK);
    }
    @GetMapping("/read/{idPaiement}")
    @Operation(summary = "Liste des clients")
    public ResponseEntity<Paiement> getById(@Valid @PathVariable long idPaiement){
        return new ResponseEntity<>(paiementService.getPaiementById(idPaiement), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Modification d'un paiement")
    public ResponseEntity<Paiement> updatePaiement(@Valid @RequestBody Paiement paiement){
        return new ResponseEntity<>(paiementService.updatePaiement(paiement), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idPaiement}")
    @Operation(summary = "Suppression d'un paiement")
    public String delete(@Valid @PathVariable long idPaiement){
        return paiementService.deletePaiement(idPaiement);
    }
}
