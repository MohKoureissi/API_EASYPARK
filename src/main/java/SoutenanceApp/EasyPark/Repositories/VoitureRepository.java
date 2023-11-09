package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VoitureRepository extends JpaRepository<Voiture ,Long > {
    public Voiture findByIdVoiture(long idVoiture);
}
