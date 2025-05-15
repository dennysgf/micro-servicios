package com.microservicio.clientepersona.service;

import com.microservicio.clientepersona.applicaction.port.out.LoadClientePort;
import com.microservicio.clientepersona.applicaction.port.out.SaveClientePort;
import com.microservicio.clientepersona.applicaction.service.CreateClienteService;
import com.microservicio.clientepersona.domain.exceptions.InvalidClientDataException;
import com.microservicio.clientepersona.domain.model.Cliente;
import com.microservicio.clientepersona.infraestructure.adapter.out.messaging.ClienteKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class CreateClienteServiceTest {
    private SaveClientePort saveClientePort;
    private CreateClienteService createClienteService;
    private LoadClientePort loadClientePort;
    private ClienteKafkaProducer kafkaProducer;

    @BeforeEach
    void setUp() {
        saveClientePort = mock(SaveClientePort.class);
        loadClientePort = mock(LoadClientePort.class);
        kafkaProducer = mock(ClienteKafkaProducer.class);
        createClienteService = new CreateClienteService(saveClientePort, loadClientePort,kafkaProducer);
    }

    @Test
    void crearCliente_Valido_GuardarYRetornarCliente() {
        Cliente cliente = new Cliente("Juan", "1234567890", "Dirección", "0999999999", "C001", "clave123", true);

        when(saveClientePort.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = createClienteService.crearCliente(cliente);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(saveClientePort, times(1)).save(cliente);
    }

    @Test
    void crearCliente_NombreVacio_LanzarExcepcion() {
        Cliente cliente = new Cliente("", "1234567890", "Dirección", "0999999999", "C001", "clave123", true);

        assertThrows(InvalidClientDataException.class, () -> createClienteService.crearCliente(cliente));
        verify(saveClientePort, never()).save(any());
    }
}
