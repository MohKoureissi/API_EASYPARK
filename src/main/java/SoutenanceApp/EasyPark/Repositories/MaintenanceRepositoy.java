package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepositoy extends JpaRepository<Maintenance, Long> {
}
