package com.microservicio.cuentamovimiento.infraestructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<CuentaEntity, String> {
    boolean existsByNumeroCuenta(String numeroCuenta);
}