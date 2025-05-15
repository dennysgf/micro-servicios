package com.microservicio.cuentamovimiento.application.port.in;

import com.microservicio.cuentamovimiento.domain.model.Cuenta;

public interface UpdateCuentaUseCase {
    Cuenta actualizar(String numeroCuenta, Cuenta cuentaActualizada);
}
