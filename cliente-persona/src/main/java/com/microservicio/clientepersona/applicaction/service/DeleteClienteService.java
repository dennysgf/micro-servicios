package com.microservicio.clientepersona.applicaction.service;

import com.microservicio.clientepersona.applicaction.port.in.DeleteClienteUseCase;
import com.microservicio.clientepersona.applicaction.port.out.DeleteClientePort;
import com.microservicio.clientepersona.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteClienteService implements DeleteClienteUseCase {
    private final DeleteClientePort deleteClientePort;

    public DeleteClienteService(DeleteClientePort deleteClientePort) {
        this.deleteClientePort = deleteClientePort;
    }

    @Override
    public void eliminar(String clienteId) {
        if (!deleteClientePort.existsById(clienteId)) {
            throw new NotFoundException("Cliente no encontrado con ID: " + clienteId);
        }
        deleteClientePort.deleteById(clienteId);
    }
}
