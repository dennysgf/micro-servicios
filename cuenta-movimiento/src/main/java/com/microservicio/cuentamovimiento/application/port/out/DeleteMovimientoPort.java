package com.microservicio.cuentamovimiento.application.port.out;

public interface DeleteMovimientoPort {
    void deleteById(String id);
    boolean existsById(String id);
}
