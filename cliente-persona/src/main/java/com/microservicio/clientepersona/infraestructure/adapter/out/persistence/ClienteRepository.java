package com.microservicio.clientepersona.infraestructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String > {
    Optional<ClienteEntity> findByIdentificacion(String identificacion);
}

