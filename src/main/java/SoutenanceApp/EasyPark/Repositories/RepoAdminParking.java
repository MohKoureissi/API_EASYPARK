package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.AdminParking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoAdminParking extends JpaRepository<AdminParking, Long> {
    public AdminParking findByEmail(String email);        // Recherche par email

    public AdminParking findByMotdepasseAndEmail(String motdepass, String email);
    public AdminParking findByIdAdminParking(Long idAdminParking);     // Recherche par identifiant
}
