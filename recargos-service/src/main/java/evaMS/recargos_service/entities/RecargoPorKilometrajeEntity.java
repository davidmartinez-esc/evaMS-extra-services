package evaMS.recargos_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recargo_por_kilometraje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecargoPorKilometrajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String kilometraje;

    private String tipoDeVehiculo;

    private int porcentajeRecargo;

}
