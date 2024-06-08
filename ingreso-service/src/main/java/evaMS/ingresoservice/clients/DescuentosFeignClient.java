package evaMS.ingresoservice.clients;

import evaMS.ingresoservice.configurations.FeignClientConfig;
import evaMS.ingresoservice.request.RequestDescuentoDiaHoraIngreso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "descuentos-service",
        path = "/api/v1/descuentos",
        configuration = {FeignClientConfig.class})
public interface DescuentosFeignClient {
    @GetMapping("/descuentosPorNRep/getDescuentoPorNumeroDeReparaciones")
    Integer getDescuentoPorNReps(@RequestParam int nReps, @RequestParam String tipoMotor);

    @PostMapping("/getDescuentoPorDiaHoraIngreso")
    Integer getDescuentoPorDiaHoraIngreso(@RequestBody RequestDescuentoDiaHoraIngreso request);

    @GetMapping("/descuentoPorBono/getCantidadBonos")
    Integer getCantidadBonos(@RequestParam String marca);

    @GetMapping("/descuentoPorBono/getMontoBono")
    Integer getMontoBono(@RequestParam String marca);
    }
