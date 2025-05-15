package com.microservicio.cuentamovimiento.application.service;

import com.microservicio.cuentamovimiento.application.port.in.ListCuentaUseCase;
import com.microservicio.cuentamovimiento.application.port.out.ListAllCuentasPort;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCuentaService implements ListCuentaUseCase {

    private final ListAllCuentasPort listAllCuentasPort;

    public ListCuentaService(ListAllCuentasPort listAllCuentasPort) {
        this.listAllCuentasPort = listAllCuentasPort;
    }

    @Override
    public List<Cuenta> listarTodas() {
        return listAllCuentasPort.findAll();
    }
}
