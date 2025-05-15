package com.microservicio.cuentamovimiento.application.service;

import com.microservicio.cuentamovimiento.application.port.out.LoadCuentaPort;
import com.microservicio.cuentamovimiento.application.port.out.SaveCuentaPort;
import com.microservicio.cuentamovimiento.application.port.out.SaveMovimientoPort;
import com.microservicio.cuentamovimiento.domain.exception.SaldoInsuficienteException;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import com.microservicio.cuentamovimiento.domain.model.Movimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class RegistrarMovimientoServiceTest {

    private LoadCuentaPort loadCuentaPort;
    private SaveCuentaPort saveCuentaPort;
    private SaveMovimientoPort saveMovimientoPort;
    private RegistrarMovimientoService service;

    @BeforeEach
    void setUp() {
        loadCuentaPort = mock(LoadCuentaPort.class);
        saveCuentaPort = mock(SaveCuentaPort.class);
        saveMovimientoPort = mock(SaveMovimientoPort.class);

        service = new RegistrarMovimientoService(loadCuentaPort, saveCuentaPort, saveMovimientoPort);
    }

    @Test
    void registrarDeposito_DeberiaActualizarSaldoYGuardar() {
        Cuenta cuenta = new Cuenta("123", "Ahorro", 1000, true, "CL001");
        Movimiento mov = new Movimiento(null, null, "DEPOSITO", 500, 0, "123");

        when(loadCuentaPort.findByNumeroCuenta("123")).thenReturn(Optional.of(cuenta));
        when(saveMovimientoPort.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Movimiento resultado = service.registrarMovimiento(mov);

        assertEquals(1500, resultado.getSaldo());
        verify(saveCuentaPort).save(any());
        verify(saveMovimientoPort).save(any());
    }

    @Test
    void registrarRetiro_ConSaldoSuficiente_DeberiaActualizarSaldo() {
        Cuenta cuenta = new Cuenta("123", "Corriente", 1000, true, "CL001");
        Movimiento mov = new Movimiento(null, null, "RETIRO", 300, 0, "123");

        when(loadCuentaPort.findByNumeroCuenta("123")).thenReturn(Optional.of(cuenta));
        when(saveMovimientoPort.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Movimiento resultado = service.registrarMovimiento(mov);

        assertEquals(700, resultado.getSaldo());
    }

    @Test
    void registrarRetiro_SinSaldo_DeberiaLanzarExcepcion() {
        Cuenta cuenta = new Cuenta("123", "Ahorro", 100, true, "CL001");
        Movimiento mov = new Movimiento(null, null, "RETIRO", 200, 0, "123");

        when(loadCuentaPort.findByNumeroCuenta("123")).thenReturn(Optional.of(cuenta));

        SaldoInsuficienteException ex = assertThrows(
                SaldoInsuficienteException.class,
                () -> service.registrarMovimiento(mov)
        );

        assertEquals("Saldo no disponible", ex.getMessage());
    }
}
