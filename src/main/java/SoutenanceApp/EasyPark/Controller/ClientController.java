package SoutenanceApp.EasyPark.Controller;

import SoutenanceApp.EasyPark.Modele.AdminParking;
import SoutenanceApp.EasyPark.Modele.Client;
import SoutenanceApp.EasyPark.Service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("client")
@CrossOrigin
public class ClientController {
    @Autowired
    private final ClientService clientService;

    @PostMapping("/create")
    @Operation(summary = "Creation d'un client")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client){
       return new ResponseEntity<>(clientService.create(client), HttpStatus.OK);
    }

    @GetMapping("/read")
    @Operation(summary = "Liste des clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Client.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    public ResponseEntity<List<Client>> getAllClient(){
        return new ResponseEntity<>(clientService.getAllClient(), HttpStatus.OK);
    }

    @GetMapping("/read/{idClient}")
    @Operation(summary = "Lire un client par son id")
    public ResponseEntity<Client> getClient(@Valid @PathVariable long idClient){
        return new ResponseEntity<>(clientService.getClientById(idClient), HttpStatus.OK);
    }

    @DeleteMapping("delete/{idClient}")
    @Operation(summary = "Suppression d'un Client")
    public String supprimer(@Valid @PathVariable long idClient){
        return clientService.delete(idClient);
    }

    @PutMapping("/update")
    @Operation(summary = "Modification d'un client")
    public Client update(@Valid @RequestBody Client client){
        return clientService.updateClient(client);
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion d'un client")
    public  Object connexion(@RequestParam("email") String email,
                             @RequestParam("motDePasse") String motDePasse) {
        return clientService.connexion(email, motDePasse);
    }
}
