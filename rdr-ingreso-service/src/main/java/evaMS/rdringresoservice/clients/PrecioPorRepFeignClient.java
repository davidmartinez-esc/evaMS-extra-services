package evaMS.rdringresoservice.clients;


import evaMS.rdringresoservice.configurations.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "precio-por-rep-service",
        path = "/api/v1/precioPorRep",
        configuration = {FeignClientConfig.class})
public interface PrecioPorRepFeignClient {

    @GetMapping("/precioByMotorYReparacion/{reparacion}/{tipoDeMotor}")
    Integer  getPrecioByRepYTipoDeMotor(@PathVariable String reparacion,@PathVariable String tipoDeMotor);
}
