package evaMS.rdringresoservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoEntity {

    private Long id;

    private String patente;
    private String marca;
    private String modelo;
    private String tipo;
    private String tipoMotor;
    private int numeroDeAsientos;
    private int anio_Fabricacion;
}
