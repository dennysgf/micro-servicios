package com.microservicio.clientepersona.applicaction.service;

import com.microservicio.clientepersona.applicaction.port.in.ListClienteUseCase;
import com.microservicio.clientepersona.applicaction.port.out.ListAllClientesPort;
import com.microservicio.clientepersona.domain.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListClienteService implements ListClienteUseCase {

    private final ListAllClientesPort listAllClientesPort;

    public ListClienteService(ListAllClientesPort listAllClientesPort) {
        this.listAllClientesPort = listAllClientesPort;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return listAllClientesPort.findAll();
    }
}
