package evaMS.recargos_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "recargo_por_antiguedad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecargoPorAntiguedadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String antiguedad;

    private String tipoDeVehiculo;

    private int porcentajeRecargo;
}
