package evaMS.ingresoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DescuentoAplicadoDto {
    private String tipoDeDescuento;
    private int porcentajeDescuento;

    private int idIngreso;
}
