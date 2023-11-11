package SoutenanceApp.EasyPark.Service;

import SoutenanceApp.EasyPark.Exception.NoContentException;
import SoutenanceApp.EasyPark.Modele.Maintenance;
import SoutenanceApp.EasyPark.Repositories.MaintenanceRepositoy;
import jakarta.persistence.EntityExistsException;
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
            throw new EntityExistsException("Cette maintenance existe déja");
        return maintenanceRepositoy.save(maintenance1);
    }

    public List<Maintenance> getAll(){
        List<Maintenance> maintenances= maintenanceRepositoy.findAll();
        if(maintenances.isEmpty())
            throw new NoContentException("Aucune maintenance trouver");
        return maintenances;
    }

    public Maintenance getById(long idMaintenance){
        Maintenance maintenance= maintenanceRepositoy.findByIdMaintenance(idMaintenance);
        if(maintenance == null)
            throw new NoContentException("Cette maintenance n'existe pas");
        return maintenance;
    }

    public Maintenance updateMaintenance(Maintenance maintenance){
        Maintenance maintenance1= maintenanceRepositoy.findByIdMaintenance(maintenance.getIdMaintenance());
        if(maintenance1 != null)
            throw new EntityExistsException("Cette maintenance existe déja");
        return maintenanceRepositoy.save(maintenance1);
    }

    public String deleteMaintenance(long idMaintenance){
        Maintenance maintenance = maintenanceRepositoy.findByIdMaintenance(idMaintenance);
        if(maintenance == null)
            throw new NoContentException("Cette maintenance n'existe pas");
        return "Maintenance Supprimer";
    }
}
