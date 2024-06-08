package evaMS.rdringresoservice.services;


import evaMS.rdringresoservice.entities.RepEspecificaEntity;
import evaMS.rdringresoservice.repositories.RepEspecificaRepository;
import evaMS.rdringresoservice.requests.BorrarReparacionEspecificaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepEspecificaService {
    @Autowired
    RepEspecificaRepository repEspecificaRepository;




    public ArrayList<RepEspecificaEntity> getAllReparacionesEspecificas(){
        return (ArrayList<RepEspecificaEntity>) repEspecificaRepository.findAll();
    }

    public RepEspecificaEntity saveTipoDeRep(RepEspecificaEntity reparacion){
        return repEspecificaRepository.save(reparacion);
    }


    public RepEspecificaEntity updateRepEspecifica(RepEspecificaEntity repEspecifica) {
        return repEspecificaRepository.save(repEspecifica);
    }

    public boolean deleteRepEspecifica(Long id) throws Exception {
        try{
            repEspecificaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public List<RepEspecificaEntity> getRepEspecificaByIdIngreso(int idIngreso){
        return repEspecificaRepository.findByIdIngresoARep(idIngreso);
    }

    public Integer getMontoTotalDelIngreso(int idIngreso){
        return repEspecificaRepository.getCostoDeLasReparacionesDeIngreso(idIngreso);
    }

    public String deleteRepEspecificaSegunNombreDeLaRep(BorrarReparacionEspecificaRequest request){
        repEspecificaRepository.
                deleteReparacionEspecificaByIdIngresoNombre(request.getIdIngresoARep(),request.getNombreReparacion());
        return "Se logró borrar con exito la reparacion";
    }



}

