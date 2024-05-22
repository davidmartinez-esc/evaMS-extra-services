package evaMS.ingresoservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "ingreso_a_rep")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngresoARepEntity {
    //FALTA CONSTRUCTOR PROPIO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int idVehiculo;

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
