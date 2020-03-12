# Doctor appointments service
Service for scheduling doctor appointments implemented with Spring Boot, Kotlin and using Hexagonal Architecture.

## Project structure
Project contains domain and adapters modules.
### Domain module
Bussiness logic, interfaces, no dependencies to Spring etc.
### Adapters module
Contains RestControllers, JPA entities etc.

## User interface
Application doesn't have any fancy user interface but there's Swagger UI available at /swagger-ui.html endpoint and auto-generated OpenAPI specification at /api-docs
