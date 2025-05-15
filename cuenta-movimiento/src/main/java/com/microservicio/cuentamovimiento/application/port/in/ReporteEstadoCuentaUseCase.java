package com.microservicio.cuentamovimiento.application.port.in;

import com.microservicio.cuentamovimiento.application.dto.EstadoCuentaDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReporteEstadoCuentaUseCase {
    List<EstadoCuentaDTO> generarReporte(String clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
