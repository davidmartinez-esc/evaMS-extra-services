package evaMS.rdringresoservice.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrarReparacionEspecificaRequest {
    String nombreReparacion;
    int idIngresoARep;
}
