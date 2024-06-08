package evaMS.ingresoservice.clients;

import evaMS.ingresoservice.configurations.FeignClientConfig;
import evaMS.ingresoservice.request.RecargoPorAtrasoEnRecogerRequest;
import evaMS.ingresoservice.request.RequestDescuentoDiaHoraIngreso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@FeignClient(value = "recargos-service",
        path = "/api/v1/recargos",
        configuration = {FeignClientConfig.class})
public interface RecargosFeignClient {
    @GetMapping("/getRecargoPorKilometraje")
    Integer getRecargoPorKilometraje(@RequestParam int kilometraje, @RequestParam String tipoDeVehiculo);


    @PostMapping("/getRecargoPorAtrasoEnRecoger")
     Integer getRecargoPorAtrasoEnRecoger(@RequestBody RecargoPorAtrasoEnRecogerRequest request);
    @GetMapping("/getRecargoPorAntiguedad")
    Integer getRecargoPorAntiguedadYtipo(@RequestParam int anioFabricacion, @RequestParam String tipoDeVehiculo);

    }
