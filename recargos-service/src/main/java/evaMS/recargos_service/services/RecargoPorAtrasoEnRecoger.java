package evaMS.recargos_service.services;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RecargoPorAtrasoEnRecoger {
    public int getRecargoPorAtrasoEnRecoger(Date fechaSalida, Date fechaRecogida){
        int porecentajeRecargo=5;
        long difEnMilisegundos = Math.abs(fechaRecogida.getTime() - fechaSalida.getTime());
        long dif = TimeUnit.DAYS.convert(difEnMilisegundos, TimeUnit.MILLISECONDS);

        int diasDeAtraso= (int) dif;
        return porecentajeRecargo*diasDeAtraso;
    }
}
