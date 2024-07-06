package evaMS.reportes_service.clients;


import evaMS.reportes_service.configurations.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(value = "precio-por-rep-service",
        path = "/api/v1/precioPorRep",
        configuration = {FeignClientConfig.class})
public interface PrecioPorRepFeignClient {

    @GetMapping("/getNombreDeLasReparaciones")
    List<String> getNombreDeLasReps();


}
