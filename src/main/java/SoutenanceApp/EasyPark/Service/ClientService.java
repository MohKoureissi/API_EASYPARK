package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Client;
import SoutenanceApp.EasyPark.Repositories.ClientRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.apache.bcel.generic.InstructionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository1){
        this.clientRepository= clientRepository1;
    }

    public Client create(Client client) {
        Client client1 = clientRepository.findByEmail(client.getEmail());
        if (client1 != null) {
            throw new EntityNotFoundException("Ce client existe déja");
        }
        return clientRepository.save(client);
    }

    public List<Client> getAllClient(){
      List<Client> clients= clientRepository.findAll();
      if(clients.isEmpty())
          throw new NoContentException("Pas de client enregistrer");
      return clients;
    }

    public Client getClientById(long idClient){
        Client client= clientRepository.findByIdClient(idClient);
        if(client == null)
            throw new NoContentException("Ce client n'existe pas");
        return client;
    }

    public String delete(long idClient){
        Client client= clientRepository.findByIdClient(idClient);
        if(client == null)
            throw new NoContentException("Ce client n'existe pas");
        clientRepository.delete(client);
        return "Client supprimer";
    }

    public Client updateClient(Client client){
        Client client1= clientRepository.findByIdClient(client.getIdClient());
        if(client1 == null)
            throw new NoContentException("Client non trouvé");
        return clientRepository.save(client);
    }

    public Client connexion(String email, String motdepasse){
        Client client1= clientRepository.findByEmailAndMotdepasse(email, motdepasse);
        if(client1 == null)
            throw new EntityNotFoundException("Ce client n'existe pas");
        return client1;
    }
}
