package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.AdminParking;
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
        return "secces";
    }
      public AdminParking createAdminParking(AdminParking adminParking, MultipartFile multipartFile) throws Exception {
        if(repoAdminParking.findByEmail(adminParking.getEmail())== null){
            if(multipartFile != null){
                String location = "C:\\xampp\\htdocs\\easy_park";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                        adminParking.setPhoto("http://localhost:8080/easy_park/"+multipartFile.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                adminParking.setPhoto("http://localhost:8080/easy_park/"+multipartFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                adminParking.setPhoto("http://localhost:8080/easy_park/"+multipartFile.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }
                return repoAdminParking.save(adminParking);
        }  else {
           throw new EntityExistsException("Cet Administrateur existe déja");
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

