package evaMS.ingresoservice.clients;

import evaMS.ingresoservice.configurations.FeignClientConfig;
import evaMS.ingresoservice.dto.VehiculoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "vehiculo-service",
        path = "/api/v1/vehiculo",
        configuration = {FeignClientConfig.class})
public interface VehiculoFeignClient {

    @GetMapping("/{id}")
    VehiculoEntity getVehiculoById(@PathVariable Long id);
}
