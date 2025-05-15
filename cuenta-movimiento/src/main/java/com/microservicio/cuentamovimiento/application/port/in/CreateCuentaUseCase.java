package com.microservicio.cuentamovimiento.application.port.in;

import com.microservicio.cuentamovimiento.domain.model.Cuenta;

public interface CreateCuentaUseCase {
    Cuenta crearCuenta(Cuenta cuenta);
}
