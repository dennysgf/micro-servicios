package com.microservicio.clientepersona.applicaction.port.out;

public interface DeleteClientePort {
    void deleteById(String clienteId);
    boolean existsById(String clienteId);
}
