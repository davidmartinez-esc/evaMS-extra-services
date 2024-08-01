package evaMS.reportes_service.clients;


import evaMS.reportes_service.configurations.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "vehiculo-service",
        path = "/api/v1/vehiculo",
        configuration = {FeignClientConfig.class})
public interface VehiculoFeignClient {

    @GetMapping("/getTodasLasPatentes")
    List<String> getTodasLasPatentes();

    @GetMapping("/getTipoVehiculo")
    String getTipoVehiculo(@RequestParam String patente);
}
