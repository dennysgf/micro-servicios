package com.microservicio.cuentamovimiento.infraestructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, String> {
    List<MovimientoEntity> findByNumeroCuentaOrderByFechaDesc(String numeroCuenta);
}
