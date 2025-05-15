package com.microservicio.cuentamovimiento.infraestructure.adapter.in.web;


import com.microservicio.cuentamovimiento.application.dto.EstadoCuentaDTO;
import com.microservicio.cuentamovimiento.application.port.in.ReporteEstadoCuentaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteEstadoCuentaUseCase reporteEstadoCuentaUseCase;

    public ReporteController(ReporteEstadoCuentaUseCase reporteEstadoCuentaUseCase) {
        this.reporteEstadoCuentaUseCase = reporteEstadoCuentaUseCase;
    }

    @Operation(summary = "Generar reporte de estado de cuenta", description = "Devuelve el estado de cuenta de todas las cuentas asociadas a un cliente dentro de un rango de fechas. Incluye los saldos y movimientos realizados.")
    @ApiResponse(responseCode = "200", description = "Reporte generado correctamente")
    @GetMapping
    public ResponseEntity<List<EstadoCuentaDTO>> generarReporte(
            @RequestParam String clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        List<EstadoCuentaDTO> resultado = reporteEstadoCuentaUseCase.generarReporte(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(resultado);
    }

}
