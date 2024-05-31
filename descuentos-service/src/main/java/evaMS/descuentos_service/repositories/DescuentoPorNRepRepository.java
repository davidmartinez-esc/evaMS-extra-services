package evaMS.descuentos_service.repositories;


import evaMS.descuentos_service.entities.DescuentoPorNRepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import tingesoV1.eva1.entities.DescuentoPorNRepEntity;
@Repository
public interface DescuentoPorNRepRepository extends JpaRepository<DescuentoPorNRepEntity,Long> {
    @Query(value = "SELECT porcentaje_descuento FROM descuento_por_numero_de_reparaciones " +
            "WHERE numero_de_reparaciones = :numeroDeReps AND tipo_de_motor = :tipoDeMotor", nativeQuery = true)
    Integer getDescuentoByNumeroDeRepsTipoDeMotor(@Param("numeroDeReps") String numeroDeReps, @Param("tipoDeMotor") String tipoDeMotor);
}
