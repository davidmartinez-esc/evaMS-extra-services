package evaMS.reportes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteTresUltimosMeses {
    String nombreTipoDeRep;
    Integer cantidadReparacionesEfectuadasMesActual;
    Integer totalRecaudadoReparacionMesActual;

    Integer porcentajeVariacionReparacionesEfectuadasMesActualAnterior;
    Integer porcentajeVariacionTotalRecaudadoMesActualAnterior;

    Integer cantidadReparacionesEfectuadasMesAnterior;
    Integer totalRecaudadoXReparacionMesAnterior;

    Integer porcentajeVariacioReparacionesEfectuadasnMesAnteriorPrevio;
    Integer porcentajeVariacioDineroRecaudadoMesAnteriorPrevio;

    Integer cantidadReparacionesEfectuadasMesPrevioAnterior;
    Integer totalRecaudadoReparacionMesPrevioAnterior;

}
