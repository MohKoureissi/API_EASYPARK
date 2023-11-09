package SoutenanceApp.EasyPark.Repositories;

import SoutenanceApp.EasyPark.Modele.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findByIdClient(long idClient);

    public Client findByEmailAndMotdepasse(String email, String motdepasse);

    public Client findByEmail(String email);
}
