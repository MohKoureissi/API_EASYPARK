package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.AdminParking;
import SoutenanceApp.EasyPark.Service.AdminParkingService;
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

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("adminParking")
@AllArgsConstructor
public class AdminParkingController {
    @Autowired
    private final AdminParkingService adminParkingService;          //Injection de dependance

    @PostMapping("/create")
    @Operation(summary = "Création d'un AdminParking")
    public ResponseEntity<AdminParking> createAdminParking(
            @Valid @RequestParam("adminParking") String adminParkingString,
            @RequestParam(value = "photo", required = false)MultipartFile multipartFile) throws Exception{
        AdminParking adminParking= new AdminParking();
        try{
            adminParking= new JsonMapper().readValue(adminParkingString, AdminParking.class);
        } catch(JsonProcessingException e){
            throw new Exception(e.getMessage());
        }
        AdminParking saveAdmin= adminParkingService.createAdminParking(adminParking, multipartFile);
        return new ResponseEntity<>(saveAdmin, HttpStatus.CREATED);
    }

    @GetMapping("read")
    @Operation(summary = "Liste de tout les AdminParking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = AdminParking.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    public ResponseEntity<List<AdminParking>> getAdmin(){
        return new ResponseEntity<>(adminParkingService.getAllAdmin(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    @Operation(summary = "Affichage d'un AdminParking")
    public ResponseEntity<AdminParking> getAdminById(@Valid @PathVariable long id){
        return new ResponseEntity<>(adminParkingService.getAdminById(id), HttpStatus.OK);
    }

    @PutMapping("/changeAccess/{idAdminParking}")
    public ResponseEntity<String> tochangeAccess(@PathVariable long idAdminParking) {
        adminParkingService.changeAccess(idAdminParking);
        return ResponseEntity.ok("Succès : Accès de l'adminParking modifié");
    }
/*
    @Operation(summary = "Modifier un AdminParking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Enseignant modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = AdminParking.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierEnseignant(@Valid @RequestBody AdminParking adminParking) {
        return adminParkingService.update(enseignant);
    }   */

    @Operation(summary = "Suppression d'un Admin Parkin")
    @DeleteMapping("/delete/{idAdminParking}")
    public String supprimer(@Valid @PathVariable long idAdminParking){
        return adminParkingService.deleteAdminById(idAdminParking);
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion d'un Admin Parking")
    public Object connexion(@RequestParam("email") String email,
                            @RequestParam("motDePasse") String motDePasse) {
        return adminParkingService.connexionAdmin(email, motDePasse);
    }

}
