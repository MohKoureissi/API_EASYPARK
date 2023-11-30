package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Maintenance;
import SoutenanceApp.EasyPark.Modele.Voiture;
import SoutenanceApp.EasyPark.Repositories.MaintenanceRepositoy;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {
    @Autowired
    private final MaintenanceRepositoy maintenanceRepositoy;

    public MaintenanceService(MaintenanceRepositoy maintenanceRepositoy1){
        this.maintenanceRepositoy= maintenanceRepositoy1;
    }

    public Maintenance create(Maintenance maintenance){
        Maintenance maintenance1= maintenanceRepositoy.findByIdMaintenance(maintenance.getIdMaintenance());
        if(maintenance1 != null)
            throw new EntityNotFoundException("Cette maintenance existe déja");
        return maintenanceRepositoy.save(maintenance);
    }

    public List<Maintenance> getAll(){
        List<Maintenance> maintenances= maintenanceRepositoy.findAll();
        if(maintenances.isEmpty())
            throw new NoContentException("Aucune maintenance trouver");
        return maintenances;
    }
    //Liste des Maintenances effectuer par l'adminParking
    public List<Maintenance> ListParAdminParking(long idAdminParking){
        if(!maintenanceRepositoy.findByAdminParkingIdAdminParking(idAdminParking).isEmpty()){
            return maintenanceRepositoy.findByAdminParkingIdAdminParking(idAdminParking);
        } else{
            throw new NoContentException("Aucune maintenance effectuer par cet AdminParking");
        }
    }

    public Maintenance getById(long idMaintenance){
        Maintenance maintenance= maintenanceRepositoy.findByIdMaintenance(idMaintenance);
        if(maintenance == null)
            throw new NoContentException("Cette maintenance n'existe pas");
        return maintenance;
    }

    public Maintenance updateMaintenance(Maintenance maintenance){
        Maintenance maintenance1= maintenanceRepositoy.findByIdMaintenance(maintenance.getIdMaintenance());
        if(maintenance1 == null)
            throw new EntityNotFoundException("Cette maintenance n'existe pas");
        return maintenanceRepositoy.save(maintenance);
    }

    public String deleteMaintenance(long idMaintenance){
        Maintenance maintenance = maintenanceRepositoy.findByIdMaintenance(idMaintenance);
        if(maintenance == null)
            throw new NoContentException("Cette maintenance n'existe pas");
        maintenanceRepositoy.delete(maintenance);
        return "Maintenance Supprimer";
    }
}
