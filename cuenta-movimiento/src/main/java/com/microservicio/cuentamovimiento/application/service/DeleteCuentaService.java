package com.microservicio.cuentamovimiento.application.service;

import com.microservicio.cuentamovimiento.application.port.in.DeleteCuentaUseCase;
import com.microservicio.cuentamovimiento.application.port.out.DeleteCuentaPort;
import com.microservicio.cuentamovimiento.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteCuentaService implements DeleteCuentaUseCase {
    private final DeleteCuentaPort deleteCuentaPort;

    public DeleteCuentaService(DeleteCuentaPort deleteCuentaPort) {
        this.deleteCuentaPort = deleteCuentaPort;
    }

    @Override
    public void eliminar(String numeroCuenta) {
        if (!deleteCuentaPort.existsByNumeroCuenta(numeroCuenta)) {
            throw new NotFoundException("Cuenta no encontrada con número: " + numeroCuenta);
        }
        deleteCuentaPort.deleteByNumeroCuenta(numeroCuenta);
    }
}
