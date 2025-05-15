package com.microservicio.clientepersona.applicaction.service;

import com.microservicio.clientepersona.applicaction.port.in.GetClienteUseCase;
import com.microservicio.clientepersona.applicaction.port.out.LoadClientePort;
import com.microservicio.clientepersona.domain.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetClienteService implements GetClienteUseCase {
    private final LoadClientePort loadClientePort;

    public GetClienteService(LoadClientePort loadClientePort) {
        this.loadClientePort = loadClientePort;
    }

    @Override
    public Optional<Cliente> obtenerPorId(String clienteId) {
        return loadClientePort.findById(clienteId);
    }
}
