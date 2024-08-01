package evaMS.reportes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteRepXTipoVehiculo {
    private String nombreTipoReparacion;
    private  Integer aplicadasASedan;
    private Integer montoTotalSedan;

    private  Integer aplicadasAHatchback;
    private Integer montoTotalHatchback;

    private  Integer aplicadasASUV;
    private Integer montoTotalSUV;

    private  Integer aplicadasAPickup;
    private Integer montoTotalPickup;

    private  Integer aplicadasAFurgoneta;
    private Integer montoTotalFurgoneta;

    private  Integer totalAplicadas;
    private Integer montoTotalReparaciones;






}
