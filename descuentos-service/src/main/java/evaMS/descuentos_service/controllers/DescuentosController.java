package evaMS.descuentos_service.controllers;

import evaMS.descuentos_service.entities.DescuentoPorNRepEntity;
import evaMS.descuentos_service.requests.RequestDescuentoDiaHoraIngreso;
import evaMS.descuentos_service.services.DatosBonoService;
import evaMS.descuentos_service.services.DescuentoIngresoLunesJueves;
import evaMS.descuentos_service.services.DescuentoPorNRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/descuentos")
public class DescuentosController {
    @Autowired
    DatosBonoService datosBonoService;
    @Autowired
    DescuentoPorNRepService descuentoService;

    @Autowired
    DescuentoIngresoLunesJueves descuentoIngresoLunesJueves;



    @GetMapping("/descuentosPorNRep/")
    public ResponseEntity<List<DescuentoPorNRepEntity>> listDescuentoPorNReps() {
        List<DescuentoPorNRepEntity> descuento = descuentoService.getDescuentoPorNReps();
        return ResponseEntity.ok(descuento);
    }

    @PostMapping("/descuentosPorNRep/")
    public ResponseEntity<DescuentoPorNRepEntity> saveDescuentoPorNRep(@RequestBody DescuentoPorNRepEntity descuento) {
        DescuentoPorNRepEntity newDescuentoPorNRep = descuentoService.saveDescuentoPorNRep(descuento);
        return ResponseEntity.ok(newDescuentoPorNRep);
    }
    @PutMapping("/descuentosPorNRep/")
    public ResponseEntity<DescuentoPorNRepEntity> updateDescuentoPorNRep(@RequestBody DescuentoPorNRepEntity descuento){
        DescuentoPorNRepEntity descuentoUpdated = descuentoService.updateDescuentoPorNRep(descuento);
        return ResponseEntity.ok(descuentoUpdated);
    }

    @DeleteMapping("/descuentosPorNRep/{id}")
    public ResponseEntity<Boolean> deleteDescuentoPorNRepById(@PathVariable Long id) throws Exception {
        var isDeleted= descuentoService.deleteDescuentoPorNRep(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/descuentosPorNRep/getDescuentoPorNumeroDeReparaciones")
    public ResponseEntity<Integer> getDescuentoPorNReps(@RequestParam int nReps, @RequestParam String tipoMotor){
        return ResponseEntity.ok(descuentoService.getDescuentoByNRepYTipoDeMotor(nReps,tipoMotor));
    }
    @PostMapping("/getDescuentoPorDiaHoraIngreso")
    public ResponseEntity<Integer> getDescuentoPorDiaHoraIngreso(@RequestBody RequestDescuentoDiaHoraIngreso request){

        return ResponseEntity.ok(descuentoIngresoLunesJueves.getDescuentoPorHoraYdiaIngreso(request.getHoraIngreso()
                ,request.getFechaIngreso()));
    }

    @GetMapping("/descuentoPorBono/getMontoBono")
    public ResponseEntity<Integer> getMontoBono(@RequestParam String marca) {
        Integer response=datosBonoService.getMontoBono(marca);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/descuentoPorBono/getCantidadBonos")
    public ResponseEntity<Integer> getCantidadBonos(@RequestParam String marca) {
        Integer response=datosBonoService.getCantidadDeBonos(marca);
        return ResponseEntity.ok(response);
    }
}
