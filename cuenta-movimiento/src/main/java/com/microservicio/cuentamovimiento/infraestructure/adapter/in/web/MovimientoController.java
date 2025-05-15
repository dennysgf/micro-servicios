package com.microservicio.cuentamovimiento.infraestructure.adapter.in.web;


import com.microservicio.cuentamovimiento.application.port.in.RegistrarMovimientoUseCase;
import com.microservicio.cuentamovimiento.domain.model.Movimiento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final RegistrarMovimientoUseCase registrarMovimientoUseCase;

    public MovimientoController(RegistrarMovimientoUseCase registrarMovimientoUseCase) {
        this.registrarMovimientoUseCase = registrarMovimientoUseCase;
    }

    @Operation(summary = "Registrar un movimiento", description = "Registra un movimiento bancario (DEPÓSITO o RETIRO) y actualiza el saldo de la cuenta correspondiente")
    @ApiResponse(responseCode = "201", description = "Movimiento registrado exitosamente")
    @ApiResponse(responseCode = "400", description = "Saldo no disponible o datos inválidos")
    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@Valid @RequestBody Movimiento movimiento) {
        Movimiento registrado = registrarMovimientoUseCase.registrarMovimiento(movimiento);
        return new ResponseEntity<>(registrado, HttpStatus.CREATED);
    }

}
