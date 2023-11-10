package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.Achat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchatRepository extends JpaRepository<Achat, Long> {
    public Achat findByIdAchat(long idAchat);
}
