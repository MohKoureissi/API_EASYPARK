package SoutenanceApp.EasyPark.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idVoiture;
    @Column(nullable = false, name = "marque")
    @NotNull(message = "Ce champs est vide")
    private String marque;
    @Column(nullable = false, name = "modele")
    @NotNull(message = "Ce champs est vide")
    private String modele;
    @Column(nullable = false, name = "anneeSortie")
    @NotNull(message = "Ce champs est vide")
    private String anneeSortie;
    @Column(nullable = false, name = "photo1")
    @NotNull(message = "Champs photo vide")
    private String photo1;
    @Column(nullable = false, name = "photo2")
    @NotNull(message = "Champs photo vide")
    private String photo2;
    @Column(nullable = false, name = "photo3")
    @NotNull(message = "Champs photo vide")
    private String photo3;
    @Column( name = "photo4")
    @NotNull(message = "Champs photo vide")
    private String photo4;
    @Column(nullable = false, name = "prix")
    @NotNull(message = "Ce champs est vide")
    private long prix;
    @Column(nullable = false, name = "quantite")
    @NotNull(message = "Ce champs est vide")
    private long quantite;
    @Column(nullable = false, name = "type")
    @NotNull(message = "Ce champs est vide")
    private String type;
    @Column(nullable = false, name = "disponibilite")
    @NotNull(message = "Ce champs est vide")
    private Boolean disponibilite;


}
