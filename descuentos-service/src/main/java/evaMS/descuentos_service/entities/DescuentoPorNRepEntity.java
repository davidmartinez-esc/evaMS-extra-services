package evaMS.descuentos_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "descuento_por_numero_de_reparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoPorNRepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String numeroDeReparaciones;

    private String tipoDeMotor;

    private int porcentajeDescuento;




}
