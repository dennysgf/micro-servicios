package com.microservicio.clientepersona.infraestructure.adapter.in.web;

import com.microservicio.clientepersona.applicaction.port.in.*;
import com.microservicio.clientepersona.domain.model.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CreateClienteUseCase createClienteUseCase;
    private final GetClienteUseCase getClienteUseCase;
    private final ListClienteUseCase listClienteUseCase;
    private final UpdateClienteUseCase updateClienteUseCase;
    private final DeleteClienteUseCase deleteClienteUseCase;

    public ClienteController(CreateClienteUseCase createClienteUseCase, GetClienteUseCase getClienteUseCase, ListClienteUseCase listClienteUseCase, UpdateClienteUseCase updateClienteUseCase, DeleteClienteUseCase deleteClienteUseCase) {
        this.createClienteUseCase = createClienteUseCase;
        this.getClienteUseCase = getClienteUseCase;
        this.listClienteUseCase = listClienteUseCase;
        this.updateClienteUseCase = updateClienteUseCase;
        this.deleteClienteUseCase = deleteClienteUseCase;
    }

    @Operation(
            summary = "Crear nuevo cliente",
            description = "Registra un nuevo cliente con nombre, dirección, teléfono, contraseña y estado"
    )
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente")
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente) {
        Cliente creado = createClienteUseCase.crearCliente(cliente);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtengo cliente",
            description = "Obtengo el cliente por un id en específico"
    )
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String id) {
        return getClienteUseCase
                .obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Listar todos los clientes",
            description = "Devuelve una lista de todos los clientes registrados en el sistema"
    )
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = listClienteUseCase
                .obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    @Operation(
            summary = "Actualizar un cliente existente",
            description = "Actualiza los datos de un cliente identificado por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        Cliente actualizado = updateClienteUseCase.actualizar(id, cliente);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(
            summary = "Eliminar un cliente",
            description = "Elimina un cliente identificado por su ID"
    )
    @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id) {
        deleteClienteUseCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
