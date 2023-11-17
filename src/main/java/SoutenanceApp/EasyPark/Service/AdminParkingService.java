package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.AdminParking;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Repositories.RepoAdminParking;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

@Service
public class AdminParkingService {
    @Autowired
    private final RepoAdminParking repoAdminParking;

    public AdminParkingService(RepoAdminParking repoAdminParking1){
        this.repoAdminParking= repoAdminParking1;
    }

    public String changeAccess(long idAdminParking){
        AdminParking adminParking = repoAdminParking.findByIdAdminParking(idAdminParking);
        if(adminParking == null)
            throw new NoContentException("invalid");
        adminParking.setAcces(!adminParking.getAcces());
        repoAdminParking.save(adminParking);
        return "succes";
    }
    //
     public AdminParking saveAdminWithPhotos(AdminParking adminParking,MultipartFile agrement) {
            try {

                savePhoto(agrement, adminParking::setAgrementParking);
                 adminParking.setAcces(false);
                return repoAdminParking.save(adminParking);
            } catch (Exception e) {
                throw new EntityNotFoundException("Erreur lors de l'enregistrement de l'admin", e);
            }
        }
          private void savePhoto(MultipartFile agrement, Consumer<String> setAgrementMethod) throws IOException {
            if (agrement != null) {
                String location = "C:\\xampp\\htdocs\\easy_park";
                Path rootLocation = Paths.get(location);

                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                }

                Path filePath = rootLocation.resolve(agrement.getOriginalFilename());

                if (!Files.exists(filePath)) {
                    Files.copy(agrement.getInputStream(), filePath);
                    setAgrementMethod.accept("http://localhost/easy_park/" + agrement.getOriginalFilename());
                } else {
                    Files.delete(filePath);
                    Files.copy(agrement.getInputStream(), filePath);
                    setAgrementMethod.accept("http://localhost/easy_park/" + agrement.getOriginalFilename());
                }
            }
           
        }


   //Fin de la méthode creer admin Parking avec sa photo
    public List<AdminParking> getAllAdmin(){
        List<AdminParking> adminParkings= repoAdminParking.findAll();
        if(adminParkings.isEmpty())
             throw new NoContentException("Aucun administrateur trouvé");
        return adminParkings;
    }

    public AdminParking getAdminById(Long idAdminParking){
      AdminParking adminParking = repoAdminParking.findByIdAdminParking(idAdminParking);
      if(adminParking == null)
          throw new NoContentException("Cet Administrateur n'existe pas");
      return adminParking;
    }

    public AdminParking editAdmin(AdminParking adminParking){
      AdminParking adminParking1= repoAdminParking.findByIdAdminParking(adminParking.getIdAdminParking());
      if(adminParking1 == null)
          throw new NoContentException("Admin non trouvé");
                  return repoAdminParking.save(adminParking1);
    }

    public String deleteAdminById(long idAdminParking){
         AdminParking adminParking1 = repoAdminParking.findByIdAdminParking(idAdminParking);
         if(adminParking1 == null)
             throw new NoContentException("Cet Admin n'existe pas!");
            repoAdminParking.delete(adminParking1);
            return "Admin supprimé";
    }


    public AdminParking connexionAdmin(String email, String motdepasse){
        AdminParking adminParking = repoAdminParking.findByMotdepasseAndEmail(motdepasse, email);
        if(adminParking == null)
            throw new NoContentException("Connexion échoué!");
         return adminParking;
    }

}

