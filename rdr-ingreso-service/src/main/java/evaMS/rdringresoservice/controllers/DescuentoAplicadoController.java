package evaMS.rdringresoservice.controllers;

import evaMS.rdringresoservice.entities.DescuentoAplicadoEntity;
import evaMS.rdringresoservice.services.DescuentoAplicadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/descuentoAplicado")
@CrossOrigin("*")
public class DescuentoAplicadoController {
    @Autowired
    DescuentoAplicadoService descuentoAplicadoService;

    @GetMapping("/")
    public ResponseEntity<List<DescuentoAplicadoEntity>> listDescuentosAplicados() {
        List<DescuentoAplicadoEntity> descuentoAplicado = descuentoAplicadoService.getDescuentosAplicados();
        return ResponseEntity.ok(descuentoAplicado);
    }

    @PostMapping("/")
    public ResponseEntity<DescuentoAplicadoEntity> saveDescuentoAplicado(@RequestBody DescuentoAplicadoEntity descuentoAplicado) {
        DescuentoAplicadoEntity newDescuentoAplicado = descuentoAplicadoService.saveDescuentoAplicado(descuentoAplicado);
        return ResponseEntity.ok(newDescuentoAplicado);
    }
    @PutMapping("/")
    public ResponseEntity<DescuentoAplicadoEntity> updateDescuentoAplicado(@RequestBody DescuentoAplicadoEntity descuentoAplicado){
        DescuentoAplicadoEntity descuentoAplicadoUpdated = descuentoAplicadoService.updateDescuentoAplicado(descuentoAplicado);
        return ResponseEntity.ok(descuentoAplicadoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDescuentoAplicadoById(@PathVariable Long id) throws Exception {
        var isDeleted= descuentoAplicadoService.deleteDescuentoAplicado(id);
        return ResponseEntity.noContent().build();
    }
}
