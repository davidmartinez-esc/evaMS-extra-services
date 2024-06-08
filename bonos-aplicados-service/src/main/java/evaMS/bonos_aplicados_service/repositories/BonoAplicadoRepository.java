package evaMS.bonos_aplicados_service.repositories;


import evaMS.bonos_aplicados_service.entities.BonoAplicadoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BonoAplicadoRepository extends JpaRepository<BonoAplicadoEntity,Long> {

    /*
    @Query(value = "SELECT * FROM extra_hours WHERE extra_hours.rut = :rut AND date_part('year',hours.date)=:year AND MONTH(extra_hours.date)=:month", nativeQuery = true)
    List<ExtraHoursEntity> getExtraHoursByRutYearMonth(@Param("rut") String rut, @Param("year") int year, @Param("month") int month);
    */
    /*
    * SELECT COUNT(b.id) FROM bono_aplicado b JOIN ingreso_a_rep i ON i.id=b.idIngreso WHERE date.part('month',fechaIngreso)=:mes
    * */

    @Query(value = "SELECT COUNT(b.id) FROM bono_aplicado b WHERE EXTRACT(MONTH FROM b.fecha_aplicacion) = :mes AND " +
            "EXTRACT(YEAR FROM b.fecha_aplicacion) = :anio  AND b.marca = :marca", nativeQuery = true)
    Integer contarBonosPorMes(@Param("mes") int mes,@Param("marca") String marca, @Param("anio") int anio);



}
