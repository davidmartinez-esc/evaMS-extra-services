package evaMS.rdringresoservice.clients;


import evaMS.rdringresoservice.configurations.FeignClientConfig;
import evaMS.rdringresoservice.models.IngresoARepEntity;
import evaMS.rdringresoservice.models.VehiculoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value = "ingreso-service",
        path = "/api/v1/ingresoAReparacion",
        configuration = {FeignClientConfig.class})
public interface IngresoFeignClient {



    @GetMapping("/{id}")
    IngresoARepEntity getIngresoARepById(@PathVariable Long id);
}
