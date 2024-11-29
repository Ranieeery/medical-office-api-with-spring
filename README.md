# Spring API REST

This is a RESTful API for managing medical and patient data. It is built with Spring Boot and connects to a MySQL database.

## Project Structure

## Technologies

- Java
- Spring Boot 3
- Spring Data JPA
- Hibernate
- Maven
- MySQL
- Flyway
- Lombok

## Prerequisites

- JDK 23
- Maven
- MySQL server running on `localhost:3306` with a database named api

## Configuration

Database connection properties are configured in application.properties:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

## Routes

### MedicoController

- `POST /medicos` - Register a new doctor
- `GET /medicos` - List all active doctors
- `PUT /medicos` - Update doctor information
- `DELETE /medicos/{id}` - Delete a doctor by ID

### PacienteController

- `POST /pacientes` - Register a new patient
- `GET /pacientes` - List all active patients
- `PUT /pacientes` - Update patient information
- `DELETE /pacientes/{id}` - Delete a patient by ID
