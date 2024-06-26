package evaMS.recargos_service.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecargoPorAtrasoEnRecogerRequest {
    Date fechaSalida;
    Date fechaRecogida;
}
