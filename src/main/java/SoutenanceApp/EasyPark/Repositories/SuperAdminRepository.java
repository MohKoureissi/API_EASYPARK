package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository <SuperAdmin,Long> {
    public SuperAdmin findByIdSuperAdmin(long idSuperAdmin);
    public SuperAdmin findByEmail(String email);

    public SuperAdmin findByMotdepasseAndEmail(String motdepasse, String email);
}
