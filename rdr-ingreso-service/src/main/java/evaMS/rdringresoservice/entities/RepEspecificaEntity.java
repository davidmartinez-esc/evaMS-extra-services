package evaMS.rdringresoservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reparacion_especifica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepEspecificaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private int idIngresoARep;

    private String nombreDeLaRep;
    private int precioDeLaReparacion;


}
