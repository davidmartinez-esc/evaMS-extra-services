package evaMS.rdringresoservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

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

    @Temporal(TemporalType.DATE)
    private Date fechaReparacion;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaReparacion;


}
