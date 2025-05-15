package com.microservicio.cuentamovimiento.application.port.out;

import com.microservicio.cuentamovimiento.domain.model.Cuenta;

import java.util.Optional;

public interface LoadCuentaPort {
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    boolean existsByNumeroCuenta(String numeroCuenta);
}
