package evaMS.rdringresoservice.clients;


import evaMS.rdringresoservice.configurations.FeignClientConfig;
import evaMS.rdringresoservice.models.VehiculoEntity;
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

    @GetMapping("/{id}")
    VehiculoEntity getVehiculoById(@PathVariable Long id);

    @GetMapping("/")
    List<VehiculoEntity> listVehiculos();

    @GetMapping("/getTipoVehiculo")
    String getTipoVehiculo(@RequestParam String patente);
}
