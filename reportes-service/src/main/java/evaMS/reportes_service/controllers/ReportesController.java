
package evaMS.reportes_service.controllers;


import evaMS.reportes_service.dto.ReporteTresUltimosMeses;
import evaMS.reportes_service.requests.ReporteTresUltimosMesRequest;
import evaMS.reportes_service.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReportesController {
    @Autowired
    ReportesService reportesService;

    @GetMapping("/getReporteTresUltimosMeses")
    public ResponseEntity<List<ReporteTresUltimosMeses>> getReportesTresUltimosMeses(@RequestBody ReporteTresUltimosMesRequest request) {


        return ResponseEntity.ok(reportesService.getReportesXTipoReparacionTresUltimosMeses(request));
    }



}

