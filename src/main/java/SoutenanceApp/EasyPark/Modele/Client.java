package SoutenanceApp.EasyPark.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(nullable = false, name = "confirmation")
    @NotNull(message = "Ce champs est vide")
    private String confirmation;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Paiement> paiementList = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Location> locationList= new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Achat> achatList = new ArrayList<>();
}
