# Doctor appointments service
Service for scheduling doctor appointments implemented with Spring Boot, Kotlin and using Hexagonal Architecture. Doctors and patients can be created, modified and deleted. It allows to schedule, reschedule and delete appointments.

## Project structure and architecture
The code is separated into domain and adapters modules

### Domain module
This module contains bussiness logic, interfaces, no dependencies to Spring etc. When some operations are performed, domain events like PatientDeleted are published and can be handled properly without creating database relations etc. in the infrastructure implementation. With this solution it should be also easier to migrate the application to microservices if necessary.

### Adapters module
Contains RestControllers, JPA entities, event listeners etc.

## User interface
Application doesn't have any fancy user interface but there's Swagger UI available at /swagger-ui.html endpoint and auto-generated OpenAPI specification at /api-docs

## Building and running
### Building the application with gradle
```console
$ gradlew build
```
### Running the application with gradle
```console
$ gradlew bootRun
```
### Running the application with Docker
```console
$ docker-compose up
```
