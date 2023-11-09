package SoutenanceApp.EasyPark.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idClient;
    @Column(nullable = false, name = "nom")
    @NotNull(message = "Ce champs est vide")
    private String nom;
    @Column(nullable = false, name = "prenom")
    @NotNull(message = "Ce champs est vide")
    private String prenom;
    @Column(nullable = false, name = "adresse")
    @NotNull(message = "Ce champs est vide")
    private String adresse;
    @Column(nullable = false, name = "email")
    @NotNull(message = "Ce champs est vide")
    private String email;
    @Column(nullable = false, name = "motdepasse")
    @NotNull(message = "Ce champs est vide")
    private String motdepasse;
}
