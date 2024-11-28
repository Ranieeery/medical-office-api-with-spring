CREATE TABLE paciente
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    nome        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    telefone    VARCHAR(20)  NOT NULL,
    cpf         VARCHAR(11)  NOT NULL UNIQUE,
    logradouro  VARCHAR(100) NOT NULL,
    bairro      VARCHAR(100) NOT NULL,
    cep         VARCHAR(9)   NOT NULL,
    cidade      VARCHAR(100) NOT NULL,
    uf          VARCHAR(2)   NOT NULL,
    numero      VARCHAR(20)  NOT NULL,
    complemento VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);