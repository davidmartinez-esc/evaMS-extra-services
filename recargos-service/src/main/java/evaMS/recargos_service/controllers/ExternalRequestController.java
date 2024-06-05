package evaMS.recargos_service.controllers;

import evaMS.recargos_service.services.RecargoPorAntiguedadService;
import evaMS.recargos_service.services.RecargoPorAtrasoEnRecoger;
import evaMS.recargos_service.services.RecargoPorKilometrajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/recargos")
public class ExternalRequestController {
    @Autowired
    RecargoPorKilometrajeService recargoPorKilometrajeService;

    @Autowired
    RecargoPorAtrasoEnRecoger recargoPorAtrasoEnRecoger;

    @Autowired
    RecargoPorAntiguedadService recargoPorAntiguedadService;

    @GetMapping("/getRecargoPorKilometraje")
    public ResponseEntity<Integer> getRecargoPorKilometraje(@RequestParam int kilometraje,
                                                            @RequestParam String tipoDeVehiculo) {
        Integer recargoPorKilometrajeResponse =
                recargoPorKilometrajeService.getRecargoPorKilometrajeByTipoDeVehiculo(kilometraje,tipoDeVehiculo);
        return ResponseEntity.ok(recargoPorKilometrajeResponse);
    }



    @GetMapping("/getRecargoPorAtrasoEnRecoger")
    public ResponseEntity<Integer> getRecargoPorAtrasoEnRecoger(@RequestParam Date fechaSalida,
                                                                @RequestParam Date fechaRecogida) {
        Integer recargoPorRecogidaTardia =
                recargoPorAtrasoEnRecoger.getRecargoPorAtrasoEnRecoger(fechaSalida,fechaRecogida);
        return ResponseEntity.ok(recargoPorRecogidaTardia);
    }

    @GetMapping("/getRecargoPorAntiguedad")
    public ResponseEntity<Integer> getRecargoPorAntiguedadYtipo(@RequestParam int anioFabricacion,
                                                                @RequestParam String tipoDeVehiculo) {
        Integer recargoPorAntiguedadResponse =
                recargoPorAntiguedadService.getRecargoPorAntiguedadPorTipo(anioFabricacion,tipoDeVehiculo);
        return ResponseEntity.ok(recargoPorAntiguedadResponse);
    }
}
