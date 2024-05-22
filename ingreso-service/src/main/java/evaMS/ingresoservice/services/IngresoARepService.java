package evaMS.ingresoservice.services;


import evaMS.ingresoservice.clients.PrecioPorRepFeignClient;
import evaMS.ingresoservice.entities.IngresoARepEntity;
import evaMS.ingresoservice.repositories.IngresoARepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IngresoARepService {

    //@Autowired
    //IngresoARepService repEspecificaRepository;
    @Autowired
    PrecioPorRepFeignClient precioPorRepFeignClient;

    @Autowired
    IngresoARepRepository ingresoARepRepository;

    public ArrayList<IngresoARepEntity> getReparaciones(){
        return (ArrayList<IngresoARepEntity>) ingresoARepRepository.findAll();
    }

    public IngresoARepEntity saveReparacion(IngresoARepEntity reparacion){
        return ingresoARepRepository.save(reparacion);
    }



  /*
    @Transactional
    public IngresoARepEntity saveIngresoARep(FormRegistroIngresoRep ingresoForm){
        IngresoARepEntity ingreso= new IngresoARepEntity();

        ingreso.setIdVehiculo(ingresoForm.getIdVehiculo());
        ingreso.setCostoTotal(ingresoForm.getCostoTotal());

        ingreso.setFechaIngreso(ingresoForm.getFechaIngreso());
        ingreso.setHoraIngreso(ingresoForm.getHoraIngreso());

        ingreso.setFechaSalida(ingresoForm.getFechaSalida());
        ingreso.setHoraSalida(ingresoForm.getHoraSalida());

        ingreso.setFechaRecogida(ingresoForm.getFechaRecogida());
        ingreso.setHoraRecogida(ingresoForm.getHoraRecogida());

        List<RepEspecificaEntity> reparacionesIndividuales=ingresoForm.getReparacionesAsociadas();
        int n=reparacionesIndividuales.size();

        RepEspecificaEntity reparacion;

        if (!reparacionesIndividuales.isEmpty()){
            System.out.println("NO ESTA VACIA");
            for (int i = 0; i < n; i++) {
                reparacion=reparacionesIndividuales.get(i);
                repEspecificaRepository.save(reparacion);
            }
        }

        return ingresoARepRepository.save(ingreso);
    }
    */


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


}
