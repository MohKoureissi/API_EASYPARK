package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.Location;
import SoutenanceApp.EasyPark.Modele.Paiement;
import SoutenanceApp.EasyPark.Service.LocationService;
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
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/create")
    @Operation(summary = "Enregistrer nouvelle Location")
    public ResponseEntity<Location> create(@Valid @RequestBody Location location){
        return new ResponseEntity<>(locationService.create(location), HttpStatus.OK);
    }

    @GetMapping("/read")
    @Operation(summary = "Liste des Locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Location.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    public ResponseEntity<List<Location>> getAll(){
        return new ResponseEntity<>(locationService.getAllLocation(), HttpStatus.OK);
    }

    @GetMapping("/read/{idLocation}")
    @Operation(summary = "Lire une location par son id")
    public ResponseEntity<Location> getById(@Valid @PathVariable long idLocation){
        return new ResponseEntity<>(locationService.getLocationById(idLocation), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Modification d'une Location")
    public ResponseEntity<Location> modifier(@Valid @RequestBody Location location){
        return new ResponseEntity<>(locationService.updateLocation(location), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idLocation}")
    @Operation(summary = "Suppression d'une location")
    public String supprimer(@Valid @PathVariable long idLocation){
        return  locationService.deleteLocation(idLocation);
    }
}
