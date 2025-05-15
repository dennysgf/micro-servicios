package com.microservicio.cuentamovimiento.application.service;

import com.microservicio.cuentamovimiento.application.port.in.RegistrarMovimientoUseCase;
import com.microservicio.cuentamovimiento.application.port.out.LoadCuentaPort;
import com.microservicio.cuentamovimiento.application.port.out.SaveCuentaPort;
import com.microservicio.cuentamovimiento.application.port.out.SaveMovimientoPort;
import com.microservicio.cuentamovimiento.domain.exception.NotFoundException;
import com.microservicio.cuentamovimiento.domain.exception.SaldoInsuficienteException;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import com.microservicio.cuentamovimiento.domain.model.Movimiento;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrarMovimientoService implements RegistrarMovimientoUseCase {
    private final LoadCuentaPort loadCuentaPort;
    private final SaveCuentaPort saveCuentaPort;
    private final SaveMovimientoPort saveMovimientoPort;

    public RegistrarMovimientoService(LoadCuentaPort loadCuentaPort, SaveCuentaPort saveCuentaPort, SaveMovimientoPort saveMovimientoPort) {
        this.loadCuentaPort = loadCuentaPort;
        this.saveCuentaPort = saveCuentaPort;
        this.saveMovimientoPort = saveMovimientoPort;
    }

    @Override
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = loadCuentaPort.findByNumeroCuenta(movimiento.getNumeroCuenta())
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada."));

        double saldoActual = cuenta.getSaldoInicial();
        double nuevoSaldo;

        if (movimiento.getTipoMovimiento().equalsIgnoreCase("RETIRO")) {
            if (saldoActual < movimiento.getValor()) {
                throw new SaldoInsuficienteException("Saldo no disponible");
            }
            nuevoSaldo = saldoActual - movimiento.getValor();
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("DEPOSITO")) {
            nuevoSaldo = saldoActual + movimiento.getValor();
        } else {
            throw new IllegalArgumentException("Tipo de movimiento no válido");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        saveCuentaPort.save(cuenta);

        Movimiento nuevoMovimiento = new Movimiento();
        nuevoMovimiento.setId(UUID.randomUUID().toString());
        nuevoMovimiento.setFecha(LocalDateTime.now());
        nuevoMovimiento.setTipoMovimiento(movimiento.getTipoMovimiento());
        nuevoMovimiento.setValor(movimiento.getValor());
        nuevoMovimiento.setSaldo(nuevoSaldo);
        nuevoMovimiento.setNumeroCuenta(cuenta.getNumeroCuenta());

        return saveMovimientoPort.save(nuevoMovimiento);
    }
}
