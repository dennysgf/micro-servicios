-- =====================================
-- CREACIÓN DE BASES DE DATOS
-- =====================================
CREATE DATABASE clientepersonadb;
CREATE DATABASE cuentamovimientodb;

-- =====================================
-- ESQUEMA PARA clientepersonadb
-- =====================================
\connect clientepersonadb;

CREATE TABLE clientes (
    id VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(10) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(100),
    password VARCHAR(100) NOT NULL
);

-- =====================================
-- ESQUEMA PARA cuentamovimientodb
-- =====================================
\connect cuentamovimientodb;

CREATE TABLE cuentas (
    numero_cuenta VARCHAR(20) PRIMARY KEY,
    tipo_cuenta VARCHAR(20) NOT NULL,
    saldo_inicial DOUBLE PRECISION NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id VARCHAR(20) NOT NULL
);

CREATE TABLE movimientos (
    id UUID PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(20) NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    saldo DOUBLE PRECISION NOT NULL,
    numero_cuenta VARCHAR(20) NOT NULL,
    FOREIGN KEY (numero_cuenta) REFERENCES cuentas(numero_cuenta)
);

CREATE INDEX idx_cuenta_cliente ON cuentas(cliente_id);
CREATE INDEX idx_movimiento_fecha ON movimientos(fecha);
