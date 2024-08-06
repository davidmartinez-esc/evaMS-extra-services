package evaMS.bonos_aplicados_service.controllers;


import evaMS.bonos_aplicados_service.entities.BonoAplicadoEntity;
import evaMS.bonos_aplicados_service.requests.NuevoBonoAplicadoRequest;
import evaMS.bonos_aplicados_service.services.BonoAplicadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bonoAplicado")

public class BonoAplicadoController {
    @Autowired
    BonoAplicadoService bonoAplicadoService;

    @GetMapping("/")
    public ResponseEntity<List<BonoAplicadoEntity>> listBonosAplicados() {
        List<BonoAplicadoEntity> bonoAplicado = bonoAplicadoService.getBonosAplicados();
        return ResponseEntity.ok(bonoAplicado);
    }

    @PostMapping("/save")
    public ResponseEntity<BonoAplicadoEntity> saveBonoAplicado(@RequestBody BonoAplicadoEntity bonoAplicado) {
        BonoAplicadoEntity newBonoAplicado = bonoAplicadoService.saveBonoAplicado(bonoAplicado);
        return ResponseEntity.ok(newBonoAplicado);
    }
    @PutMapping("/")
    public ResponseEntity<BonoAplicadoEntity> updateBonoAplicado(@RequestBody BonoAplicadoEntity bonoAplicado){
        BonoAplicadoEntity bonoAplicadoUpdated = bonoAplicadoService.updateBonoAplicado(bonoAplicado);
        return ResponseEntity.ok(bonoAplicadoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBonoAplicadoById(@PathVariable Long id) throws Exception {
        var isDeleted= bonoAplicadoService.deleteBonoAplicado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getNumeroDeBonos")
    public ResponseEntity<Integer> getNumeroDeBonosAplicadosPorMesMarca(@RequestParam int mes,@RequestParam int anio,@RequestParam String marca) {
        Integer numeroDeBonosAplicados = bonoAplicadoService.getNumeroDeBonosAplicadosPorMesMarca(mes,anio,marca);
        return ResponseEntity.ok(numeroDeBonosAplicados);
    }

    @PostMapping("/guardarNuevoBonoAplicado")
    public ResponseEntity<NuevoBonoAplicadoRequest> saveBonoAplicado(@RequestBody NuevoBonoAplicadoRequest bonoAplicado) {

        return ResponseEntity.ok(bonoAplicadoService.saveNuevoBonoAplicado(bonoAplicado));
    }


}
