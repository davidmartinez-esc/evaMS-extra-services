package evaMS.ingresoservice.repositories;


import evaMS.ingresoservice.entities.IngresoARepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

//import tingesoV1.eva1.entities.IngresoARepEntity;
@Repository
public interface IngresoARepRepository extends JpaRepository<IngresoARepEntity,Long> {

    List<IngresoARepEntity> findByIdVehiculo(int idVehiculo);

    @Query(value = "SELECT COUNT(id) FROM ingreso_a_rep WHERE fecha_recogida <= :fecha AND fecha_recogida >= :fechaHaceUnAnio AND id_vehiculo = :idVehiculo", nativeQuery = true)
    Integer getNumeroDeReparaciones(@Param("fecha") Date fecha,@Param("fechaHaceUnAnio") Date fechaHaceUnAnio,@Param("idVehiculo") int idVehiculo);

    @Query(value = "SELECT COUNT(id) FROM ingreso_a_rep", nativeQuery = true)
    Integer getTotalDeIngresos();
}
