package com.microservicio.clientepersona.infraestructure.adapter.out.persistence;

import com.microservicio.clientepersona.applicaction.port.out.DeleteClientePort;
import com.microservicio.clientepersona.applicaction.port.out.ListAllClientesPort;
import com.microservicio.clientepersona.applicaction.port.out.LoadClientePort;
import com.microservicio.clientepersona.applicaction.port.out.SaveClientePort;
import com.microservicio.clientepersona.domain.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientePersistenceAdapter implements SaveClientePort, LoadClientePort, ListAllClientesPort, DeleteClientePort {
    private final ClienteRepository clienteRepository;

    public ClientePersistenceAdapter(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = mapToEntity(cliente);
        ClienteEntity saved = clienteRepository.save(entity);
        return mapToDomain(saved);
    }

    private ClienteEntity mapToEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getClienteId(),
                cliente.getNombre(),
                cliente.getIdentificacion(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getContrasena(),
                cliente.isEstado()
        );
    }

    private Cliente mapToDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getNombre(),
                entity.getIdentificacion(),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getClienteId(),
                entity.getContrasena(),
                entity.isEstado()
        );
    }
    @Override
    public Optional<Cliente> findById(String clienteId) {
        return clienteRepository.findById(clienteId)
                .map(this::mapToDomain);
    }
    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .toList();
    }
    @Override
    public void deleteById(String clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    @Override
    public boolean existsById(String clienteId) {
        return clienteRepository.existsById(clienteId);
    }
}
