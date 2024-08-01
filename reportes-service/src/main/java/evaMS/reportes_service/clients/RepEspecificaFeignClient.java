package evaMS.reportes_service.clients;


import evaMS.reportes_service.configurations.FeignClientConfig;
import evaMS.reportes_service.dto.ReporteMesDto;
import evaMS.reportes_service.models.RepEspecificaEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "rdr-ingreso-service",
        path = "/api/v1/repEspecifica",
        configuration = {FeignClientConfig.class})
public interface RepEspecificaFeignClient {

    @GetMapping("/getReporteDelMesPorRep")
    ReporteMesDto getReporteUltimoMesPorRep(@RequestParam int mes, @RequestParam int year, @RequestParam String nombreRep);

    @GetMapping("/getPatentesReparadasCiertoMesAnio")
    List<String> getPatentesAutosReparadosCiertoMesAnio(@RequestParam int mes, @RequestParam int anio);

    @GetMapping("/getRepsEfectuadasCiertoMesAnio")
    List<RepEspecificaEntity> getRepsEfectuadasCiertoMesAnio(@RequestParam int mes, @RequestParam int anio);
}