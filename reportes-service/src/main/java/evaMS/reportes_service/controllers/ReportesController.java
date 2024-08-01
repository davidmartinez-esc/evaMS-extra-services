
package evaMS.reportes_service.controllers;


import evaMS.reportes_service.dto.PatenteYTipo;
import evaMS.reportes_service.dto.ReporteRepXTipoVehiculo;
import evaMS.reportes_service.dto.ReporteTresUltimosMeses;
import evaMS.reportes_service.requests.ReporteTresUltimosMesRequest;
import evaMS.reportes_service.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReportesController {
    @Autowired
    ReportesService reportesService;

    @GetMapping("/getReporteTresUltimosMeses")
    public ResponseEntity<List<ReporteTresUltimosMeses>> getReportesTresUltimosMeses(@RequestParam String mes,@RequestParam String anio) throws ParseException {


        return ResponseEntity.ok(reportesService.getReportesXTipoReparacionTresUltimosMeses(mes,anio));
    }

    @GetMapping("/reporteRepXTipoVehiculo")
    public ResponseEntity<List<ReporteRepXTipoVehiculo>> getReporteReparacionXTipoVehiculo(@RequestParam int mes, @RequestParam int anio) {


        return ResponseEntity.ok(reportesService.getReporteTiposDeRepXTipoVehiculo(mes,anio));
    }



}

