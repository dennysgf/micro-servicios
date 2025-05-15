package com.microservicio.cuentamovimiento.application.port.in;

import com.microservicio.cuentamovimiento.domain.model.Movimiento;

public interface RegistrarMovimientoUseCase {
    Movimiento registrarMovimiento(Movimiento movimiento);
}
