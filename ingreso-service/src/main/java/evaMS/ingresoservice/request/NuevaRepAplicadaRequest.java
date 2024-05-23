package evaMS.ingresoservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevaRepAplicadaRequest {
 private Long idVehiculo;
 private Integer idIngreso;
 private String tipoDeReparacion;
}
