package com.microservicio.cuentamovimiento.domain.exception;

public class CuentaYaExisteException extends RuntimeException {
    public CuentaYaExisteException(String mensaje) {
        super(mensaje);
    }
}
