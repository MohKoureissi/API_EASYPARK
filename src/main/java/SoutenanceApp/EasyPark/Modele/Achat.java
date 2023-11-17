package SoutenanceApp.EasyPark.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAchat;
    @Column(nullable = false, name = "dateAchat")
    @NotNull(message = "Le champs date est vide")
    private LocalDate dateAchat;
    @Column(nullable = false, name = "prix")
    @NotNull(message = "Le champs prix est vide")
    private long prix;

    @ManyToOne
    @JoinColumn()
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idVoiture")
    private Voiture voiture;
}
