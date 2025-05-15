package com.microservicio.clientepersona.domain.exceptions;

public class InvalidClientDataException extends RuntimeException {
    public InvalidClientDataException(String mensaje) {
        super(mensaje);
    }
}
