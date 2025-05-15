package com.microservicio.cuentamovimiento.application.port.out;

import com.microservicio.cuentamovimiento.domain.model.Cuenta;

import java.util.List;

public interface ListAllCuentasPort {
    List<Cuenta> findAll();
}
