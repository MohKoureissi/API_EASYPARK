package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.Location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    public Location findByIdLocation(long idLocation);
    public List<Location> findByAdminParkingIdAdminParking(long idAdminParking);
}
