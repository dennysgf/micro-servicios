package com.microservicio.cuentamovimiento.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import com.microservicio.cuentamovimiento.domain.model.Movimiento;
import com.microservicio.cuentamovimiento.infraestructure.adapter.out.persistence.CuentaEntity;
import com.microservicio.cuentamovimiento.infraestructure.adapter.out.persistence.CuentaRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class MovimientoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        cuentaRepository.deleteAll();
        cuentaRepository.save(new CuentaEntity("123456", "Ahorro", 1000, true, "CL001"));
    }

    @Test
    void registrarDeposito_Retorno201YActualizarSaldo() throws Exception {
        Movimiento movimiento = new Movimiento();
        movimiento.setNumeroCuenta("123456");
        movimiento.setTipoMovimiento("DEPOSITO");
        movimiento.setValor(500);

        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movimiento)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.saldo").value(1500));
    }

    @Test
    void registrarRetiro_SinSaldo_RetornoError() throws Exception {
        Movimiento movimiento = new Movimiento();
        movimiento.setNumeroCuenta("123456");
        movimiento.setTipoMovimiento("RETIRO");
        movimiento.setValor(2000);

        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movimiento)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Saldo no disponible")));
    }
}
