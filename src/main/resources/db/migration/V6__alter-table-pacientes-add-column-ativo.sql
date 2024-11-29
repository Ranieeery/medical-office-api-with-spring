ALTER TABLE paciente ADD ativo TINYINT;

UPDATE paciente SET ativo = 1;

ALTER TABLE paciente MODIFY ativo TINYINT NOT NULL;