package com.microservicio.clientepersona.infraestructure.adapter.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.clientepersona.domain.model.Cliente;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ClienteKafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void enviarClienteCreado(Cliente cliente) {
        try {
            String mensaje = objectMapper.writeValueAsString(cliente);
            kafkaTemplate.send("cliente-creado-topic", mensaje);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializando cliente para Kafka", e);
        }
    }
}
