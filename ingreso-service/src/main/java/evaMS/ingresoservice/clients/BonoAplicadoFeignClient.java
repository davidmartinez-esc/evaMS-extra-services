package evaMS.ingresoservice.clients;

import evaMS.ingresoservice.configurations.FeignClientConfig;
import evaMS.ingresoservice.dto.RepEspecificaEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "bonos-aplicados-service",
        path = "/api/v1/bonoAplicado",
        configuration = {FeignClientConfig.class})
public interface BonoAplicadoFeignClient {

    @GetMapping("/getNumeroDeBonos")
    Integer getNumeroDeBonosAplicadosPorMesMarca(@RequestParam int mes, @RequestParam int anio,
                                                 @RequestParam String marca);


}
