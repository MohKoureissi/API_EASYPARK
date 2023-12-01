package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Achat;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Repositories.VoitureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
public class VoitureService {
    @Autowired
    private final VoitureRepository voitureRepository;

    public VoitureService (VoitureRepository voitureRepository1){
        this.voitureRepository= voitureRepository1;
    }
    //Ajouter voiture avec ses 3 photos
        public Voiture saveVoitureWithPhotos(Voiture voiture,MultipartFile photo2, MultipartFile photo3, MultipartFile photo4) {
            try {

                savePhoto(photo2, voiture::setPhoto2);
                savePhoto(photo3, voiture::setPhoto3);
                savePhoto(photo4, voiture::setPhoto4);

                return voitureRepository.save(voiture);
            } catch (Exception e) {
                throw new EntityNotFoundException("Erreur lors de l'enregistrement de la voiture", e);
            }
        }

        private void savePhoto(MultipartFile photo, Consumer<String> setPhotoMethod) throws IOException {
            if (photo != null) {
                String location = "C:\\xampp\\htdocs\\easy_park";
                Path rootLocation = Paths.get(location);

                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                }

                Path filePath = rootLocation.resolve(photo.getOriginalFilename());

                if (!Files.exists(filePath)) {
                    Files.copy(photo.getInputStream(), filePath);
                    setPhotoMethod.accept("http://localhost/easy_park/" + photo.getOriginalFilename());
                } else {
                    Files.delete(filePath);
                    Files.copy(photo.getInputStream(), filePath);
                    setPhotoMethod.accept("http://localhost/easy_park/" + photo.getOriginalFilename());
                }
            }
        }
        // Fin methode Ajouter
    //Lister tout les voitures
    public List<Voiture> getAllVoiture(){
        List<Voiture> voiture1= voitureRepository.findAll();
        if(voiture1.isEmpty())
            throw new NoContentException("Aucune voiture trouvé");
        return voiture1;
    }
   

    //Liste des voitures ajouter par l'adminParking
    public List<Voiture> ListParAdminParking(long idAdminParking){
        if(!voitureRepository.findByAdminParkingIdAdminParking(idAdminParking).isEmpty()){
            return voitureRepository.findByAdminParkingIdAdminParking(idAdminParking);
        } else{
            throw new NoContentException("Aucune voiture ajouter par cet AdminParking");
        }
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
        return voitureRepository.save(voiture);
    }

    //Suppression
    public String deleteVoiture(Long idVoiture) throws EntityNotFoundException {
        // Recherche de la voiture par ID
        Voiture voitureToDelete = voitureRepository.findByIdVoiture(idVoiture);
               if(voitureToDelete == null) throw  new EntityNotFoundException("Voiture non trouvée avec l'ID : " + idVoiture);

        // Suppression des photos associées
        deletePhoto(voitureToDelete.getPhoto2());
        deletePhoto(voitureToDelete.getPhoto3());
        deletePhoto(voitureToDelete.getPhoto4());

        // Suppression de la voiture
        voitureRepository.deleteById(idVoiture);
        return "Voiture supprimer";

    }
    private void deletePhoto(String photoUrl) {
        if (photoUrl != null) {
            // Extrait le nom du fichier à partir de l'URL
            String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);

            // Supprime le fichier
            Path filePath = Paths.get("C:\\xampp\\htdocs\\easy_park", fileName);
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                // Gérer l'erreur de suppression de fichier, si nécessaire
                e.printStackTrace();
            }
        }
    }

    //Methode modifier voiture
    public Voiture updateVoiture(Long id, Voiture updatedVoiture, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4) throws EntityNotFoundException {
        // Recherche de la voiture par ID
        Voiture existingVoiture = voitureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Voiture non trouvée avec l'ID : " + id));

        // Mise à jour des informations de base de la voiture
        existingVoiture.setMarque(updatedVoiture.getMarque());
        existingVoiture.setModele(updatedVoiture.getModele());
        // ... Mise à jour d'autres propriétés

        // Mise à jour des photos (suppression des anciennes et ajout des nouvelles)
        deletePhoto(existingVoiture.getPhoto2());
        deletePhoto(existingVoiture.getPhoto3());
        deletePhoto(existingVoiture.getPhoto4());

        existingVoiture.setPhoto2(savePhoto(photo2));
        existingVoiture.setPhoto3(savePhoto(photo3));
        existingVoiture.setPhoto4(savePhoto(photo4));
        // Enregistrement de la voiture mise à jour dans la base de données
        return voitureRepository.save(existingVoiture);
    }

    private String savePhoto(MultipartFile photo) {
        if (photo != null) {
            String location = "C:\\xampp\\htdocs\\easy_park";
            Path rootLocation = Paths.get(location);

            try {
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                }

                Path filePath = rootLocation.resolve(photo.getOriginalFilename());

                if (!Files.exists(filePath)) {
                    Files.copy(photo.getInputStream(), filePath);
                    return "http://localhost:8080/easy_park/" + photo.getOriginalFilename();
                } else {
                    Files.delete(filePath);
                    Files.copy(photo.getInputStream(), filePath);
                    return "http://localhost:8080/easy_park/" + photo.getOriginalFilename();
                }
            } catch (IOException e) {
                // Gérer l'erreur de traitement de fichier, si nécessaire
                e.printStackTrace();
            }
        }

        return null;
    }


   

}
