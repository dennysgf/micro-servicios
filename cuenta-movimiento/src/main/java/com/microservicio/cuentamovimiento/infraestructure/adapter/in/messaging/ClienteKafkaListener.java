package com.microservicio.cuentamovimiento.infraestructure.adapter.in.messaging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteKafkaListener {
    private final ObjectMapper objectMapper;

    public ClienteKafkaListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "cliente-creado-topic", groupId = "cuenta-movimiento-group")
    public void recibirClienteCreado(String mensajeJson) {
        try {
            JsonNode json = objectMapper.readTree(mensajeJson);
            String clienteId = json.get("clienteId").asText();
            String nombre = json.get("nombre").asText();

            System.out.println("Cliente recibido:");
            System.out.println("ID: " + clienteId + ", Nombre: " + nombre);

        } catch (Exception e) {
            System.err.println("Error procesando mensaje : " + e.getMessage());
        }
    }
}
