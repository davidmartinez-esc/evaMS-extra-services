package evaMS.rdringresoservice.repositories;



import evaMS.rdringresoservice.dto.INRepsEfectuadasYMonto;
import evaMS.rdringresoservice.dto.IReporteMes;
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

    @Query(value =
            "SELECT *\n" +
            "FROM (\n" +
            "SELECT \n" +
            "SUM(precio_de_la_reparacion) AS totalRecaudadoMesActual,\n" +
            "COUNT(nombre_de_la_rep) AS numeroReparacionesMesActual\n" +
            "FROM reparacion_especifica re\n" +
            "WHERE EXTRACT(MONTH FROM re.fecha_reparacion) = :mesActual AND nombre_de_la_rep= :nombreRep AND EXTRACT(YEAR FROM re.fecha_reparacion) = :actualYear \n" +
            "GROUP BY nombre_de_la_rep) tabla_respuesta\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "0 AS totalRecaudadoMesActual,\n" +
            "0 AS numeroReparacionesMesActual\n" +
            "WHERE NOT EXISTS (SELECT 1 FROM (\n" +
            "SELECT 1\n" +
            "FROM reparacion_especifica re\n" +
            "WHERE EXTRACT(MONTH FROM re.fecha_reparacion) = :mesActual AND nombre_de_la_rep= :nombreRep AND EXTRACT(YEAR FROM re.fecha_reparacion) = :actualYear  \n" +
            "GROUP BY nombre_de_la_rep))", nativeQuery = true)
    IReporteMes getReporteReparacionPorMes(@Param("mesActual") int mesActual,
                                                                 @Param("actualYear") int actualYear,
                                                                 @Param("nombreRep") String nombreRep);

    @Query(value = "SELECT COUNT(r.precio_de_la_reparacion) AS NumeroDeReparaciones, SUM(r.precio_de_la_reparacion) AS MontoTotal  " +
            "FROM reparacion_especifica r WHERE r.patente = :patente AND r.nombre_de_la_rep = :nombreDeLaRep", nativeQuery = true)
    INRepsEfectuadasYMonto getNumeroDeRepsYMontoByPatenteTipoVehiculo(@Param("patente") String patente,@Param("nombreDeLaRep") String nombreDeLaRep);


    @Query(value = "SELECT * FROM reparacion_especifica r WHERE EXTRACT(MONTH FROM r.fecha_reparacion) = :mes " +
            "AND EXTRACT(YEAR FROM r.fecha_reparacion) = :anio", nativeQuery = true)
    List<RepEspecificaEntity> getRepsEfectuadasEnCiertoMesAnio(@Param("mes") int mes,@Param("anio") int anio);

    @Query(value = "SELECT DISTINCT patente FROM reparacion_especifica r WHERE EXTRACT(MONTH FROM r.fecha_reparacion) = :mes " +
            "AND EXTRACT(YEAR FROM r.fecha_reparacion) = :anio", nativeQuery = true)
    List<String> getPatentesDeAutosReparadosCiertoMesAnio(@Param("mes") int mes, @Param("anio") int anio);

}
