package com.microservicio.cuentamovimiento.infraestructure.adapter.in.web;


import com.microservicio.cuentamovimiento.application.port.in.*;
import com.microservicio.cuentamovimiento.domain.model.Cuenta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CreateCuentaUseCase createCuentaUseCase;
    private final GetCuentaUseCase getCuentaUseCase;
    private final ListCuentaUseCase listCuentaUseCase;
    private final UpdateCuentaUseCase updateCuentaUseCase;
    private final DeleteCuentaUseCase deleteCuentaUseCase;

    public CuentaController(
            CreateCuentaUseCase createCuentaUseCase,
            GetCuentaUseCase getCuentaUseCase,
            ListCuentaUseCase listCuentaUseCase,
            UpdateCuentaUseCase updateCuentaUseCase, DeleteCuentaUseCase deleteCuentaUseCase) {
        this.createCuentaUseCase = createCuentaUseCase;
        this.getCuentaUseCase = getCuentaUseCase;
        this.listCuentaUseCase = listCuentaUseCase;
        this.updateCuentaUseCase = updateCuentaUseCase;
        this.deleteCuentaUseCase = deleteCuentaUseCase;
    }

    @Operation(summary = "Crear una nueva cuenta", description = "Registra una nueva cuenta bancaria asociada a un cliente")
    @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente")
    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@Valid @RequestBody Cuenta cuenta) {
        Cuenta creada = createCuentaUseCase.crearCuenta(cuenta);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener cuenta por número", description = "Devuelve una cuenta específica según el número de cuenta proporcionado")
    @ApiResponse(responseCode = "200", description = "Cuenta encontrada")
    @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    @GetMapping("/{numero}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable String numero) {
        return getCuentaUseCase.obtenerPorNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todas las cuentas", description = "Devuelve una lista con todas las cuentas bancarias registradas")
    @ApiResponse(responseCode = "200", description = "Lista de cuentas obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        List<Cuenta> cuentas = listCuentaUseCase.listarTodas();
        return ResponseEntity.ok(cuentas);
    }

    @Operation(summary = "Actualizar una cuenta", description = "Modifica los datos de una cuenta específica según su número")
    @ApiResponse(responseCode = "200", description = "Cuenta actualizada correctamente")
    @PutMapping("/{numero}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable String numero, @RequestBody Cuenta cuenta) {
        Cuenta actualizada = updateCuentaUseCase.actualizar(numero, cuenta);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Eliminar una cuenta", description = "Elimina una cuenta bancaria a partir de su número de cuenta")
    @ApiResponse(responseCode = "204", description = "Cuenta eliminada exitosamente")
    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable String numero) {
        deleteCuentaUseCase.eliminar(numero);
        return ResponseEntity.noContent().build();
    }

}
