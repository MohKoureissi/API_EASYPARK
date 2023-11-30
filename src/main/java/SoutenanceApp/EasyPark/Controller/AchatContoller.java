package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.Achat;
import SoutenanceApp.EasyPark.Modele.SuperAdmin;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Service.AchatService;
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

@CrossOrigin(origins ="*" )
@RestController
@AllArgsConstructor
@RequestMapping("/achat")
public class AchatContoller {
    @Autowired
    private final AchatService achatService;

    @PostMapping("/create")
    @Operation(summary = "Enregistrer nouvelle Achat")
    public ResponseEntity<Achat> create(@Valid @RequestBody Achat achat){
       achatService.decrementeQuantiteVoiture(achat);
        return new ResponseEntity<>(achatService.create(achat), HttpStatus.OK);
    }

    @GetMapping("/read")
    @Operation(summary = "Liste des Achats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Achat.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    public ResponseEntity<List<Achat>> read(){
        return new ResponseEntity<>(achatService.getAllAchat(), HttpStatus.OK);
    }
    @GetMapping("/read/{idAchat}")
    @Operation(summary = "Lire achat par son id")
    public ResponseEntity<Achat> getById(@Valid @PathVariable long idAchat){
        return new ResponseEntity<>(achatService.getAchatById(idAchat), HttpStatus.OK);
    }

     @GetMapping("list/{idAdminParking}")
    @Operation(summary = "Affichage de la liste des ventes enregistrer par l'AdminParking")
    public ResponseEntity<List<Achat>> ListVoiture(@PathVariable long idAdminParking){
        return new ResponseEntity<>(achatService.ListParAdminParking(idAdminParking), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Modification d'un Achat")
    public ResponseEntity<Achat> modifier(@Valid @RequestBody Achat achat){
        return new ResponseEntity<>(achatService.updateAchat(achat), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{idAchat}")
    @Operation(summary = "Suppression d'un achat")
    public String supprimer(@Valid @PathVariable long idAchat){
        return achatService.delete(idAchat);
    }
}
