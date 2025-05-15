package com.microservicio.clientepersona.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.clientepersona.domain.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearCliente_DeberiaRetornar201YCliente() throws Exception {
        Cliente cliente = new Cliente(
                "Carlos Pérez", "1122334455", "Av. Quito", "099998877",
                "C999", "clave123", true
        );

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clienteId").value("C999"))
                .andExpect(jsonPath("$.nombre").value("Carlos Pérez"));
    }
}
