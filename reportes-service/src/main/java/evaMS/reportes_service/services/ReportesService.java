package evaMS.reportes_service.services;


import evaMS.reportes_service.clients.PrecioPorRepFeignClient;
import evaMS.reportes_service.clients.RepEspecificaFeignClient;
import evaMS.reportes_service.dto.ReporteMesDto;
import evaMS.reportes_service.dto.ReporteTresUltimosMeses;
import evaMS.reportes_service.requests.ReporteTresUltimosMesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReportesService {
  @Autowired
    RepEspecificaFeignClient repEspecificaFeignClient;

  @Autowired
    PrecioPorRepFeignClient precioPorRepFeignClient;

  public List<ReporteTresUltimosMeses> getReportesXTipoReparacionTresUltimosMeses(ReporteTresUltimosMesRequest request){
      //List<ReporteTresUltimosMeses> listaReportes=new ArrayList<>();
      List<String> listaTiposDeReparacion=precioPorRepFeignClient.getNombreDeLasReps();
      Date fechaActual=request.getFechaActual();
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

            reporteTresUltimosMeses.setCantidadReparacionesEfectuadasMesActual(numeroReparacionesMesActual);
            reporteTresUltimosMeses.setTotalRecaudadoReparacionMesActual(totalRecaudadoMesActual);


            calendar.add(Calendar.MONTH, -1);

            reporteMes = repEspecificaFeignClient.
                    getReporteUltimoMesPorRep(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), nombreDeLaRep);

            Integer numeroReparacionesMesAnterior=reporteMes.getNumeroReparacionesMesActual();
            Integer totalRecaudadoMesAnterior=reporteMes.getTotalRecaudadoMesActual();

            reporteTresUltimosMeses.setCantidadReparacionesEfectuadasMesAnterior(numeroReparacionesMesAnterior);
            reporteTresUltimosMeses.setTotalRecaudadoXReparacionMesAnterior(totalRecaudadoMesAnterior);

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
                    setPorcentajeVariacionReparacionesEfectuadasMesActualAnterior(variacionNumeroReparacionesActualAnterior);

            reporteTresUltimosMeses.
                    setPorcentajeVariacionTotalRecaudadoMesActualAnterior(variacionTotalRecaudadoMesActualAnterior);

            calendar.add(Calendar.MONTH, -1);

            reporteMes = repEspecificaFeignClient.
                    getReporteUltimoMesPorRep(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), nombreDeLaRep);

            Integer numeroReparacionesMesPrevioAnterior=reporteMes.getNumeroReparacionesMesActual();
            Integer totalRecaudadoMesPrevioAnterior=reporteMes.getTotalRecaudadoMesActual();

            reporteTresUltimosMeses.setCantidadReparacionesEfectuadasMesPrevioAnterior(numeroReparacionesMesPrevioAnterior);
            reporteTresUltimosMeses.setTotalRecaudadoReparacionMesPrevioAnterior(totalRecaudadoMesPrevioAnterior);

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
                    setPorcentajeVariacioReparacionesEfectuadasnMesAnteriorPrevio(variacionNumeroReparacionesAnteriorPrevio);

            reporteTresUltimosMeses.
                    setPorcentajeVariacioDineroRecaudadoMesAnteriorPrevio(variacionTotalRecaudadoMesAnteriorPrevio);



            listaRespuesta.add(reporteTresUltimosMeses);
        }


      return listaRespuesta;

  }






}
