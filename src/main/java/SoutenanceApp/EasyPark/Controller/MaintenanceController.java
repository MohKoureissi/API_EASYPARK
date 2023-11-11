package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.Achat;
import SoutenanceApp.EasyPark.Modele.Maintenance;
import SoutenanceApp.EasyPark.Service.MaintenanceService;
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
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    private final MaintenanceService maintenanceService;

    @PostMapping("/create")
    @Operation(summary = "Nouvelle Maintenance")
    public ResponseEntity<Maintenance> create(@Valid @RequestBody Maintenance maintenance){
        return new ResponseEntity<>(maintenanceService.create(maintenance), HttpStatus.OK);
    }

    @GetMapping("/read")
    @Operation(summary = "Liste des Maintenances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Achat.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    public ResponseEntity<List<Maintenance>> getAll(){
        return new ResponseEntity<>(maintenanceService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{idMaintenance}")
    @Operation(summary = "Lire une Maintenance par son ID")
    public ResponseEntity<Maintenance> getById(@Valid @PathVariable long idMaintenance){
        return new ResponseEntity<>(maintenanceService.getById(idMaintenance), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Modification d'une maintenance")
    public ResponseEntity<Maintenance> modifier(@Valid @RequestBody Maintenance maintenance){
        return new ResponseEntity<>(maintenanceService.updateMaintenance(maintenance), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Suppression d'une maintenance")
    public String supprimer(@Valid @PathVariable long idMaintenance){
        return maintenanceService.deleteMaintenance(idMaintenance);
    }
}
