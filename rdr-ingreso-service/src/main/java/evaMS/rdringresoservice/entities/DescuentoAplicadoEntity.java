package evaMS.rdringresoservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "descuento_aplicado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoAplicadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String tipoDeDescuento;
    private int porcentajeDescuento;

    private int idIngreso;
}
