package evaMS.ingresoservice.clients;

import evaMS.ingresoservice.configurations.FeignClientConfig;
import evaMS.ingresoservice.dto.RepEspecificaEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "rdr-ingreso-service",
        path = "/api/v1/repEspecifica",
        configuration = {FeignClientConfig.class})
public interface RepEspecificaFeignClient {

    @PostMapping("/")
    RepEspecificaEntity saveRepEspecifica(@RequestBody RepEspecificaEntity tipoDeRep);

}
