package evaMS.reportes_service.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;




@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RepEspecificaEntity {


    private Long id;

    @Column(nullable = false)
    private int idIngresoARep;

    private String nombreDeLaRep;



    private int precioDeLaReparacion;

    @Temporal(TemporalType.DATE)
    private Date fechaReparacion;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaReparacion;

    private String patente;


}
