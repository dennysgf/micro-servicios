package com.microservicio.clientepersona.applicaction.port.in;

import com.microservicio.clientepersona.domain.model.Cliente;

import java.util.Optional;

public interface GetClienteUseCase {
    Optional<Cliente> obtenerPorId(String clienteId);
}
