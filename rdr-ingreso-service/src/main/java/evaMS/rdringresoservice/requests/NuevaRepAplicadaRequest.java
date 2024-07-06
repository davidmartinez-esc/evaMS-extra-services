package evaMS.rdringresoservice.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class NuevaRepAplicadaRequest {

 private Integer idIngreso;
 private String tipoDeReparacion;

 @Temporal(TemporalType.DATE)
 private Date fechaReparacion;

 @Temporal(TemporalType.TIME)
 @JsonFormat(pattern = "HH:mm")
 private LocalTime horaReparacion;
}
