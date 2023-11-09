package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.AdminParking;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Service.VoitureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("voiture")
@AllArgsConstructor
public class VoitureController {
    @Autowired
    private final VoitureService voitureService;

    @PostMapping("/create")
    @Operation(summary = "Ajouter nouvelle Voiture")
    public ResponseEntity<Voiture> create(
            @Valid @RequestParam("voiture") String voitureString,
            @RequestParam(value = "photo", required = false) MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4 ) throws Exception{
        Voiture voiture= new Voiture();
        try{
            voiture= new JsonMapper().readValue(voitureString, Voiture.class);
        } catch(JsonProcessingException e){
            throw new Exception(e.getMessage());
        }
        Voiture saveVoiture= voitureService.addVoiture(voiture, photo1, photo2,photo3, photo4);
        return new ResponseEntity<>(saveVoiture, HttpStatus.CREATED);
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

}
