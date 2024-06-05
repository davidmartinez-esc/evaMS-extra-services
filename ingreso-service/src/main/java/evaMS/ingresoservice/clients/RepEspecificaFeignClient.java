package evaMS.ingresoservice.clients;

import evaMS.ingresoservice.configurations.FeignClientConfig;
import evaMS.ingresoservice.dto.RepEspecificaEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "rdr-ingreso-service",
        path = "/api/v1/repEspecifica",
        configuration = {FeignClientConfig.class})
public interface RepEspecificaFeignClient {

    @PostMapping("/")
    RepEspecificaEntity saveRepEspecifica(@RequestBody RepEspecificaEntity tipoDeRep);

    @GetMapping("/getMontoTotalDelIngreso/{id}")
     Integer getMontoTotalDelIngreso(@PathVariable int id);

}
