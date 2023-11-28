package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Achat;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Repositories.AchatRepository;
import SoutenanceApp.EasyPark.Repositories.VoitureRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchatService {
    @Autowired
    private final AchatRepository achatRepository;

    @Autowired
    private  VoitureRepository voitureRepository;

    public void VoitureService (VoitureRepository voitureRepository1){
        this.voitureRepository= voitureRepository1;
    }

    /**
     * @param achatRepository1
     */
    public AchatService(AchatRepository achatRepository1){
      //  this.achatRepository1 = achatRepository1;
        this.achatRepository= achatRepository1;
    }

    

    public Achat create(Achat achat){
        Achat achat1  = achatRepository.findByIdAchat(achat.getIdAchat());
            if(achat1 != null)
                throw new EntityNotFoundException("Cet achat existe déja");
            return achatRepository.save(achat);
    }

    public List<Achat> getAllAchat(){
        List<Achat> achat= achatRepository.findAll();
        if(achat.isEmpty())
            throw new NoContentException("Aucun Achat trouvé");
        return achat;
    }

    public Achat getAchatById(long idAchat){
        Achat achat = achatRepository.findByIdAchat(idAchat);
        if(achat ==  null)
            throw new NoContentException("Cet achat n'existe pas");
        return achat;
    }

    public Achat updateAchat(Achat achat){
        Achat achat1= achatRepository.findByIdAchat(achat.getIdAchat());
        if(achat1 == null)
            throw new NoContentException("Cet achat n'existe pas");
        return achatRepository.save(achat);
    }

    public String delete(long idAchat){
        Achat achat = achatRepository.findByIdAchat(idAchat);
        if(achat == null)
            throw new NoContentException("Cet achat n'existe pas");
        achatRepository.delete(achat);
        return "Achat supprimer";
    }

      @Transactional
    public void decrementeQuantiteVoiture(Achat achat) {
        Voiture voiture = achat.getVoiture();
        if (voiture != null && voiture.getQuantite() > 0) {
            voiture.setQuantite(voiture.getQuantite() - 1);
            voitureRepository.save(voiture);
        }
    }
}
