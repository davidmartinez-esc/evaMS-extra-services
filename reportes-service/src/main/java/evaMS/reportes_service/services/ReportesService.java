package evaMS.reportes_service.services;


import evaMS.reportes_service.clients.PrecioPorRepFeignClient;
import evaMS.reportes_service.clients.RepEspecificaFeignClient;
import evaMS.reportes_service.clients.VehiculoFeignClient;
import evaMS.reportes_service.dto.PatenteYTipo;
import evaMS.reportes_service.dto.ReporteMesDto;
import evaMS.reportes_service.dto.ReporteRepXTipoVehiculo;
import evaMS.reportes_service.dto.ReporteTresUltimosMeses;
import evaMS.reportes_service.models.RepEspecificaEntity;
import jakarta.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportesService {
  @Autowired
    RepEspecificaFeignClient repEspecificaFeignClient;

  @Autowired
    PrecioPorRepFeignClient precioPorRepFeignClient;

  @Autowired
    VehiculoFeignClient vehiculoFeignClient;

  public List<ReporteTresUltimosMeses> getReportesXTipoReparacionTresUltimosMeses(String mes,String anio) throws ParseException {

      List<String> listaTiposDeReparacion=precioPorRepFeignClient.getNombreDeLasReps();

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

      String fechaString=anio;

      fechaString=fechaString.concat("-");
      fechaString=fechaString.concat(mes);
      fechaString=fechaString.concat("-20");

      Date fechaActual=formatter.parse(fechaString);

      ReporteMesDto reporteMes=new ReporteMesDto();

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fechaActual);

      List<ReporteTresUltimosMeses> listaRespuesta=new ArrayList<>();


        for (String nombreDeLaRep : listaTiposDeReparacion) {

            ReporteTresUltimosMeses reporteTresUltimosMeses = new ReporteTresUltimosMeses();

            reporteTresUltimosMeses.setNombreTipoDeRep(nombreDeLaRep);

            calendar.setTime(fechaActual);

            reporteMes = repEspecificaFeignClient.
                    getReporteUltimoMesPorRep(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), nombreDeLaRep);

            Integer numeroReparacionesMesActual=reporteMes.getNumeroReparacionesMesActual();
            Integer totalRecaudadoMesActual=reporteMes.getTotalRecaudadoMesActual();

            reporteTresUltimosMeses.setRepsMesActual(numeroReparacionesMesActual);
            reporteTresUltimosMeses.setSumaPrecioRepsMesActual(totalRecaudadoMesActual);


            calendar.add(Calendar.MONTH, -1);

            reporteMes = repEspecificaFeignClient.
                    getReporteUltimoMesPorRep(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), nombreDeLaRep);

            Integer numeroReparacionesMesAnterior=reporteMes.getNumeroReparacionesMesActual();
            Integer totalRecaudadoMesAnterior=reporteMes.getTotalRecaudadoMesActual();

            reporteTresUltimosMeses.setRepsMesAnterior(numeroReparacionesMesAnterior);
            reporteTresUltimosMeses.setSumaPrecioRepsMesAnterior(totalRecaudadoMesAnterior);

            Integer variacionNumeroReparacionesActualAnterior;
            Integer variacionTotalRecaudadoMesActualAnterior;

            if (numeroReparacionesMesAnterior==0){
                variacionNumeroReparacionesActualAnterior=0;
                variacionTotalRecaudadoMesActualAnterior=0;
            }
            else{
                variacionNumeroReparacionesActualAnterior=
                        ((numeroReparacionesMesActual-numeroReparacionesMesAnterior)*100)/numeroReparacionesMesAnterior;
                variacionTotalRecaudadoMesActualAnterior=
                        ((totalRecaudadoMesActual-totalRecaudadoMesAnterior)*100)/totalRecaudadoMesAnterior;
            }



            reporteTresUltimosMeses.
                    setVariacionNRepsMesActualAnterior(variacionNumeroReparacionesActualAnterior);

            reporteTresUltimosMeses.
                    setVariacionSumaPrecioMesActualAnterior(variacionTotalRecaudadoMesActualAnterior);

            calendar.add(Calendar.MONTH, -1);

            reporteMes = repEspecificaFeignClient.
                    getReporteUltimoMesPorRep(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), nombreDeLaRep);

            Integer numeroReparacionesMesPrevioAnterior=reporteMes.getNumeroReparacionesMesActual();
            Integer totalRecaudadoMesPrevioAnterior=reporteMes.getTotalRecaudadoMesActual();

            reporteTresUltimosMeses.setRepsMesPrevioAnterior(numeroReparacionesMesPrevioAnterior);
            reporteTresUltimosMeses.setSumaPrecioRepsMesPrevioAnterior(totalRecaudadoMesPrevioAnterior);

            Integer variacionNumeroReparacionesAnteriorPrevio;
            Integer variacionTotalRecaudadoMesAnteriorPrevio;

            if (numeroReparacionesMesPrevioAnterior==0){
                variacionNumeroReparacionesAnteriorPrevio=0;
                variacionTotalRecaudadoMesAnteriorPrevio=0;
            }
            else{
                variacionNumeroReparacionesAnteriorPrevio=
                        ((numeroReparacionesMesAnterior-numeroReparacionesMesPrevioAnterior)*100)/numeroReparacionesMesPrevioAnterior;
                variacionTotalRecaudadoMesAnteriorPrevio=
                        ((totalRecaudadoMesAnterior-totalRecaudadoMesPrevioAnterior)*100)/totalRecaudadoMesPrevioAnterior;
            }

            reporteTresUltimosMeses.
                    setVariacionNRepsMesAnteriorPrevio(variacionNumeroReparacionesAnteriorPrevio);

            reporteTresUltimosMeses.
                    setVariacionSumaPrecioMesAnterioPrevio(variacionTotalRecaudadoMesAnteriorPrevio);



            listaRespuesta.add(reporteTresUltimosMeses);
        }


      return listaRespuesta;

  }

 public List<ReporteRepXTipoVehiculo> getReporteTiposDeRepXTipoVehiculo(int mes, int year){
    List<ReporteRepXTipoVehiculo> listaReportes=new ArrayList<>();

    List<String> tiposDeReparacionRegistrados=precioPorRepFeignClient.getNombreDeLasReps();

    List<RepEspecificaEntity> reparacionesEfectuadas= repEspecificaFeignClient.getRepsEfectuadasCiertoMesAnio(mes,year);
     if (reparacionesEfectuadas == null){
         throw new NotFoundException("No se registraron reparaciones en ese mes");
     }

    List<String> patentesQueTuvieronReparacion=repEspecificaFeignClient.getPatentesAutosReparadosCiertoMesAnio(mes, year);
    if (patentesQueTuvieronReparacion == null){
        throw new NotFoundException("No se registraron reparaciones en ese mes");
    }

    List<PatenteYTipo> patentesConTipoDeVehiculo=new ArrayList<>();

     for (int i = 0, patentesQueTuvieronReparacionSize = patentesQueTuvieronReparacion.size(); i < patentesQueTuvieronReparacionSize; i++) {
         String patente = patentesQueTuvieronReparacion.get(i);

         PatenteYTipo patenteYTipo = new PatenteYTipo();
         patenteYTipo.setPatente(patente);
         patenteYTipo.setTipo(vehiculoFeignClient.getTipoVehiculo(patente));

         patentesConTipoDeVehiculo.add(patenteYTipo);
     }

     //Iteracion Segun tipo de Reparacion


     //Este for va tipo por tipo de reparacion buscando si se efectuo una reparacion de ese tipo en el mes y año pedido
     for (Iterator<String> iterator = tiposDeReparacionRegistrados.iterator(); iterator.hasNext(); ) {

         String tipoDeReparacion = iterator.next();

         ReporteRepXTipoVehiculo nuevoReporte= new ReporteRepXTipoVehiculo(
                 tipoDeReparacion,0,0,0,0,
                 0,0,0,0,0,0,
                 0,0
         );

         for (RepEspecificaEntity rep : reparacionesEfectuadas){

             String nombreRepEfectuada=rep.getNombreDeLaRep();

             if (nombreRepEfectuada.equals(tipoDeReparacion)){

                 String patenteDeLaRepEfectuada=rep.getPatente();

                 PatenteYTipo patenteYTipoBuscados= patentesConTipoDeVehiculo.stream()
                         .filter(patenteYTipo -> patenteDeLaRepEfectuada.equals(patenteYTipo.getPatente()))
                         .findAny()
                         .orElse(null);



                 switch (patenteYTipoBuscados.getTipo()){
                     case "HATCHBACK":
                         nuevoReporte.setAplicadasAHatchback(nuevoReporte.getAplicadasAHatchback()+1);
                         nuevoReporte.setMontoTotalHatchback(nuevoReporte.getMontoTotalHatchback()+
                                 rep.getPrecioDeLaReparacion());
                         break;
                     case "SEDAN":
                         nuevoReporte.setAplicadasASedan(nuevoReporte.getAplicadasASedan()+1);
                         nuevoReporte.setMontoTotalSedan(nuevoReporte.getMontoTotalSedan()+
                                 rep.getPrecioDeLaReparacion());
                         break;
                     case "PICKUP":
                         nuevoReporte.setAplicadasAPickup(nuevoReporte.getAplicadasAPickup()+1);
                         nuevoReporte.setMontoTotalPickup(nuevoReporte.getMontoTotalPickup()+
                                 rep.getPrecioDeLaReparacion());
                         break;
                     case "SUV":
                         nuevoReporte.setAplicadasASUV(nuevoReporte.getAplicadasASUV()+1);
                         nuevoReporte.setMontoTotalSUV(nuevoReporte.getMontoTotalSUV()+
                                 rep.getPrecioDeLaReparacion());
                         break;
                     case "FURGONETA":
                         nuevoReporte.setAplicadasAFurgoneta(nuevoReporte.getAplicadasAFurgoneta()+1);
                         nuevoReporte.setMontoTotalFurgoneta(nuevoReporte.getMontoTotalFurgoneta()+
                                 rep.getPrecioDeLaReparacion());
                         break;
                     default:
                         break;

                 }


             }

         }

         nuevoReporte.setTotalAplicadas(nuevoReporte.getAplicadasAFurgoneta() + nuevoReporte.getAplicadasASUV()+
                 nuevoReporte.getAplicadasAHatchback() + nuevoReporte.getAplicadasAPickup() + nuevoReporte.getAplicadasASedan());

         nuevoReporte.setMontoTotalReparaciones( nuevoReporte.getMontoTotalHatchback()+ nuevoReporte.getMontoTotalFurgoneta()
         + nuevoReporte.getMontoTotalSedan()+ nuevoReporte.getMontoTotalPickup()+ nuevoReporte.getMontoTotalSUV());

         listaReportes.add(nuevoReporte);





     }





    return listaReportes;
 }






}
