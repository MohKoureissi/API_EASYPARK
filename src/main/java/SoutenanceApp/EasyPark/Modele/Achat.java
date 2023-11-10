package SoutenanceApp.EasyPark.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idAchat;
    @Column(nullable = false, name = "dateAchat")
    @NotNull(message = "Le champs date est vide")
    private LocalDate dateAchat;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idVoiture")
    private Voiture voiture;
}
