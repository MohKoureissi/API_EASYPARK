package SoutenanceApp.EasyPark.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPaiement;
    @Column(nullable = false, name = "montant")
    @NotNull(message = "Champs montant vide")
    private long montant;
    @Column(nullable = false, name = "date")
    @NotNull(message = "Ce champs est vide")
    private LocalDate date;
    @Column(nullable = false, name = "photoIdentite")
    @NotNull(message = "Ce champs est vide")
    private String photoIdentite;

    @OneToOne
    @JoinColumn(name = "idVoiture")
    private Voiture voiture;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
}
