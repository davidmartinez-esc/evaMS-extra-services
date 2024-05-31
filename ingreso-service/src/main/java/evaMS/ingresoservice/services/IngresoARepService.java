package evaMS.ingresoservice.services;


import evaMS.ingresoservice.clients.DescuentoAplicadoFeignClient;
import evaMS.ingresoservice.clients.PrecioPorRepFeignClient;
import evaMS.ingresoservice.clients.RepEspecificaFeignClient;
import evaMS.ingresoservice.clients.VehiculoFeignClient;
import evaMS.ingresoservice.dto.RepEspecificaEntity;
import evaMS.ingresoservice.dto.VehiculoEntity;
import evaMS.ingresoservice.entities.IngresoARepEntity;
import evaMS.ingresoservice.repositories.IngresoARepRepository;
import evaMS.ingresoservice.request.NuevaRepAplicadaRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class IngresoARepService {

    //@Autowired
    //IngresoARepService repEspecificaRepository;


    @Autowired
    IngresoARepRepository ingresoARepRepository;





    public ArrayList<IngresoARepEntity> getReparaciones(){
        return (ArrayList<IngresoARepEntity>) ingresoARepRepository.findAll();
    }

    public IngresoARepEntity saveReparacion(IngresoARepEntity reparacion){
        return ingresoARepRepository.save(reparacion);
    }

    public IngresoARepEntity updateIngresoARep(IngresoARepEntity ingresoARep) {
        return ingresoARepRepository.save(ingresoARep);
    }

    public boolean deleteIngresoARep(Long id) throws Exception {
        try{
            ingresoARepRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public ArrayList<IngresoARepEntity> getReparacionesByIdVehiculo(int id){
        return (ArrayList<IngresoARepEntity>) ingresoARepRepository.findByIdVehiculo(id);
    }

    public IngresoARepEntity getIngresoARepById(Long id){
        return ingresoARepRepository.findById(id).get();
    }


    public Integer getNumeroDeReparacionesDeVehiculo(int idVehiculo, Date fechaRecogida){
        Calendar cal=Calendar.getInstance();
        cal.setTime(fechaRecogida);

        cal.add(Calendar.YEAR,-1);
        return ingresoARepRepository.getNumeroDeReparaciones(fechaRecogida,cal.getTime(),idVehiculo);
    }

}
