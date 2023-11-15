package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.Paiement;
import SoutenanceApp.EasyPark.Modele.SuperAdmin;
import SoutenanceApp.EasyPark.Repositories.SuperAdminRepository;
import SoutenanceApp.EasyPark.Service.SuperAdminService;
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

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/superAdmin")
public class SuperAdminController {
    @Autowired
     SuperAdminService superAdminService;

    @Autowired
    SuperAdminRepository superAdminRepository;

    @PostMapping("/create")
    @Operation(summary = "Creation du Super Admin")
    public ResponseEntity<SuperAdmin> createSuperAdmin(@Valid @RequestBody SuperAdmin superAdmin){
        return new ResponseEntity<>(superAdminService.create(superAdmin), HttpStatus.OK);
    }
    @GetMapping("/read")
    @Operation(summary = "Liste SuperAdmin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = SuperAdmin.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    public ResponseEntity<List<SuperAdmin>> getAll(){
        return new ResponseEntity<>(superAdminService.read(), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Modification du SuperAdmin")
    public ResponseEntity<SuperAdmin> update(@Valid @RequestBody SuperAdmin superAdmin){
        return new ResponseEntity<>(superAdminService.update(superAdmin), HttpStatus.OK);
    }
}
