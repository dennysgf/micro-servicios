package com.microservicio.cuentamovimiento.application.port.out;

public interface DeleteCuentaPort {
    void deleteByNumeroCuenta(String numeroCuenta);
    boolean existsByNumeroCuenta(String numeroCuenta);
}
