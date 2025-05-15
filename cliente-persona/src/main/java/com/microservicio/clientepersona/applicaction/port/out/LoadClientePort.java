package com.microservicio.clientepersona.applicaction.port.out;

import com.microservicio.clientepersona.domain.model.Cliente;

import java.util.Optional;

public interface LoadClientePort {
    Optional<Cliente> findById(String clienteId);
}
