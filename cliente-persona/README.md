# Cliente-Persona Microservicio

Este microservicio es responsable de la gestión de información de los clientes y sus datos personales. Forma parte de una arquitectura basada en microservicios y se comunica asincrónicamente con `cuenta-movimiento` mediante Apache Kafka.

---

## 🔧 Tecnologías utilizadas

* Java 17
* Spring Boot 3.4.5
* Spring Data JPA
* Spring Kafka
* PostgreSQL
* Docker & Docker Compose
* Lombok
* Maven

---

## 📂 Estructura del proyecto (Hexagonal)

```
cliente-persona/
├── application
│   └── port (in/out)
├── domain
│   └── model
├── infrastructure
│   ├── adapter
│   │   ├── in (web)
│   │   └── out (persistence, messaging)
│   └── config
└── ClientePersonaApplication.java
```

---

## 🚧 Endpoints expuestos

* `POST /clientes` - Crear cliente
* `GET /clientes/{id}` - Obtener cliente por ID
* `PUT /clientes/{id}` - Actualizar cliente
* `DELETE /clientes/{id}` - Eliminar cliente
* `GET /clientes` - Listar clientes

---

## 🚩 Lógica de negocio

* Validaciones personalizadas:

    * El nombre no debe contener números.
    * La identificación debe ser única y de máximo 10 caracteres.
    * La contraseña no puede estar vacía.

* Al crear un cliente se publica un evento Kafka al topic `cliente-creado-topic`.

---

## 🧳 Comunicación asincrónica

Publica eventos al topic Kafka `cliente-creado-topic`, consumido por el microservicio `cuenta-movimiento`.

---

## 🚶 Pruebas

* Pruebas unitarias y de integración en `src/test/java`
* Cobertura:

    * Crear cliente
    * Validaciones de campos
    * Actualizar, eliminar y consultar cliente

---

## 📦 Docker

```bash
docker build -t cliente-persona .
docker run -p 8080:8080 cliente-persona
```

---

## 🪥 Docker Compose

Incluido en el `docker-compose.yml` general del sistema junto con:

* Kafka
* PostgreSQL (clientepersonadb)
* cuenta-movimiento (para pruebas cruzadas)

---

## 🔍 Swagger UI

Documentación disponible en:

```
http://localhost:8080/swagger-ui.html
```

Y su especificación OpenAPI:

```
http://localhost:8080/v3/api-docs
```

---

## 📁 Base de datos

Nombre de la base: `clientepersonadb`.
Tablas principales:

* `clientes`

---

## 🌐 Autor

Dennys Belduma Morocho - 2025

---
