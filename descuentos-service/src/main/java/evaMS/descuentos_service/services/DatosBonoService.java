package evaMS.descuentos_service.services;

import evaMS.descuentos_service.repositories.DatosBonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosBonoService {
    @Autowired
    DatosBonoRepository datosBonoRepository;
    public Integer getMontoBono(String marca){
        Integer response;
        response= datosBonoRepository.getMontoDelBono(marca);
        if (response==null){
            response=0;
        }
        return response;
    }

    public Integer getCantidadDeBonos(String marca){
        Integer response;
        response= datosBonoRepository.getCantidadDeBonosDisponiblesXMes(marca);
        if (response==null){
            response=0;
        }
        return response;
    }
}
