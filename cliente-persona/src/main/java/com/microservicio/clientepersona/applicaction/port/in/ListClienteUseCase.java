package com.microservicio.clientepersona.applicaction.port.in;

import com.microservicio.clientepersona.domain.model.Cliente;

import java.util.List;

public interface ListClienteUseCase {
    List<Cliente> obtenerTodos();
}
