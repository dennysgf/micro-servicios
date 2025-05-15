package com.microservicio.cuentamovimiento.application.port.out;

import com.microservicio.cuentamovimiento.domain.model.Movimiento;

public interface SaveMovimientoPort {
    Movimiento save(Movimiento movimiento);
}
