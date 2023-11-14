package SoutenanceApp.EasyPark.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(nullable = false, name = "photo2")
    @NotNull(message = "Champs photo vide")
    private String photo2;
    @Column(nullable = false, name = "photo3")
    @NotNull(message = "Champs photo vide")
    private String photo3;
    @NotNull(message = "Champs photo vide")
    @Column(nullable = false, name = "photo4")
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
    @Column( name = "disponibilite")
    private Boolean disponibilite;

    @ManyToOne()
    @JoinColumn(name = "idAdminParking")
    private AdminParking adminParking;

    @OneToOne(mappedBy = "voiture")
    @JsonIgnore
    private Paiement paiement;

    @OneToMany(mappedBy = "voiture")
    private List<Maintenance> maintenanceList= new ArrayList<>();


}
