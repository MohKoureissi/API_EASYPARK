package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.AdminParking;
import SoutenanceApp.EasyPark.Service.AdminParkingService;
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
@RequestMapping("/adminParking")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AdminParkingController {
    @Autowired
    private final AdminParkingService adminParkingService;          //Injection de dependance

    @PostMapping("/create")
    @Operation(summary = "Création d'un AdminParking")
    public ResponseEntity<AdminParking> createAdminParking(
            @Valid @RequestParam("adminParking") String adminParkString,
            @RequestParam(value = "agrement", required = false) MultipartFile agrement) throws Exception {
        AdminParking adminParking = new JsonMapper().readValue(adminParkString, AdminParking.class);

        AdminParking savedAdminParking = adminParkingService.saveAdminWithPhotos(adminParking, agrement);

        return new ResponseEntity<>(savedAdminParking, HttpStatus.CREATED);
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
    public ResponseEntity<String> tochangeAccess(@PathVariable long idAdminParking){
        adminParkingService.changeAccess(idAdminParking);
        return ResponseEntity.ok("Access de l'AdminParking modifié avec succes");
    }


    @Operation(summary = "Suppression d'un Admin Parkin")
    @DeleteMapping("/delete/{idAdminParking}")
    public String supprimer(@Valid @PathVariable long idAdminParking){
        return adminParkingService.deleteAdminById(idAdminParking);
    }

    @GetMapping("/login")
    @Operation(summary = "Connexion d'un Admin Parking")
    public AdminParking connexion(@RequestParam("email")  String email,
                            @RequestParam("motdepasse")  String motdepasse) {
        return adminParkingService.connexionAdmin(email, motdepasse);
    }

}
