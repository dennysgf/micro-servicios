package com.microservicio.cuentamovimiento.application.service;

import com.microservicio.cuentamovimiento.application.port.in.UpdateCuentaUseCase;
import com.microservicio.cuentamovimiento.application.port.out.LoadCuentaPort;
import com.microservicio.cuentamovimiento.application.port.out.SaveCuentaPort;
import com.microservicio.cuentamovimiento.domain.exception.InvalidCuentaDataException;
import com.microservicio.cuentamovimiento.domain.exception.NotFoundException;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UpdateCuentaService implements UpdateCuentaUseCase {

    private final LoadCuentaPort loadCuentaPort;
    private final SaveCuentaPort saveCuentaPort;

    public UpdateCuentaService(LoadCuentaPort loadCuentaPort, SaveCuentaPort saveCuentaPort) {
        this.loadCuentaPort = loadCuentaPort;
        this.saveCuentaPort = saveCuentaPort;
    }

    @Override
    public Cuenta actualizar(String numeroCuenta, Cuenta cuentaNueva) {
        Cuenta cuentaExistente = loadCuentaPort.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada con número: " + numeroCuenta));

        validarCuenta(cuentaNueva);

        cuentaExistente.setTipoCuenta(cuentaNueva.getTipoCuenta());
        cuentaExistente.setSaldoInicial(cuentaNueva.getSaldoInicial());
        cuentaExistente.setEstado(cuentaNueva.isEstado());
        cuentaExistente.setClienteId(cuentaNueva.getClienteId());

        return saveCuentaPort.save(cuentaExistente);
    }

    private void validarCuenta(Cuenta cuenta) {
        if (!StringUtils.hasText(cuenta.getTipoCuenta())) {
            throw new InvalidCuentaDataException("El tipo de cuenta es obligatorio.");
        }

        if (!StringUtils.hasText(cuenta.getClienteId())) {
            throw new InvalidCuentaDataException("El cliente es obligatorio.");
        }

        if (cuenta.getSaldoInicial() < 0) {
            throw new InvalidCuentaDataException("El saldo no puede ser negativo.");
        }
    }
}
