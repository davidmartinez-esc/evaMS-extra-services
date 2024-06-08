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


@NoArgsConstructor
@Getter
@Setter
public class RepEspecificaEntity {

    private int idIngresoARep;
    private String nombreDeLaRep;
    private int precioDeLaReparacion;

    @Temporal(TemporalType.DATE)
    private Date fechaReparacion;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaReparacion;

}
