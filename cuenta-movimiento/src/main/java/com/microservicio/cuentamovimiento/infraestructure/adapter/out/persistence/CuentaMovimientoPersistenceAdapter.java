package com.microservicio.cuentamovimiento.infraestructure.adapter.out.persistence;

import com.microservicio.cuentamovimiento.application.port.out.*;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import com.microservicio.cuentamovimiento.domain.model.Movimiento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CuentaMovimientoPersistenceAdapter implements
        SaveCuentaPort,
        LoadCuentaPort,
        SaveMovimientoPort,
        LoadMovimientosPort,
        DeleteCuentaPort,
        ListAllCuentasPort,
        DeleteMovimientoPort {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public CuentaMovimientoPersistenceAdapter(CuentaRepository cuentaRepository, MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }


    @Override
    public Cuenta save(Cuenta cuenta) {
        CuentaEntity entity = mapToCuentaEntity(cuenta);
        CuentaEntity saved = cuentaRepository.save(entity);
        return mapToCuentaDomain(saved);
    }

    @Override
    public List<Movimiento> findMovimientosByNumeroCuenta(String numeroCuenta) {
        return movimientoRepository.findByNumeroCuentaOrderByFechaDesc(numeroCuenta)
                .stream()
                .map(this::mapToMovimientoDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll()
                .stream()
                .map(this::mapToCuentaDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta).map(this::mapToCuentaDomain);
    }

    @Override
    public boolean existsByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.existsByNumeroCuenta(numeroCuenta);
    }

    @Override
    public void deleteByNumeroCuenta(String numeroCuenta) {
        cuentaRepository.deleteById(numeroCuenta);
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        MovimientoEntity entity = mapToMovimientoEntity(movimiento);
        MovimientoEntity saved = movimientoRepository.save(entity);
        return mapToMovimientoDomain(saved);
    }



    @Override
    public void deleteById(String id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return movimientoRepository.existsById(id);
    }


    private CuentaEntity mapToCuentaEntity(Cuenta c) {
        return new CuentaEntity(
                c.getNumeroCuenta(),
                c.getTipoCuenta(),
                c.getSaldoInicial(),
                c.isEstado(),
                c.getClienteId()
        );
    }

    private Cuenta mapToCuentaDomain(CuentaEntity e) {
        return new Cuenta(
                e.getNumeroCuenta(),
                e.getTipoCuenta(),
                e.getSaldoInicial(),
                e.isEstado(),
                e.getClienteId()
        );
    }

    private MovimientoEntity mapToMovimientoEntity(Movimiento m) {
        return new MovimientoEntity(
                m.getId(),
                m.getFecha(),
                m.getTipoMovimiento(),
                m.getValor(),
                m.getSaldo(),
                m.getNumeroCuenta()
        );
    }

    private Movimiento mapToMovimientoDomain(MovimientoEntity e) {
        return new Movimiento(
                e.getId(),
                e.getFecha(),
                e.getTipoMovimiento(),
                e.getValor(),
                e.getSaldo(),
                e.getNumeroCuenta()
        );
    }
}
