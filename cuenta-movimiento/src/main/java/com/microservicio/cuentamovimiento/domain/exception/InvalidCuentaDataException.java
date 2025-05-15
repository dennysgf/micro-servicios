package com.microservicio.cuentamovimiento.domain.exception;

public class InvalidCuentaDataException extends RuntimeException {
    public InvalidCuentaDataException(String mensaje) {
        super(mensaje);
    }
}
