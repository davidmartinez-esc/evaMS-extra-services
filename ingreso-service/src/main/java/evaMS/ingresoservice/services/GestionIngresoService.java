package evaMS.ingresoservice.services;

import evaMS.ingresoservice.clients.*;
import evaMS.ingresoservice.dto.ReporteReparacionCompleta;
import evaMS.ingresoservice.dto.VehiculoEntity;
import evaMS.ingresoservice.entities.IngresoARepEntity;
import evaMS.ingresoservice.request.CalcularCostoFinalRequest;
import evaMS.ingresoservice.request.NuevoBonoAplicadoRequest;
import evaMS.ingresoservice.request.RecargoPorAtrasoEnRecogerRequest;
import evaMS.ingresoservice.request.RequestDescuentoDiaHoraIngreso;
import evaMS.ingresoservice.utlity.CalculadoraPrecioFinal;
import evaMS.ingresoservice.utlity.DescuentosAplicables;
import evaMS.ingresoservice.utlity.MontosCheckout;
import evaMS.ingresoservice.utlity.RecargosAplicables;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GestionIngresoService {
    @Autowired
    VehiculoFeignClient vehiculoFeignClient;

    @Autowired
    RepEspecificaFeignClient repEspecificaFeignClient;

    @Autowired
    BonoAplicadoFeignClient bonoAplicadoFeignClient;

    @Autowired
    IngresoARepService ingresoARepService;

    @Autowired
    DescuentosFeignClient descuentosFeignClient;

    @Autowired
    RecargosFeignClient recargosFeignClient;

    float IVA=19;
    @Transactional
    public void calculoCheckoutActualizacion(CalcularCostoFinalRequest calculoFinalRequest) throws Exception {
       // checkearPropiedadesNotNull(ingreso);
        IngresoARepEntity ingreso=calculoFinalRequest.getIngreso();
        int montoReparaciones=repEspecificaFeignClient.getMontoTotalDelIngreso(Math.toIntExact(ingreso.getId()));
        VehiculoEntity vehiculo=vehiculoFeignClient.getVehiculoById((long)ingreso.getIdVehiculo());
        int numeroDeReps= ingresoARepService.getNumeroDeReparacionesDeVehiculo(ingreso.getIdVehiculo(),ingreso.getFechaRecogida());

        if (calculoFinalRequest.getUsaBono()){
            Integer montoDescuentoPorBono=getMontoBonoSiEsPosible(ingreso.getFechaRecogida(),vehiculo.getMarca(),ingreso.getId());

        }
        DescuentosAplicables descuentos=new DescuentosAplicables(descuentosFeignClient);
        descuentos.setDescuentoDiaIngreso(ingreso.getFechaIngreso(),ingreso.getHoraIngreso());
        descuentos.setDescuentoPorNreps(numeroDeReps,vehiculo.getTipo());

        RecargosAplicables recargos=new RecargosAplicables(recargosFeignClient);
        recargos.setRecargoPorAntiguedad(vehiculo.getAnio_Fabricacion(), vehiculo.getTipo());
        recargos.setRecargoPorKilometraje(ingreso.getKilometrajeAlIngreso());
        recargos.setRecargoPorAtrasoEnRecoger(ingreso.getFechaSalida(),ingreso.getFechaRecogida());
        CalculadoraPrecioFinal calculadora= new CalculadoraPrecioFinal(montoReparaciones,vehiculo,recargos,descuentos);
        asignarMontosAlIngreso(ingreso, calculadora.calcularCostosCehckout());
        ingresoARepService.updateIngresoARep(ingreso);
    }

    private static IngresoARepEntity asignarMontosAlIngreso(IngresoARepEntity ingreso, MontosCheckout montosDelCheckout) {
        ingreso.setCostoTotal(montosDelCheckout.getCostoTotal());
        ingreso.setMontoTotalReparaciones(montosDelCheckout.getMontoReparaciones());
        ingreso.setMontoRecargos(montosDelCheckout.getMontoRecargos());
        ingreso.setMontoDescuentos(montosDelCheckout.getMontoDescuentos());
        ingreso.setMontoIVA(montosDelCheckout.getMontoIVA());
        return ingreso;
    }

    private Integer getMontoBonoSiEsPosible(Date fecha, String marca, Long idIngreso) throws Exception {
        int mes=0;
        int anio=0;

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecha);

        anio=calendar.get(Calendar.YEAR);
        mes=calendar.get(Calendar.MONTH)+1;

       Integer  bonosAplicadosEsteMes=bonoAplicadoFeignClient.getNumeroDeBonosAplicadosPorMesMarca(mes,anio,marca);
       Integer  bonosDisponibles=descuentosFeignClient.getCantidadBonos(marca);

        if (bonosAplicadosEsteMes>=bonosDisponibles){
            throw new Exception("No se puede asignar un bono a este vehiculo, intentelo denuevo");
        }
       Integer montoDelBono=descuentosFeignClient.getMontoBono(marca);
        if (montoDelBono==null){
            montoDelBono=0;
        }

        NuevoBonoAplicadoRequest request=new NuevoBonoAplicadoRequest();
        request.setIdIngreso(Math.toIntExact(idIngreso));
        request.setFechaAplicacion(fecha);
        request.setMarca(marca);
        request.setMonto((int)montoDelBono);

        bonoAplicadoFeignClient.saveBonoAplicado(request);


        return montoDelBono;
    }

    public List<ReporteReparacionCompleta> getTodasLasReparacionesConTodosLosDatos(){
        List<ReporteReparacionCompleta> listaReportes=new ArrayList<>();

        List<VehiculoEntity> listaVehiculos=vehiculoFeignClient.listVehiculos();
        List<IngresoARepEntity> listaIngresos=ingresoARepService.getReparaciones();
        IngresoARepEntity ingreso;
        VehiculoEntity vehiculo;
        /*Se obtienen las listas de todos los ingresos y todos los vehiculos asi no hacemos consultas de
        ms a las bases de datos
        */
        for (int i = 0; i < listaIngresos.size(); i++) {
            ingreso=listaIngresos.get(i);
            for (int j = 0; j < listaVehiculos.size(); j++) {
                vehiculo=listaVehiculos.get(j);
                if (ingreso.getIdVehiculo() == vehiculo.getId()){
                    ReporteReparacionCompleta reporte= new ReporteReparacionCompleta();
                    reporte.setIdIngreso(ingreso.getId());
                    reporte.setPatente(vehiculo.getPatente());
                    reporte.setMarca(vehiculo.getMarca());
                    reporte.setModelo(vehiculo.getModelo());
                    reporte.setTipo(vehiculo.getTipo());
                    reporte.setAnio_Fabricacion(vehiculo.getAnio_Fabricacion());
                    reporte.setTipoMotor(vehiculo.getTipoMotor());

                    reporte.setFechaIngreso(ingreso.getFechaIngreso());
                    reporte.setHoraIngreso(ingreso.getHoraIngreso());

                    reporte.setFechaSalida(ingreso.getFechaSalida());
                    reporte.setHoraSalida(ingreso.getHoraSalida());

                    reporte.setFechaRecogida(ingreso.getFechaRecogida());
                    reporte.setHoraRecogida(ingreso.getHoraRecogida());

                    reporte.setCostoTotal(ingreso.getCostoTotal());
                    reporte.setMontoTotalReparaciones(ingreso.getMontoTotalReparaciones());
                    reporte.setMontoRecargos(ingreso.getMontoRecargos());
                    reporte.setMontoDescuentos(ingreso.getMontoDescuentos());
                    reporte.setMontoIVA(ingreso.getMontoIVA());

                    listaReportes.add(reporte);

                }
            }

        }

        return listaReportes;
    }

}
