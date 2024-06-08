package evaMS.recargos_service.services;

import evaMS.recargos_service.entities.RecargoPorKilometrajeEntity;
import evaMS.recargos_service.repositories.RecargoPorKilometrajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecargoPorKilometrajeService {

    @Autowired
    RecargoPorKilometrajeRepository recargoPorKilometrajeRepository;

    public ArrayList<RecargoPorKilometrajeEntity> getRecargosPorKilometraje(){
        return (ArrayList<RecargoPorKilometrajeEntity>) recargoPorKilometrajeRepository.findAll();
    }

    public RecargoPorKilometrajeEntity saveRecargoPorKilometraje(RecargoPorKilometrajeEntity recargoPorKilometraje){
        return recargoPorKilometrajeRepository.save(recargoPorKilometraje);
    }


    public RecargoPorKilometrajeEntity updateRecargoPorKilometraje(RecargoPorKilometrajeEntity recargoPorKilometraje) {
        return recargoPorKilometrajeRepository.save(recargoPorKilometraje);
    }

    public boolean deleteRecargoPorKilometraje(Long id) throws Exception {
        try{
            recargoPorKilometrajeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    private String getKilometrajeInString(int kilometraje){
        if (kilometraje>=0 && kilometraje<=5000 ){
            return "0-5000";
        } else if (kilometraje >= 5001 && kilometraje <= 12000) {
            return "5001-12000";
        } else if (kilometraje >= 12001 && kilometraje <= 25000) {
            return "12001-25000";
        } else if (kilometraje >= 25001 && kilometraje <= 40000) {
            return "25001-40000";
        }
            return "40000-MAS";
    }

    public Integer getRecargoPorKilometrajeByTipoDeVehiculo(int kilometraje, String tipoDeVehiculo){
        Integer response;
        String kilmetrajeString= getKilometrajeInString(kilometraje);

        response=recargoPorKilometrajeRepository.getRecargoByKilmetrajeYTipoDeVehiculo(kilmetrajeString,tipoDeVehiculo);
        if (response==null){
            response=0;
        }
        return response;

    }



}
