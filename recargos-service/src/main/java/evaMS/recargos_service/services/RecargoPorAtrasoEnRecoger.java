package evaMS.recargos_service.services;

import evaMS.recargos_service.requests.RecargoPorAtrasoEnRecogerRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RecargoPorAtrasoEnRecoger {
    public Integer getRecargoPorAtrasoEnRecoger(RecargoPorAtrasoEnRecogerRequest request){
        int porecentajeRecargo=5;
        Date fechaRecogida=request.getFechaRecogida();
        Date fechaSalida=request.getFechaSalida();


        long difEnMilisegundos = Math.abs(fechaRecogida.getTime() - fechaSalida.getTime());
        long dif = TimeUnit.DAYS.convert(difEnMilisegundos, TimeUnit.MILLISECONDS);

        int diasDeAtraso= (int) dif;
        Integer resultado=porecentajeRecargo*diasDeAtraso;
        return resultado;
    }
}
