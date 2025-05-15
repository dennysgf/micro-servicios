# 📦 Colecciones de Postman – Microservicios

Este directorio contiene las colecciones necesarias para probar los microservicios desarrollados en este proyecto:

## 🧩 Microservicios
- **cliente-persona** – gestión de clientes.
- **cuenta-movimiento** – gestión de cuentas y movimientos financieros.

## 📂 Archivos incluidos
| Archivo | Descripción |
|--------|-------------|
| `cliente-persona.postman_collection.json` | Pruebas CRUD para el microservicio de clientes |
| `cuenta-movimiento.postman_collection.json` | Pruebas CRUD de cuentas, movimientos, y generación de reportes |

## 🚀 Cómo importar en Postman
1. Abrí Postman.
2. Click en `Import` (esquina superior izquierda).
3. Seleccioná la opción **"Upload Files"**.
4. Elegí alguno de los archivos `.postman_collection.json` de esta carpeta.
5. ¡Listo! Ya podés probar los endpoints directamente.

## 📌 Recomendaciones
- Asegurate de que los contenedores de Docker estén levantados (`docker-compose up`).
- Swagger está disponible en:
  - `http://localhost:8080/swagger-ui.html` (cliente-persona)
  - `http://localhost:8081/swagger-ui.html` (cuenta-movimiento)
- Las URLs base en Postman deben ser:
  - `http://localhost:8080` para cliente-persona
  - `http://localhost:8081` para cuenta-movimiento