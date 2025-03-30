CREATE SEQUENCE usuario_sequence
  INCREMENT BY 1
  START WITH 1
  CACHE 1;

CREATE TABLE usuario (
    codigo BIGINT PRIMARY KEY DEFAULT nextval('usuario_sequence'),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);