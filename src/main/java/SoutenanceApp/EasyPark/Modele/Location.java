package SoutenanceApp.EasyPark.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLocation;
    @Column(nullable = false, name = "dateLocation")
    @NotNull(message = "Le champs date est vide")
    private LocalDate dateLocation;
    @Column(nullable = false, name = "duree")
    @NotNull(message = "Le champs duree est vide")
    private String duree;

    @ManyToOne
    @JoinColumn(name = "idVoiture")
    private Voiture voiture;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

}
