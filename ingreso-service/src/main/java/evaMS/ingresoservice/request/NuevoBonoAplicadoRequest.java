package evaMS.ingresoservice.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NuevoBonoAplicadoRequest {

 private int monto;

 @Temporal(TemporalType.DATE)
 private Date fechaAplicacion;

 private String marca;

 private int idIngreso;

}
