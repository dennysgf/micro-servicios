package com.microservicio.clientepersona.applicaction.port.in;

import com.microservicio.clientepersona.domain.model.Cliente;

public interface UpdateClienteUseCase {
    Cliente actualizar(String clienteId, Cliente cliente);
}
