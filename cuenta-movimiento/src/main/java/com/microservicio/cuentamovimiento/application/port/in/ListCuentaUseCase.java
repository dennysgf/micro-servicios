package com.microservicio.cuentamovimiento.application.port.in;

import com.microservicio.cuentamovimiento.domain.model.Cuenta;

import java.util.List;

public interface ListCuentaUseCase {
    List<Cuenta> listarTodas();
}
