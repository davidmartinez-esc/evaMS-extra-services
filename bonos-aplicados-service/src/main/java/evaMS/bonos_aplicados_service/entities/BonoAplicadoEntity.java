package evaMS.bonos_aplicados_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "bono_aplicado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonoAplicadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    private int monto;

    private Date fechaAplicacion;

    private String marca;

    private int idIngreso;
}
