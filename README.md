# Spring API REST

API Rest para gerenciamento de consultório médico desenvolvida com Spring Boot e MySQL.

## Estrutura do Projeto

```a
src/
├── main/
│   ├── java/
│   │   └── dev/raniery/med/voll/api/
│   │       ├── controller/
│   │       ├── domain/
│   │       └── infra/
│   │       └── user/
│   └── resources/
│       └── db/migration/
└── test/
    └── java/
```

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
- Maven
- MySQL rodando na porta 3306 com banco de dados

## Configuração

Propriedades de conexão com banco de dados em `application.properties`:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

## Rotas

### Médicos

- `POST /medicos` - Cadastrar novo médico
- `GET /medicos` - Listar médicos ativos
- `PUT /medicos` - Atualizar dados do médico
- `DELETE /medicos/{id}` - Excluir médico

### Pacientes

- `POST /pacientes` - Cadastrar novo paciente
- `GET /pacientes` - Listar pacientes ativos
- `PUT /pacientes` - Atualizar dados do paciente
- `DELETE /pacientes/{id}` - Excluir paciente

### Consultas

- `POST /consultas` - Agendar consulta

### Autenticação

- `POST /login` - Login para obter token JWT

## Documentação

A documentação completa da API pode ser acessada em `/swagger-ui.html` após iniciar a aplicação.

## Segurança

- Autenticação via JWT Token
- Endpoints protegidos necessitam do header Authorization: Bearer {token}

## Deploy

```shell
mvn package -f pom.xml
```

```shell
java "-Dspring.profiles.active=prod" "-DDATABASE_URL=" "-DDATABASE_USERNAME=" "-DDATABASE_PASSWORD=" -jar API-0.0.1-SNAPSHOT.jar
```
