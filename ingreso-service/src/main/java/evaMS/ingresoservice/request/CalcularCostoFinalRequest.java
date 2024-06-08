package evaMS.ingresoservice.request;

import evaMS.ingresoservice.entities.IngresoARepEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CalcularCostoFinalRequest {
    Boolean usaBono;
    IngresoARepEntity ingreso;
}
