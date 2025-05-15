package com.microservicio.cuentamovimiento.application.service;

import com.microservicio.cuentamovimiento.application.dto.EstadoCuentaDTO;
import com.microservicio.cuentamovimiento.application.dto.MovimientoDTO;
import com.microservicio.cuentamovimiento.application.port.in.ReporteEstadoCuentaUseCase;
import com.microservicio.cuentamovimiento.application.port.out.ListAllCuentasPort;
import com.microservicio.cuentamovimiento.application.port.out.LoadCuentaPort;
import com.microservicio.cuentamovimiento.application.port.out.LoadMovimientosPort;
import com.microservicio.cuentamovimiento.domain.model.Movimiento;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteEstadoCuentaService implements ReporteEstadoCuentaUseCase {

    private final ListAllCuentasPort listAllCuentasPort;
    private final LoadMovimientosPort loadMovimientosPort;

    public ReporteEstadoCuentaService(ListAllCuentasPort listAllCuentasPort, LoadMovimientosPort loadMovimientosPort) {
        this.listAllCuentasPort = listAllCuentasPort;
        this.loadMovimientosPort = loadMovimientosPort;
    }

    @Override
    public List<EstadoCuentaDTO> generarReporte(String clienteId, LocalDate fechaInicio, LocalDate fechaFin) {

        return listAllCuentasPort.findAll().stream()
                .filter(cuenta -> cuenta.getClienteId().equals(clienteId))
                .map(cuenta -> {
                    List<MovimientoDTO> movimientos = loadMovimientosPort
                            .findMovimientosByNumeroCuenta(cuenta.getNumeroCuenta())
                            .stream()
                            .filter(mov -> {
                                LocalDate fecha = mov.getFecha().toLocalDate();
                                return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
                            })
                            .map(this::mapToMovimientoDTO)
                            .collect(Collectors.toList());

                    return new EstadoCuentaDTO(
                            cuenta.getNumeroCuenta(),
                            cuenta.getTipoCuenta(),
                            cuenta.getSaldoInicial(),
                            cuenta.isEstado(),
                            movimientos
                    );
                })
                .collect(Collectors.toList());
    }

    private MovimientoDTO mapToMovimientoDTO(Movimiento m) {
        return new MovimientoDTO(
                m.getFecha(),
                m.getTipoMovimiento(),
                m.getValor(),
                m.getSaldo()
        );
    }
}
