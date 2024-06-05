package evaMS.ingresoservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class RequestDescuentoDiaHoraIngreso {
    LocalTime horaIngreso;
    Date fechaIngreso;
}
