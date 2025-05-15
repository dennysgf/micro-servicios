# Cuenta-Movimiento Microservicio

Este microservicio es parte de una arquitectura basada en microservicios, orientada a la gestión de cuentas bancarias y sus movimientos (depósitos y retiros). Se comunica asincrónicamente con el microservicio `cliente-persona` mediante Apache Kafka.

---

## 🔧 Tecnologías utilizadas

* Java 17
* Spring Boot 3.4.5
* Spring Data JPA
* Spring Kafka
* PostgreSQL
* Docker & Docker Compose
* Redis (para cache en el futuro)
* Lombok
* Maven

---

## 📂 Estructura del proyecto (Hexagonal)

```
cuenta-movimiento/
├── application
│   ├── dto
│   ├── port (in/out)
│   └── service
├── domain
│   └── model
├── infrastructure
│   ├── adapter
│   │   ├── in (web, messaging)
│   │   └── out (persistence)
│   └── config
└── CuentaMovimientoApplication.java
```

---

## 🚧 Endpoints expuestos

### 📅 Cuenta

* `POST /cuentas` - Crear cuenta
* `GET /cuentas/{numero}` - Obtener cuenta por número
* `PUT /cuentas/{numero}` - Actualizar cuenta
* `DELETE /cuentas/{numero}` - Eliminar cuenta
* `GET /cuentas` - Listar cuentas

### 💵 Movimiento

* `POST /movimientos` - Registrar depósito/retiro

### 📊 Reporte

* `GET /reportes?clienteId=XXX&fechaInicio=YYYY-MM-DD&fechaFin=YYYY-MM-DD`

---

## 🚩 Lógica de negocio

* Los retiros validan que exista saldo suficiente.
* El saldo se actualiza con cada movimiento.
* El reporte devuelve cuentas y movimientos por cliente y rango de fechas.
* Mensajes de error como "Saldo no disponible" se manejan mediante excepciones personalizadas.

---

## 🧳 Comunicación asincrónica

Este microservicio escucha el topic `cliente-creado-topic` mediante Kafka para recibir datos de clientes creados por el microservicio `cliente-persona`.

---

## 🚶 Pruebas

* Pruebas unitarias en `src/test/java`
* Pruebas de integración con `MockMvc`
* Casos cubiertos:

    * Crear cuenta
    * Registrar movimiento
    * Validar saldo insuficiente
    * Generar reporte

---

## 📦 Docker

Este microservicio se puede construir y ejecutar mediante:

```bash
docker build -t cuenta-movimiento .
```

Y luego:

```bash
docker run -p 8081:8081 cuenta-movimiento
```

---

## 🪥 Docker Compose

Forma parte del archivo `docker-compose.yml` general del sistema, que incluye:

* Kafka
* PostgreSQL (cuentamovimientodb)
* cliente-persona (opcional)
* cuenta-movimiento

---

## 🔍 Swagger UI

Documentación disponible en:

```
http://localhost:8081/swagger-ui.html
```

Si falla, también podés consultar:

```
http://localhost:8081/v3/api-docs
```

---

## 📁 Base de datos

La base de datos se inicializa con el nombre `cuentamovimientodb`. Las tablas se generan automáticamente:

* `cuentas`
* `movimientos`

---

## 🌐 Autor

Dennys Belduma Morocho  - 2025

---
