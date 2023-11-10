package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Achat;
import SoutenanceApp.EasyPark.Repositories.AchatRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchatService {
    @Autowired
    private final AchatRepository achatRepository;

    public AchatService(AchatRepository achatRepository1){
        this.achatRepository= achatRepository1;
    }

    public Achat create(Achat achat){
        Achat achat1  = achatRepository.findByIdAchat(achat.getIdAchat());
            if(achat1 != null)
                throw new EntityExistsException("Cet achat existe déja");
            return achatRepository.save(achat1);
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
            throw new NoContentException("Ce achat n'existe pas");
        return achatRepository.save(achat1);
    }

    public String delete(long idAchat){
        Achat achat = achatRepository.findByIdAchat(idAchat);
        if(achat == null)
            throw new NoContentException("Cet achat n'existe pas");
        return "Achat supprimer";
    }
}
