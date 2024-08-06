
package evaMS.ingresoservice.controllers;


import evaMS.ingresoservice.dto.ReporteReparacionCompleta;
import evaMS.ingresoservice.entities.IngresoARepEntity;
import evaMS.ingresoservice.request.CalcularCostoFinalRequest;
import evaMS.ingresoservice.services.GestionIngresoService;
import evaMS.ingresoservice.services.IngresoARepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresoAReparacion")

public class IngresoARepController {
    @Autowired
    IngresoARepService ingresoARepService;

    @Autowired
    GestionIngresoService gestionIngresoService;

    @GetMapping("/")
    public ResponseEntity<List<IngresoARepEntity>> listReparaciones() {
        List<IngresoARepEntity> reparaciones = ingresoARepService.getReparaciones();
        return ResponseEntity.ok(reparaciones);
    }

    @PostMapping("/")
    public ResponseEntity<IngresoARepEntity> saveReparaciones(@RequestBody IngresoARepEntity reparacion) {
        IngresoARepEntity newReparacion = ingresoARepService.saveReparacion(reparacion);
        return ResponseEntity.ok(newReparacion);
    }





    @PutMapping("/")
    public ResponseEntity<IngresoARepEntity> updateIngresoARep(@RequestBody IngresoARepEntity employee){
        IngresoARepEntity ingresoARepUpdated = ingresoARepService.updateIngresoARep(employee);
        return ResponseEntity.ok(ingresoARepUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteIngresoARepById(@PathVariable Long id) throws Exception {
        var isDeleted= ingresoARepService.deleteIngresoARep(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<IngresoARepEntity>> getReparacionesByIdVehiculo(@PathVariable int id) {
        List<IngresoARepEntity> reparaciones = ingresoARepService.getReparacionesByIdVehiculo(id);
        return ResponseEntity.ok(reparaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoARepEntity> getIngresoARepById(@PathVariable Long id) {
        IngresoARepEntity ingresoARepEntity= ingresoARepService.getIngresoARepById(id);
        return ResponseEntity.ok(ingresoARepEntity);
    }

    @GetMapping("/getNumeroDeReps")
    public ResponseEntity<Integer> getNumeroDeReparaciones(@RequestBody IngresoARepEntity ingresoARep){
        return ResponseEntity.ok(ingresoARepService.getNumeroDeReparacionesDeVehiculo(1,ingresoARep.getFechaRecogida()));
    }
    @PutMapping("/pasoPorCaja")
    public ResponseEntity<IngresoARepEntity> pasoPorCaja(@RequestBody CalcularCostoFinalRequest request) throws Exception {
        IngresoARepEntity response = gestionIngresoService.calcularCostosPreciosFinales(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getTotalDeIngresosARep")
    public ResponseEntity<Integer> getTotalDeIngresosARep() {
        return ResponseEntity.ok( ingresoARepService.getTotalIngresosARep());
    }

    @GetMapping("/getReportesTodosLosDatos")
    public ResponseEntity<List<ReporteReparacionCompleta>> getReportesDeReparacionesCompletas() {
        return ResponseEntity.ok( gestionIngresoService.getTodasLasReparacionesConTodosLosDatos());
    }
}

