package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.Maintenance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepositoy extends JpaRepository<Maintenance, Long> {
    public Maintenance findByIdMaintenance(long idParking);
    public List<Maintenance> findByAdminParkingIdAdminParking(long idAdminParking);
}
