package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.SuperAdmin;
import SoutenanceApp.EasyPark.Repositories.SuperAdminRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminService {
    @Autowired
    SuperAdminRepository superAdminRepository;

    public SuperAdminService(SuperAdminRepository superAdminRepository1){
        this.superAdminRepository= superAdminRepository1;
    }
    public SuperAdmin create(SuperAdmin superAdmin){
        SuperAdmin superAdmin1= superAdminRepository.findByEmail(superAdmin.getEmail());
        if(superAdmin1 != null)
            throw new EntityNotFoundException("Ce SuperAdmin existe déja");
        return superAdminRepository.save(superAdmin);
    }

    public List<SuperAdmin> read(){
        List<SuperAdmin> superAdmin= superAdminRepository.findAll();
        if(superAdmin.isEmpty())
            throw new NoContentException("Aucun Super Admin Trouvé");
        return superAdmin;
    }

    public  SuperAdmin update(SuperAdmin superAdmin){
        SuperAdmin superAdmin1= superAdminRepository.findByIdSuperAdmin(superAdmin.getIdSuperAdmin());
        if(superAdmin1 == null)
            throw new EntityNotFoundException("Ce SuperAdmin n' existe déja");
        return superAdminRepository.save(superAdmin);
    }

    public SuperAdmin connexion(String email, String motdepasse){
        SuperAdmin superAdmin= superAdminRepository.findByMotdepasseAndEmail(email, motdepasse);
        if(superAdmin == null)
            throw new EntityExistsException("Connexion échoué");
        return superAdmin;
    }
}
