package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.AdminParking;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Repositories.VoitureRepository;
import SoutenanceApp.EasyPark.Service.VoitureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("voiture")
@AllArgsConstructor
public class VoitureController {
    @Autowired
    private final VoitureService voitureService;

    @Autowired
    private VoitureRepository voitureRepository; 

    @PostMapping("/create")
    @Operation(summary = "Ajouter nouvelle Voiture")
    public ResponseEntity<Voiture> create(
            @Valid @RequestParam("voiture") String voitureString,
            @RequestParam(value = "photo2", required = false) MultipartFile photo2,
            @RequestParam(value = "photo3", required = false) MultipartFile photo3,
            @RequestParam(value = "photo4", required = false) MultipartFile photo4) throws Exception {
        Voiture voiture = new JsonMapper().readValue(voitureString, Voiture.class);

        Voiture savedVoiture = voitureService.saveVoitureWithPhotos(voiture, photo2, photo3, photo4);

        return new ResponseEntity<>(savedVoiture, HttpStatus.CREATED);
    }

    @GetMapping("read")
    @Operation(summary = "Liste de tout les Voitures")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Voiture.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    public ResponseEntity<List<Voiture>> getVoiture(){
        return new ResponseEntity<>(voitureService.getAllVoiture(), HttpStatus.OK);
    }

    @GetMapping("list/{idAdminParking}")
    @Operation(summary = "Affichage de la liste des voitures a travers l'idAdminParking")
    public ResponseEntity<List<Voiture>> ListVoiture(@PathVariable long idAdminParking){
        return new ResponseEntity<>(voitureService.ListParAdminParking(idAdminParking), HttpStatus.OK);
    }

    @GetMapping("/read/{idVoiture}")
    @Operation(summary = "Lire voiture par id ")
    public ResponseEntity<Voiture> getVoitureById(@Valid @PathVariable long idVoiture){
        return new ResponseEntity<>(voitureService.getVoitureById(idVoiture), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idVoiture}")
    @Operation(summary = "Supprimer une Voiture par ID")
    public ResponseEntity<String> delete(@PathVariable Long idVoiture) throws EntityNotFoundException {
        voitureService.deleteVoiture(idVoiture);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @DeleteMapping("/delete/{idVoiture}")
//    @Operation(summary = "Suppression d'une voiture")
//    public String supprimer(@Valid @PathVariable long idVoiture){
//        return voitureService.deleteVoiture(idVoiture);
//    }
@PutMapping("/update/{id}")
@Operation(summary = "Modifier une Voiture par ID")
public ResponseEntity<Voiture> update(
        @PathVariable Long idVoiture,
        @Valid @RequestParam("voiture") String voitureString,
        @RequestParam(value = "photo2", required = false) MultipartFile photo2,
        @RequestParam(value = "photo3", required = false) MultipartFile photo3,
        @RequestParam(value = "photo4", required = false) MultipartFile photo4) throws Exception {

    Voiture updatedVoiture = new JsonMapper().readValue(voitureString, Voiture.class);

    Voiture savedVoiture = voitureService.updateVoiture(idVoiture, updatedVoiture, photo2, photo3, photo4);

    return new ResponseEntity<>(savedVoiture, HttpStatus.OK);
}
}
