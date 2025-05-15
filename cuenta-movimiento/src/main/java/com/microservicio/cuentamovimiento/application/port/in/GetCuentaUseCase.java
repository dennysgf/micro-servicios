package com.microservicio.cuentamovimiento.application.port.in;

import com.microservicio.cuentamovimiento.domain.model.Cuenta;

import java.util.Optional;

public interface GetCuentaUseCase {
    Optional<Cuenta> obtenerPorNumero(String numeroCuenta);
}
