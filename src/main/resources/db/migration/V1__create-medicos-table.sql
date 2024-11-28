CREATE TABLE medico
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    nome          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    crm           VARCHAR(6)   NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    logradouro    VARCHAR(100) NOT NULL,
    bairro        VARCHAR(100) NOT NULL,
    cep           VARCHAR(9)   NOT NULL,
    cidade        VARCHAR(100) NOT NULL,
    uf            VARCHAR(2)   NOT NULL,
    numero        VARCHAR(20)  NOT NULL,
    complemento   VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);