package evaMS.rdringresoservice.repositories;



import evaMS.rdringresoservice.entities.RepEspecificaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepEspecificaRepository extends JpaRepository<RepEspecificaEntity, Long> {


    List<RepEspecificaEntity> findByIdIngresoARep(int idIngresoARep);

    @Query(value = "SELECT SUM(r.precio_de_la_reparacion) FROM reparacion_especifica r WHERE r.id_ingresoarep = :idIngreso", nativeQuery = true)
    Integer getCostoDeLasReparacionesDeIngreso(@Param("idIngreso") int idIngreso);

    @Query(value = "DELETE FROM reparacion_especifica r " +
            "WHERE r.id_ingresoarep = :idIngreso AND r.nombre_de_la_rep = :nombreDeLaRep", nativeQuery = true)
    void deleteReparacionEspecificaByIdIngresoNombre(@Param("idIngreso") int idIngreso,
                                                     @Param("nombreDeLaRep") String nombreDeLaRep);
}
