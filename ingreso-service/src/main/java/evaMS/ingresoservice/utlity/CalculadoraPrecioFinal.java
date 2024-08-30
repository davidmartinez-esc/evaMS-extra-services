package evaMS.ingresoservice.utlity;


import evaMS.ingresoservice.dto.VehiculoEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CalculadoraPrecioFinal {
    private int montoReparaciones;
    private VehiculoEntity vehiculo;
    private RecargosAplicables recargos;
    private DescuentosAplicables descuentos;
    private  final float IVA= 0.19F;
    public MontosCheckout calcularCostosCehckout(){
        int montoDescuentos=calcularMontoDescontado();
        int montoRecargos=calcularMontoRecargado();
        int montoIVA=(int)(montoReparaciones*IVA);
        int costoTotal= montoReparaciones-montoDescuentos+montoRecargos + montoIVA;

        MontosCheckout montosCheckout=new MontosCheckout(costoTotal,montoReparaciones,montoDescuentos,montoRecargos,
                montoIVA);

        return montosCheckout;

    }

    private int calcularMontoDescontado(){
        int montoDescuentos=(int) (montoReparaciones* descuentos.getDescuentoPorNreps());
        montoDescuentos=(int)(montoDescuentos+montoReparaciones*descuentos.getDescuentoPorIngresoLunesJueves());
        montoDescuentos=montoDescuentos+ descuentos.getMontoDescuentoPorBono();
        return montoDescuentos;
    }

    private int calcularMontoRecargado(){
        int montoRecargos=(int)(montoReparaciones* recargos.getRecargoPorAntiguedad());
        montoRecargos= (int) (montoRecargos+montoReparaciones*recargos.getRecargoPorKilometraje());
        montoRecargos= (int) (montoRecargos+montoReparaciones*recargos.getRecargoPorAtrasoEnRecoger());
        return  montoRecargos;
    }

}
