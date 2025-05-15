package com.microservicio.clientepersona.applicaction.port.in;

import com.microservicio.clientepersona.domain.model.Cliente;

public interface CreateClienteUseCase {
    Cliente crearCliente(Cliente cliente);
}
