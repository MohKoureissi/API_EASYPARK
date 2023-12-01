package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Location;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Repositories.LocationRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository1){
        this.locationRepository= locationRepository1;
    }

    public Location create(Location location){
        Location location1= locationRepository.findByIdLocation(location.getIdLocation());
        if(location1 != null)
            throw new EntityNotFoundException("Cette location existe déja");
        return locationRepository.save(location);
    }

    //Liste des voitures ajouter par l'adminParking
    // public List<Location> ListParAdminParking(long idAdminParking){
    //     if(!locationRepository.findByAdminParkingIdAdminParking(idAdminParking).isEmpty()){
    //         return locationRepository.findByAdminParkingIdAdminParking(idAdminParking);
    //     } else{
    //         throw new NoContentException("Aucune voiture ajouter par cet AdminParking");
    //     }
    // }

    public List<Location> getAllLocation(){
        List<Location> locations= locationRepository.findAll();
        if(locations.isEmpty())
            throw new NoContentException("Aucune Location trouvé");
        return locations;
    }

    public Location getLocationById(long idLocation){
        Location location= locationRepository.findByIdLocation(idLocation);
        if(location == null)
            throw new EntityExistsException("Cette location n'existe pas");
        return location;
    }

    public Location updateLocation(Location location){
        Location location1= locationRepository.findByIdLocation(location.getIdLocation());
        if(location1 == null)
            throw new NoContentException("Cette location n'existe pas");
        return locationRepository.save(location);
    }

    public String deleteLocation(long idLocation){
        Location location= locationRepository.findByIdLocation(idLocation);
        if(location == null)
            throw new NoContentException("Cette location n'existe pas");
        locationRepository.delete(location);
            return "Location supprimer";
    }
}
