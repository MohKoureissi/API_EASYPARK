package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Paiement;
import SoutenanceApp.EasyPark.Repositories.PaiementRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    @Autowired
    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository1){
        this.paiementRepository= paiementRepository1;
    }

    public Paiement create(Paiement paiement){
        Paiement paiement1= paiementRepository.findByIdPaiement(paiement.getIdPaiement());
        if(paiement1 != null)
            throw new EntityExistsException("Ce paiement existe deja");
        return paiementRepository.save(paiement1);
    }

    public List<Paiement> getAllPaiement(){
        List<Paiement> paiments= paiementRepository.findAll();
        if(paiments.isEmpty())
            throw new NoContentException("Aucun paiement trouvé");
        return paiments;
    }

    public Paiement getPaiementById(long idPaiement){
        Paiement paiement= paiementRepository.findByIdPaiement(idPaiement);
        if(paiement == null)
            throw new NoContentException("Ce Paiement n'existe pas");
        return paiement;
    }

    public Paiement updatePaiement(Paiement paiement){
        Paiement paiement1= paiementRepository.findByIdPaiement(paiement.getIdPaiement());
        if(paiement1 == null)
            throw new EntityExistsException("Paiement non trouvé");
        return paiementRepository.save(paiement1);
    }

    public String deletePaiement(long idPaiement){
        Paiement paiement= paiementRepository.findByIdPaiement(idPaiement);
        if(paiement == null)
            throw new EntityExistsException("Ce paiement n'existe pas");
        return "Paiement supprimé";
    }
}
