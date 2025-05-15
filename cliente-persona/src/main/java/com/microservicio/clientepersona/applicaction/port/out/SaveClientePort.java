package com.microservicio.clientepersona.applicaction.port.out;

import com.microservicio.clientepersona.domain.model.Cliente;

public interface SaveClientePort {
    Cliente save(Cliente cliente);
}
