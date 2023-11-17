package SoutenanceApp.EasyPark.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMaintenance;
    @Column(nullable = false, name = "date")
    @NotNull(message = "Le champs date est vide")
    private LocalDate date;
    @Column(nullable = false, name = "prix")
    @NotNull(message = "Le champs prix est vide")
    private long prix;
    @Column(nullable = false, name = "description")
    @NotNull(message = "Le champs description est vide")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "idVoiture")
    private Voiture voiture;
}
