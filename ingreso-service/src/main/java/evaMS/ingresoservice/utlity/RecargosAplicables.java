package evaMS.ingresoservice.utlity;

import evaMS.ingresoservice.clients.DescuentosFeignClient;
import evaMS.ingresoservice.clients.RecargosFeignClient;
import evaMS.ingresoservice.request.RecargoPorAtrasoEnRecogerRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
public class RecargosAplicables {
private float recargoPorAntiguedad;
private float recargoPorKilometraje;
private float recargoPorAtrasoEnRecoger;
private final RecargosFeignClient recargosClient;

    public RecargosAplicables(RecargosFeignClient recargosClient) {
        this.recargosClient = recargosClient;
    }

    public void setRecargoPorAntiguedad(int anioFabricacion,String tipoDeVehiculo){
        recargoPorAntiguedad= recargosClient.getRecargoPorAntiguedadYtipo(anioFabricacion,tipoDeVehiculo);
        recargoPorAntiguedad= recargoPorAntiguedad/100;
    }

    public void setRecargoPorKilometraje(int kilometraje, String tipoDeVehiculo){
        recargoPorKilometraje=recargosClient.getRecargoPorKilometraje(kilometraje,tipoDeVehiculo);
        recargoPorKilometraje=recargoPorKilometraje/100;

    }

    public void setRecargoPorAtrasoEnRecoger(Date fechaSalida, Date fechaRecogida){
        RecargoPorAtrasoEnRecogerRequest requestRecargo=new RecargoPorAtrasoEnRecogerRequest(fechaSalida,fechaRecogida);
        recargoPorAtrasoEnRecoger=recargosClient.getRecargoPorAtrasoEnRecoger(requestRecargo);
        recargoPorAtrasoEnRecoger=recargoPorAtrasoEnRecoger/100;
    }





}
