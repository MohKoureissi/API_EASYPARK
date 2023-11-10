package SoutenanceApp.EasyPark.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idSuperAdmin;
    @Column(nullable = false, name = "email")
    @NotNull(message = "Ce champs est vide")
    private String email;
    @Column(nullable = false, name = "motdepasse")
    @NotNull(message = "Ce champs est vide")
    private String motdepasse;

    @OneToMany(mappedBy = "superAdmin")
    @JsonIgnore
    private List<AdminParking> adminParkingList= new ArrayList<>();
}
