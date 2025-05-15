package com.microservicio.cuentamovimiento.application.port.out;

import com.microservicio.cuentamovimiento.domain.model.Movimiento;

import java.util.List;

public interface LoadMovimientosPort {
    List<Movimiento> findMovimientosByNumeroCuenta(String numeroCuenta);

}
