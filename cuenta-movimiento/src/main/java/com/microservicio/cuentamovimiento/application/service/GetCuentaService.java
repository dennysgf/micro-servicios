package com.microservicio.cuentamovimiento.application.service;

import com.microservicio.cuentamovimiento.application.port.in.GetCuentaUseCase;
import com.microservicio.cuentamovimiento.application.port.out.LoadCuentaPort;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCuentaService implements GetCuentaUseCase {
    private final LoadCuentaPort loadCuentaPort;

    public GetCuentaService(LoadCuentaPort loadCuentaPort) {
        this.loadCuentaPort = loadCuentaPort;
    }

    @Override
    public Optional<Cuenta> obtenerPorNumero(String numeroCuenta) {
        return loadCuentaPort.findByNumeroCuenta(numeroCuenta);
    }
}
