package SoutenanceApp.EasyPark.Repositories;


import SoutenanceApp.EasyPark.Modele.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement ,Long> {
    public Paiement findByIdPaiement(long idPaiement);
}
