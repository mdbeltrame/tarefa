CREATE TABLE colaborador (
    codigo BIGINT PRIMARY KEY DEFAULT nextval('colaborador_sequence'),
    nome VARCHAR(255) NOT NULL
);

CREATE SEQUENCE colaborador_sequence START WITH 1 INCREMENT BY 1;