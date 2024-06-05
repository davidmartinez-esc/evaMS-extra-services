package evaMS.recargos_service.repositories;


import evaMS.recargos_service.entities.RecargoPorAntiguedadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//tingesoV1.eva1.entities.RecargoPorAntiguedadEntity;

@Repository
public interface RecargoPorAntiguedadRepository extends JpaRepository<RecargoPorAntiguedadEntity,Long> {
    @Query(value = "SELECT porcentaje_recargo " + "FROM recargo_por_antiguedad ra " +
            "WHERE ra.antiguedad=:antiguedad AND ra.tipo_de_vehiculo=:tipoDeVehiculo", nativeQuery = true)
    Integer getRecargoByAntiguedadPorTipo(@Param("antiguedad") String antiguedad, @Param("tipoDeVehiculo") String tipoDeVehiculo);
}
