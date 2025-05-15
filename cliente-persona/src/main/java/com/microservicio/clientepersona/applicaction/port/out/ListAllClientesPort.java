package com.microservicio.clientepersona.applicaction.port.out;

import com.microservicio.clientepersona.domain.model.Cliente;

import java.util.List;

public interface ListAllClientesPort {
    List<Cliente> findAll();
}
