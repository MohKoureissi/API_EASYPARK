package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.Voiture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture ,Long > {
    public Voiture findByIdVoiture(long idVoiture);
    public List<Voiture> findByAdminParkingIdAdminParking(long idAdminParking);
}
