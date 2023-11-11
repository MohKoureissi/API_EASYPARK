package SoutenanceApp.EasyPark.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class AdminParking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAdminParking;
    @Column(nullable = false, name = "agrementParking")
    @NotNull(message = "Ce champs vide")
    private  String agrementParking;
    @Column(nullable = false, name = "nomPrenom")
    @NotNull(message = "Ce champs vide")
    private String nomPrenom;
    @Column(nullable = false, name = "email")
    @NotNull(message = "Ce champs vide")
    private String email;
    @Column(nullable = false, name = "motdepasse")
    @NotNull(message = "Ce champs vide")
    private String motdepasse;
    @Column(name = "photo")
    private String photo;
    @Column(nullable = false, name = "nomParking")
    @NotNull(message = "Ce champs vide")
    private String nomParking;
    @Column(nullable = false, name = "adresseParking")
    @NotNull(message = "Ce champs vide")
    private String adresseParking;
    @Column(nullable = false, name = "acces")
    private Boolean acces;

    @OneToMany(mappedBy = "adminParking", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Voiture> voitureList= new ArrayList<>();

    @OneToMany(mappedBy = "adminParking", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Maintenance> maintenanceList= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idSuperAdmin")
    private SuperAdmin superAdmin;
}
