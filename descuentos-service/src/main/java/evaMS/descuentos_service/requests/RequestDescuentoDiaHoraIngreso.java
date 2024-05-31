package evaMS.descuentos_service.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
public class RequestDescuentoDiaHoraIngreso {
    LocalTime horaIngreso;
    Date fechaIngreso;
}
