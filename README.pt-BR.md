# Consultório Médico API

[Leia  em inglês](README.md)

API Rest para gerenciamento de consultório médico desenvolvida com Spring Boot e MySQL.

## Visão Geral do Projeto

Esta API fornece endpoints para gerenciamento de médicos, pacientes e consultas em um sistema de clínica médica, com autenticação JWT e operações CRUD completas.

## Funcionalidades

- Autenticação JWT
- Gerenciamento de médicos
- Registros de pacientes
- Agendamento de consultas
- Validação de entrada
- Tratamento de erros
- Integração com MySQL
- Migrações Flyway

## Tecnologias

- Java
- Spring Boot 3
- Spring Data JPA
- Hibernate
- Maven
- MySQL
- Flyway
- Lombok
- JUnit
- Mockito

## Pré-requisitos

- JDK 23
- Maven 3.8+
- MySQL 8.0+ Server rodando na porta 3306
- Banco de dados criado

## Configuração

1. Clone o repositório

2. Crie os bancos de dados:

```shell
CREATE DATABASE api;
CREATE DATABASE api_test;
```

3. Configure as propriedades do banco em `application.properties`:

```properties
   spring.datasource.url=
   spring.datasource.username=
   spring.datasource.password=
```

4. Build do projeto:

```shell
mvn clean install
```

5. Execute a aplicação:

```shell
mvn spring-boot:run
```

## Documentation

A documentação completa da API está em /swagger-ui.html quando a aplicação estiver em execução.

### Principais Endpoints

#### Autenticação

- `POST /login` - Login para obter token JWT

#### Doctors

- `POST /medicos` - Cadastrar novo médico
- `GET /medicos` - Listar médicos ativos
- `PUT /medicos` - Atualizar dados do médico
- `DELETE /medicos/{id}` - Excluir médico

#### Patients

- `POST /pacientes` - Cadastrar novo paciente
- `GET /pacientes` - Listar pacientes ativos
- `PUT /pacientes` - Atualizar dados do paciente
- `DELETE /pacientes/{id}` - Excluir paciente

#### Consultas

- `POST /consultas` - Agendar consulta

### Segurança

- Autenticação via JWT Token
- Endpoints protegidos necessitam do header Authorization: `Bearer {token}`

## Estrutura do Projeto

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

## Testes

Execute os testes com:

```shell
mvn test
```

## Deploy

1. Build do projeto:

```shell
mvn package -f pom.xml
```

2. Execute a aplicação:

```shell
java "-Dspring.profiles.active=prod" "-DDATABASE_URL=" "-DDATABASE_USERNAME=" "-DDATABASE_PASSWORD=" -jar API-0.0.1-SNAPSHOT.jar
```
