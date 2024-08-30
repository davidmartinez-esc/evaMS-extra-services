package evaMS.ingresoservice.utlity;

import evaMS.ingresoservice.clients.DescuentosFeignClient;
import evaMS.ingresoservice.dto.VehiculoEntity;
import evaMS.ingresoservice.request.RequestDescuentoDiaHoraIngreso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class DescuentosAplicables {
  private final DescuentosFeignClient descuentoClient;
  private float descuentoPorNreps;
  private float descuentoPorIngresoLunesJueves;
  private int montoDescuentoPorBono;

 public DescuentosAplicables(DescuentosFeignClient descuentoClient){
   this.descuentoClient=descuentoClient;
 }

 public void setTodosLosDescuentos(VehiculoEntity vehiculo,RequestDescuentoDiaHoraIngreso request,int numeroDeReps){

 }

 public void setDescuentoDiaIngreso(Date fechaIngreso, LocalTime horaIngreso){
     RequestDescuentoDiaHoraIngreso request=new RequestDescuentoDiaHoraIngreso(horaIngreso,fechaIngreso);
   descuentoPorIngresoLunesJueves=descuentoClient.getDescuentoPorDiaHoraIngreso(request);
   descuentoPorIngresoLunesJueves=descuentoPorIngresoLunesJueves/100;
 }

 public void setDescuentoPorNreps(int numeroDeReps,String tipoDeVehiculo){
   descuentoPorNreps=descuentoClient.getDescuentoPorNReps(numeroDeReps,tipoDeVehiculo);
   descuentoPorNreps=descuentoPorNreps/100;
 }
}
