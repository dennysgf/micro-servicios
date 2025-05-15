package com.microservicio.cuentamovimiento.application.service;

import com.microservicio.cuentamovimiento.application.port.in.CreateCuentaUseCase;
import com.microservicio.cuentamovimiento.application.port.out.LoadCuentaPort;
import com.microservicio.cuentamovimiento.application.port.out.SaveCuentaPort;
import com.microservicio.cuentamovimiento.domain.exception.CuentaYaExisteException;
import com.microservicio.cuentamovimiento.domain.exception.InvalidCuentaDataException;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CreateCuentaService implements CreateCuentaUseCase {
    private final SaveCuentaPort saveCuentaPort;
    private final LoadCuentaPort loadCuentaPort;

    public CreateCuentaService(SaveCuentaPort saveCuentaPort, LoadCuentaPort loadCuentaPort) {
        this.saveCuentaPort = saveCuentaPort;
        this.loadCuentaPort = loadCuentaPort;
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        validarCuenta(cuenta);

        if (loadCuentaPort.existsByNumeroCuenta(cuenta.getNumeroCuenta())) {
            throw new CuentaYaExisteException("Ya existe una cuenta con ese número.");
        }

        return saveCuentaPort.save(cuenta);
    }

    private void validarCuenta(Cuenta cuenta) {
        if (!StringUtils.hasText(cuenta.getNumeroCuenta())) {
            throw new InvalidCuentaDataException("El número de cuenta no puede estar vacío.");
        }

        if (!StringUtils.hasText(cuenta.getTipoCuenta())) {
            throw new InvalidCuentaDataException("El tipo de cuenta es obligatorio.");
        }

        if (!StringUtils.hasText(cuenta.getClienteId())) {
            throw new InvalidCuentaDataException("El ID del cliente es obligatorio.");
        }

        if (cuenta.getSaldoInicial() < 0) {
            throw new InvalidCuentaDataException("El saldo inicial no puede ser negativo.");
        }
    }
}
