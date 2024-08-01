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
    Integer repsMesActual;
    Integer sumaPrecioRepsMesActual;

    Integer variacionNRepsMesActualAnterior;
    Integer variacionSumaPrecioMesActualAnterior;

    Integer repsMesAnterior;
    Integer sumaPrecioRepsMesAnterior;

    Integer variacionNRepsMesAnteriorPrevio;
    Integer variacionSumaPrecioMesAnterioPrevio;

    //El mes previo anterior es el mes que viene antes del anterior
    Integer repsMesPrevioAnterior;
    Integer sumaPrecioRepsMesPrevioAnterior;

}
