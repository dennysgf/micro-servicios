package com.microservicio.clientepersona.applicaction.service;

import com.microservicio.clientepersona.applicaction.port.in.CreateClienteUseCase;
import com.microservicio.clientepersona.applicaction.port.out.LoadClientePort;
import com.microservicio.clientepersona.applicaction.port.out.SaveClientePort;
import com.microservicio.clientepersona.domain.exceptions.InvalidClientDataException;
import com.microservicio.clientepersona.domain.model.Cliente;
import com.microservicio.clientepersona.infraestructure.adapter.out.messaging.ClienteKafkaProducer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CreateClienteService  implements CreateClienteUseCase {
    private final SaveClientePort saveClientePort;
    private final LoadClientePort loadClientePort;
    private final ClienteKafkaProducer kafkaProducer;

    public CreateClienteService(SaveClientePort saveClientePort, LoadClientePort loadClientePort, ClienteKafkaProducer kafkaProducer) {
        this.saveClientePort = saveClientePort;
        this.loadClientePort = loadClientePort;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        validarCliente(cliente);
        kafkaProducer.enviarClienteCreado(cliente);
        return saveClientePort.save(cliente);
    }

    private void validarCliente(Cliente cliente) {
        if (!StringUtils.hasText(cliente.getNombre()) || !cliente.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new InvalidClientDataException("El nombre solo debe contener letras y espacios.");
        }

        if (!StringUtils.hasText(cliente.getIdentificacion())
                || !cliente.getIdentificacion().matches("^[0-9]{10}$")) {
            throw new InvalidClientDataException("La identificación debe contener exactamente 10 dígitos numéricos.");
        }

        if (loadClientePort.findById(cliente.getClienteId()).isPresent()) {
            throw new InvalidClientDataException("Ya existe un cliente con ese ID.");
        }

        if (!StringUtils.hasText(cliente.getContrasena())) {
            throw new InvalidClientDataException("La contraseña no puede estar vacía.");
        }
    }
}
