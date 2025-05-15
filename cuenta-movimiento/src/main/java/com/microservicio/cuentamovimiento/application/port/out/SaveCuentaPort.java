package com.microservicio.cuentamovimiento.application.port.out;

import com.microservicio.cuentamovimiento.domain.model.Cuenta;

public interface SaveCuentaPort {
    Cuenta save(Cuenta cuenta);
}
