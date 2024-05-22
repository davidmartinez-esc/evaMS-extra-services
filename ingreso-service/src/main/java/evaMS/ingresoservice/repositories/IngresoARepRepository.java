package evaMS.ingresoservice.repositories;


import evaMS.ingresoservice.entities.IngresoARepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//import tingesoV1.eva1.entities.IngresoARepEntity;
@Repository
public interface IngresoARepRepository extends JpaRepository<IngresoARepEntity,Long> {

    List<IngresoARepEntity> findByIdVehiculo(int idVehiculo);
}
