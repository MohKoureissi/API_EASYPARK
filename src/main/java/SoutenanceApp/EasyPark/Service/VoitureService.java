package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Repositories.VoitureRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VoitureService {
    @Autowired
    private final VoitureRepository voitureRepository;

    public VoitureService (VoitureRepository voitureRepository1){
        this.voitureRepository= voitureRepository1;
    }
    public Voiture addVoiture(Voiture voiture, MultipartFile photo1,MultipartFile photo2,MultipartFile photo3,MultipartFile photo4) throws Exception{
        if(voitureRepository.findByIdVoiture(voiture.getIdVoiture())== null){
            if(photo1 != null){
                String location = "C:\\xampp\\htdocs\\easy_park";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(photo1.getInputStream(),rootlocation.resolve(photo1.getOriginalFilename()));
                        voiture.setPhoto1("http://localhost:8080/easy_park/"+photo1.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+photo1.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(photo1.getInputStream(),rootlocation.resolve(photo1.getOriginalFilename()));
                                voiture.setPhoto1("http://localhost:8080/easy_park/"+photo1.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(photo1.getInputStream(),rootlocation.resolve(photo1.getOriginalFilename()));
                                voiture.setPhoto1("http://localhost:8080/easy_park/"+photo1.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }
           // Photo 2
            if(photo2 != null){
                String location = "C:\\xampp\\htdocs\\easy_park";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(photo2.getInputStream(),rootlocation.resolve(photo2.getOriginalFilename()));
                        voiture.setPhoto2("http://localhost:8080/easy_park/"+photo2.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+photo2.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(photo2.getInputStream(),rootlocation.resolve(photo2.getOriginalFilename()));
                                voiture.setPhoto2("http://localhost:8080/easy_park/"+photo2.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(photo2.getInputStream(),rootlocation.resolve(photo2.getOriginalFilename()));
                                voiture.setPhoto2("http://localhost:8080/easy_park/"+photo2.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }
            //Photo 3
            if(photo3 != null){
                String location = "C:\\xampp\\htdocs\\easy_park";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(photo3.getInputStream(),rootlocation.resolve(photo3.getOriginalFilename()));
                        voiture.setPhoto3("http://localhost:8080/easy_park/"+photo3.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+photo3.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(photo3.getInputStream(),rootlocation.resolve(photo3.getOriginalFilename()));
                                voiture.setPhoto3("http://localhost:8080/easy_park/"+photo3.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(photo3.getInputStream(),rootlocation.resolve(photo3.getOriginalFilename()));
                                voiture.setPhoto3("http://localhost:8080/easy_park/"+photo3.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }
            //Photo4
            if(photo4 != null){
                String location = "C:\\xampp\\htdocs\\easy_park";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(photo4.getInputStream(),rootlocation.resolve(photo4.getOriginalFilename()));
                        voiture.setPhoto4("http://localhost:8080/easy_park/"+photo4.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+photo4.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(photo4.getInputStream(),rootlocation.resolve(photo4.getOriginalFilename()));
                                voiture.setPhoto4("http://localhost:8080/easy_park/"+photo4.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(photo4.getInputStream(),rootlocation.resolve(photo4.getOriginalFilename()));
                                voiture.setPhoto4("http://localhost:8080/easy_park/"+photo4.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }

            return voitureRepository.save(voiture);
        } else {
            throw new EntityExistsException("Cette voiture existe deja");
        }
    }  // Fin methode Ajouter
    //Lister tout les voitures
    public List<Voiture> getAllVoiture(){
        List<Voiture> voiture1= voitureRepository.findAll();
        if(voiture1.isEmpty())
            throw new NoContentException("Aucune voiture trouvé");
        return voiture1;
    }

    //Lister une voiture spécifique
    public Voiture getVoitureById(long idVoiture){
        Voiture voiture= voitureRepository.findByIdVoiture(idVoiture);
        if(voiture == null)
            throw new NoContentException("Cette Voiture n'existe pas");
        return voiture;
    }

    //Edit
    public Voiture editVoiture(Voiture voiture){
        Voiture voiture1= voitureRepository.findByIdVoiture(voiture.getIdVoiture());
        if(voiture1 == null)
            throw new NoContentException("voiture non trouvé");
        return voitureRepository.save(voiture1);
    }

    //Suppression
    public String deleteVoiture(long idVoiture){
        Voiture voiture= voitureRepository.findByIdVoiture(idVoiture);
        if(voiture == null)
            throw new NoContentException("Voiture non trouvé");
        return "Voiture supprimé";
    }
}
