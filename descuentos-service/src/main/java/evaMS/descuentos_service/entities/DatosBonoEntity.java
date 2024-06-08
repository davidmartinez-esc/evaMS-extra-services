package evaMS.descuentos_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "datos_bono")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosBonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    private int monto;

    private int cantidadDisponiblePorMes;

    @Column(length=50)
    private String marca;
}
