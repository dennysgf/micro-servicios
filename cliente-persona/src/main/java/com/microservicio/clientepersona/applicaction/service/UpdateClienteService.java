package com.microservicio.clientepersona.applicaction.service;

import com.microservicio.clientepersona.applicaction.port.in.UpdateClienteUseCase;
import com.microservicio.clientepersona.applicaction.port.out.LoadClientePort;
import com.microservicio.clientepersona.applicaction.port.out.SaveClientePort;
import com.microservicio.clientepersona.domain.exceptions.InvalidClientDataException;
import com.microservicio.clientepersona.domain.exceptions.NotFoundException;
import com.microservicio.clientepersona.domain.model.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UpdateClienteService implements UpdateClienteUseCase {
    private final LoadClientePort loadClientePort;
    private final SaveClientePort saveClientePort;

    public UpdateClienteService(LoadClientePort loadClientePort, SaveClientePort saveClientePort) {
        this.loadClientePort = loadClientePort;
        this.saveClientePort = saveClientePort;
    }

    @Override
    public Cliente actualizar(String clienteId, Cliente nuevoCliente) {
        Cliente existente = loadClientePort.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado con ID: " + clienteId));

        validarCliente(nuevoCliente);

        existente.setNombre(nuevoCliente.getNombre());
        existente.setIdentificacion(nuevoCliente.getIdentificacion());
        existente.setDireccion(nuevoCliente.getDireccion());
        existente.setTelefono(nuevoCliente.getTelefono());
        existente.setContrasena(nuevoCliente.getContrasena());
        existente.setEstado(nuevoCliente.isEstado());

        return saveClientePort.save(existente);
    }

    private void validarCliente(Cliente cliente) {
        if (!StringUtils.hasText(cliente.getNombre())) {
            throw new InvalidClientDataException("El nombre no puede estar vacío.");
        }
        if (!StringUtils.hasText(cliente.getIdentificacion())) {
            throw new InvalidClientDataException("La identificación no puede estar vacía.");
        }
        if (!StringUtils.hasText(cliente.getTelefono())) {
            throw new InvalidClientDataException("El teléfono no puede estar vacío.");
        }
        if (!StringUtils.hasText(cliente.getContrasena())) {
            throw new InvalidClientDataException("La contraseña no puede estar vacía.");
        }
    }
}
