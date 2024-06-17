package evaMS.ingresoservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReporteReparacionCompleta {


    private String patente;
    private String marca;
    private String modelo;
    private String tipo;
    private String tipoMotor;
    private int anio_Fabricacion;

    private int costoTotal;

    private int montoTotalReparaciones;

    private int montoRecargos;

    private int montoDescuentos;

    private int montoIVA;

    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaIngreso;


    @Temporal(TemporalType.DATE)
    private Date fechaSalida;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaSalida;

    @Temporal(TemporalType.DATE)
    private Date fechaRecogida;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaRecogida;
}
