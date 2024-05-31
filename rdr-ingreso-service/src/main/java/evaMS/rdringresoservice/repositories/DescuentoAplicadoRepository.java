package evaMS.rdringresoservice.repositories;

import evaMS.rdringresoservice.entities.DescuentoAplicadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescuentoAplicadoRepository extends JpaRepository<DescuentoAplicadoEntity,Long> {
}
