package evaMS.ingresoservice.utlity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class MontosCheckout {
    private int costoTotal;
    private int montoReparaciones;
    private int montoDescuentos;
    private int montoRecargos;
   private int montoIVA;

}
