package evaMS.ingresoservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDescuentoDiaHoraIngreso {
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    LocalTime horaIngreso;


    Date fechaIngreso;
}
