# 🧩 Microservicios: Cliente-Persona y Cuenta-Movimiento

Este proyecto implementa una arquitectura de microservicios utilizando Spring Boot, PostgreSQL y Apache Kafka para manejar la gestión de clientes y sus cuentas bancarias, con registro de movimientos y generación de reportes.

---

## 📁 Estructura del Proyecto

```
micro-servicios/
│
├── cliente-persona/                # Microservicio para la gestión de clientes
├── cuenta-movimiento/             # Microservicio para gestión de cuentas y movimientos
│
├── postman/
│   └── microservicios.postman_collection.json
│
├── docker-compose.yml             # Orquestación de servicios
├── init-multiple-dbs.sql          # Script SQL para crear las bases de datos
└── BaseDatos.sql                  # Estructura de tablas y datos (opcional)
```

---

## 🐳 Despliegue con Docker

1. Asegúrate de tener Docker y Docker Compose instalados.
2. Posiciónate en la raíz del proyecto (`micro-servicios/`).
3. Ejecuta el siguiente comando:

```bash
docker-compose up --build
```

Esto levantará los siguientes servicios:

- PostgreSQL con dos bases de datos: `clientepersonadb` y `cuentamovimientodb`
- Apache Kafka + Zookeeper
- Microservicio `cliente-persona` en `http://localhost:8080`
- Microservicio `cuenta-movimiento` en `http://localhost:8081`

---

## 🧪 Pruebas

Utiliza Postman con la colección incluida:

**Ruta:** `postman/microservicios.postman_collection.json`

Importa la colección en Postman para probar endpoints como:

- `POST /clientes`
- `GET /clientes/{id}`
- `POST /cuentas`
- `POST /movimientos`
- `GET /reportes`

---

## 📚 Documentación Swagger

Después de iniciar los servicios, accede a:

- [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html) — Cliente Persona
- [`http://localhost:8081/swagger-ui.html`](http://localhost:8081/swagger-ui.html) — Cuenta Movimiento

---

## ⚙️ Tecnologías

- Java 17
- Spring Boot 3
- PostgreSQL
- Apache Kafka
- Docker y Docker Compose
- SpringDoc (Swagger)
