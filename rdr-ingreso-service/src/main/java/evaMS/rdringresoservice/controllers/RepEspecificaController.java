package evaMS.rdringresoservice.controllers;


import evaMS.rdringresoservice.entities.RepEspecificaEntity;
import evaMS.rdringresoservice.requests.BorrarReparacionEspecificaRequest;
import evaMS.rdringresoservice.services.RepEspecificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repEspecifica")
public class RepEspecificaController {
    @Autowired
    RepEspecificaService repEspecificaService;



    @GetMapping("/")
    public ResponseEntity<List<RepEspecificaEntity>> listAllRepsecificas() {
        List<RepEspecificaEntity> tiposDeRep = repEspecificaService.getAllReparacionesEspecificas();
        return ResponseEntity.ok(tiposDeRep);
    }

    @PostMapping("/")
    public ResponseEntity<RepEspecificaEntity> saveRepEspecifica(@RequestBody RepEspecificaEntity tipoDeRep) {
        RepEspecificaEntity tipoDeRepNew = repEspecificaService.saveTipoDeRep(tipoDeRep);
        return ResponseEntity.ok(tipoDeRepNew);
    }
    @PutMapping("/")
    public ResponseEntity<RepEspecificaEntity> updateRepEspecifica(@RequestBody RepEspecificaEntity repEspecifica){
        RepEspecificaEntity repEspecificaUpdated = repEspecificaService.updateRepEspecifica(repEspecifica);
        return ResponseEntity.ok(repEspecificaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRepEspecificaById(@PathVariable Long id) throws Exception {
        var isDeleted= repEspecificaService.deleteRepEspecifica(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByIdIngreso/{id}")
    public ResponseEntity<List<RepEspecificaEntity>> getRepsEspecificasByIdIngreso(@PathVariable int id) {
        List<RepEspecificaEntity> reps = repEspecificaService.getRepEspecificaByIdIngreso(id);
        return ResponseEntity.ok(reps);
    }

    @GetMapping("/getMontoTotalDelIngreso/{id}")
    public ResponseEntity<Integer> getMontoTotalDelIngreso(@PathVariable int id) {
        Integer response = repEspecificaService.getMontoTotalDelIngreso(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/borrarByNombreRepIdIngreso")
    public ResponseEntity<String> deleteRepEspecificaByNombreYId(@RequestBody BorrarReparacionEspecificaRequest request) throws Exception {
        return ResponseEntity.ok(repEspecificaService.deleteRepEspecificaSegunNombreDeLaRep(request));
    }



}
