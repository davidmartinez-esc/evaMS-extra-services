package evaMS.ingresoservice.services;

import evaMS.ingresoservice.clients.*;
import evaMS.ingresoservice.dto.RepEspecificaEntity;
import evaMS.ingresoservice.dto.VehiculoEntity;
import evaMS.ingresoservice.entities.IngresoARepEntity;
import evaMS.ingresoservice.request.CalcularCostoFinalRequest;
import evaMS.ingresoservice.request.NuevaRepAplicadaRequest;
import evaMS.ingresoservice.request.RecargoPorAtrasoEnRecogerRequest;
import evaMS.ingresoservice.request.RequestDescuentoDiaHoraIngreso;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class GestionIngresoService {
    @Autowired
    VehiculoFeignClient vehiculoFeignClient;

    @Autowired
    RepEspecificaFeignClient repEspecificaFeignClient;

    @Autowired
    BonoAplicadoFeignClient bonoAplicadoFeignClient;

    @Autowired
    PrecioPorRepFeignClient precioPorRepFeignClient;

    @Autowired
    IngresoARepService ingresoARepService;

    @Autowired
    DescuentosFeignClient descuentosFeignClient;

    @Autowired
    RecargosFeignClient recargosFeignClient;


    float IVA=19;

    public Integer asignarNuevaRepEspecificaAIngreso(NuevaRepAplicadaRequest request){
        RepEspecificaEntity repPorAsignar=new RepEspecificaEntity();
        IngresoARepEntity ingreso=ingresoARepService.getIngresoARepById((long)request.getIdIngreso());
        VehiculoEntity vehiculo=vehiculoFeignClient.getVehiculoById((long)ingreso.getIdVehiculo());

        Integer precioReparacion= precioPorRepFeignClient.getPrecioByRepYTipoDeMotor(request.getTipoDeReparacion(),vehiculo.getTipoMotor());

        repPorAsignar.setIdIngresoARep(request.getIdIngreso());
        repPorAsignar.setNombreDeLaRep(request.getTipoDeReparacion());
        repPorAsignar.setPrecioDeLaReparacion(precioReparacion);

        repEspecificaFeignClient.saveRepEspecifica(repPorAsignar);

        return precioReparacion;
    }

    @Transactional
    public IngresoARepEntity saveIngresoARep(IngresoARepEntity ingreso){

        VehiculoEntity vehiculo=vehiculoFeignClient.getVehiculoById((long) ingreso.getIdVehiculo());


        return ingresoARepService.saveReparacion(ingreso);
    }
    /*
    private void checkearPropiedadesNotNull(IngresoARepEntity ingreso) throws Exception {
        if (ingreso.getFechaIngreso().equals(null) || ingreso.getHoraIngreso().equals(null) ||
                ingreso.getFechaSalida().equals(null) || ingreso.getHoraSalida().equals(null) ||
                ingreso.getFechaRecogida().equals(null) || ingreso.getHoraRecogida().equals(null)){

            throw new Exception("Falta un campo por ingresar");
        }
    }
    */

    @Transactional
    public IngresoARepEntity calcularCostosPreciosFinales(CalcularCostoFinalRequest calculoFinalRequest) throws Exception {
       // checkearPropiedadesNotNull(ingreso);
        IngresoARepEntity ingreso=calculoFinalRequest.getIngreso();

        int montoReparaciones=repEspecificaFeignClient.getMontoTotalDelIngreso(Math.toIntExact(ingreso.getId()));
        int montoDescuentos=0;
        int montoRecargos=0;
        int montoIVA=0;
        int costoTotal=0;

        VehiculoEntity vehiculo=vehiculoFeignClient.getVehiculoById((long)ingreso.getIdVehiculo());
        String tipoDeVehiculo=vehiculo.getTipo();
        int numeroDeReps= ingresoARepService.
                getNumeroDeReparacionesDeVehiculo(ingreso.getIdVehiculo(),ingreso.getFechaRecogida());

        float descuentoPorNreps=0;
        float descuentoPorIngresoLunesJueves=0;
        int montoDescuentoPorBono=0;

        float recargoPorAntiguedad=0;
        float recargoPorKilometraje=0;
        float recargoPorAtrasoEnRecoger=0;


        if (calculoFinalRequest.getUsaBono()){
            montoDescuentoPorBono=getMontoBonoSiEsPosible(ingreso.getFechaRecogida(),vehiculo.getMarca());
        }

        //DESCUENTOS
        descuentoPorNreps=descuentosFeignClient.getDescuentoPorNReps(numeroDeReps,tipoDeVehiculo);
        descuentoPorNreps=descuentoPorNreps/100;


        RequestDescuentoDiaHoraIngreso request=new RequestDescuentoDiaHoraIngreso(ingreso.getHoraIngreso(),ingreso.getFechaIngreso());
        descuentoPorIngresoLunesJueves=descuentosFeignClient.getDescuentoPorDiaHoraIngreso(request);
        descuentoPorIngresoLunesJueves=descuentoPorIngresoLunesJueves/100;

        //RECARGOS

        recargoPorAntiguedad= recargosFeignClient.getRecargoPorAntiguedadYtipo(vehiculo.getAnio_Fabricacion(),tipoDeVehiculo);
        recargoPorAntiguedad= recargoPorAntiguedad/100;

        recargoPorKilometraje=recargosFeignClient.getRecargoPorKilometraje(ingreso.getKilometrajeAlIngreso(),tipoDeVehiculo);
        recargoPorKilometraje=recargoPorKilometraje/100;

        RecargoPorAtrasoEnRecogerRequest requestRecargo=new RecargoPorAtrasoEnRecogerRequest(ingreso.getFechaSalida(),ingreso.getFechaRecogida());
        recargoPorAtrasoEnRecoger=recargosFeignClient.getRecargoPorAtrasoEnRecoger(requestRecargo);
        recargoPorAtrasoEnRecoger=recargoPorAtrasoEnRecoger/100;

        //CALCULOS
        montoDescuentos= (int) (montoReparaciones*descuentoPorNreps + montoReparaciones*descuentoPorIngresoLunesJueves + montoDescuentoPorBono);

        montoRecargos=(int)(montoReparaciones*recargoPorAntiguedad + montoReparaciones*recargoPorKilometraje +
                montoReparaciones*recargoPorAtrasoEnRecoger);


        montoIVA=(int)(montoReparaciones*(IVA/100)); //CAMBIAR ESTO PARA QUE APAREZCA EN UNA TABLA DE UNA BASE DE DATOS

        costoTotal= (int) (montoReparaciones-montoDescuentos+montoRecargos + (montoReparaciones*IVA/100));

        //SETEO AL INGRESO

        ingreso.setCostoTotal(costoTotal);
        ingreso.setMontoTotalReparaciones(montoReparaciones);
        ingreso.setMontoRecargos(montoRecargos);
        ingreso.setMontoDescuentos(montoDescuentos);
        ingreso.setMontoIVA(montoIVA);

        ingresoARepService.updateIngresoARep(ingreso);

       return ingreso;
    }

    private Integer getMontoBonoSiEsPosible(Date fecha, String marca) throws Exception {
        int mes=0;
        int anio=0;
        Integer montoDelBono;
        Integer bonosAplicadosEsteMes;
        Integer bonosDisponibles;

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecha);

        anio=calendar.get(Calendar.YEAR);
        mes=calendar.get(Calendar.MONTH)+1;

         bonosAplicadosEsteMes=bonoAplicadoFeignClient.getNumeroDeBonosAplicadosPorMesMarca(mes,anio,marca);
         bonosDisponibles=descuentosFeignClient.getCantidadBonos(marca);

        if (bonosAplicadosEsteMes>bonosDisponibles){
            throw new Exception("No se puede asignar un bono a este vehiculo, intentelo denuevo");
        }
       montoDelBono=descuentosFeignClient.getMontoBono(marca);
        if (montoDelBono==null){
            montoDelBono=0;
        }
        return montoDelBono;
    }

}
