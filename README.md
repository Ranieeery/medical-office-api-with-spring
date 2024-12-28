# Medical Clinic API

[Read this in Portuguese](README.pt-BR.md)

A REST API for medical clinic management built with Spring Boot and MySQL.

## Project Overview

This API provides endpoints for managing doctors, patients, and appointments in a medical clinic system, featuring JWT authentication and complete CRUD operations.

## Features

- JWT Authentication
- Doctor management
- Patient records
- Appointment scheduling
- Input validation
- Error handling
- MySQL integration
- Flyway migrations

## Tech Stack

- Java 23
- Spring Boot 3
- Spring Data JPA
- Hibernate
- Maven
- MySQL
- Flyway
- Lombok
- JUnit
- Mockito

## Prerequisites

- JDK 23
- Maven 3.8+
- MySQL 8.0+ Server running on port 3306
- Database created

## Getting Started

1. Clone the repository

2. Create database:

```shell
CREATE DATABASE api;
CREATE DATABASE api_test;
```

3. Configure database properties in `application.properties`:

```properties
   spring.datasource.url=
   spring.datasource.username=
   spring.datasource.password=
```

4. Build the project:

```shell
mvn clean install
```

5. Run the application:

```shell
mvn spring-boot:run
```

## Documentation

Full API documentation is available at /swagger-ui.html when the application is running.

### Main Endpoints

#### Authentication

- `POST /login` - Login to obtain JWT token

#### Doctors

- `POST /medicos` - Register new doctor
- `GET /medicos` - List active doctors
- `PUT /medicos` - Update doctor info
- `DELETE /medicos/{id}` - Delete doctor

#### Patients

- `POST /pacientes` - Register new patient
- `GET /pacientes` - List active patients
- `PUT /pacientes` - Update patient info
- `DELETE /pacientes/{id}` - Delete patient

#### Appointments

- `POST /consultas` - Schedule appointment

### Security

- JWT Token authentication
- Protected endpoints require Authorization header: `Bearer {token}`

## Project Structure

```structure
src/
├── main/
│   ├── java/
│   │   └── dev/raniery/med/voll/api/
│   │       ├── controller/
│   │       ├── domain/
│   │       ├── infra/
│   │       └── user/
│   └── resources/
│       └── db/migration/
└── test/
    └── java/
```

## Tests

Run tests with:

```shell
mvn test
```

## Deployment

1. Build the project:

```shell
mvn package -f pom.xml
```

2. Run the application:

```shell
java "-Dspring.profiles.active=prod" "-DDATABASE_URL=" "-DDATABASE_USERNAME=" "-DDATABASE_PASSWORD=" -jar API-0.0.1-SNAPSHOT.jar
```
