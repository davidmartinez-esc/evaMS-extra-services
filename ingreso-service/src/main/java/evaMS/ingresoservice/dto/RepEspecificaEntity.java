package evaMS.ingresoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class RepEspecificaEntity {

    private int idIngresoARep;
    private String nombreDeLaRep;
    private int precioDeLaReparacion;
}
