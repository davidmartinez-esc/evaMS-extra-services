package evaMS.ingresoservice.clients;

import evaMS.ingresoservice.configurations.FeignClientConfig;
import evaMS.ingresoservice.dto.DescuentoAplicadoDto;
import evaMS.ingresoservice.dto.VehiculoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "descuentos-service",
        path = "/api/v1/descuentoAplicado",
        configuration = {FeignClientConfig.class})
public interface DescuentoAplicadoFeignClient {
    @PostMapping("/")
    DescuentoAplicadoDto saveDescuentoAplicado(@RequestBody DescuentoAplicadoDto descuentoAplicado);


    }
