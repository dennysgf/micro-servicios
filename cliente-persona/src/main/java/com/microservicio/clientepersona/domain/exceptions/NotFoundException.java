package com.microservicio.clientepersona.domain.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
