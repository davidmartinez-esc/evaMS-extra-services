package evaMS.descuentos_service.repositories;

import evaMS.descuentos_service.entities.DatosBonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosBonoRepository extends JpaRepository<DatosBonoEntity,Long> {
    @Query(value = "SELECT monto FROM datos_bono WHERE marca = :marca", nativeQuery = true)
    Integer getMontoDelBono(@Param("marca") String marca);

    @Query(value = "SELECT cantidad_disponible_por_mes FROM datos_bono WHERE marca = :marca", nativeQuery = true)
    Integer getCantidadDeBonosDisponiblesXMes(@Param("marca") String marca);
}
