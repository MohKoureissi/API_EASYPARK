package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    public Location findByIdLocation(long idLocation);
}
