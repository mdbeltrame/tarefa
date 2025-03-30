CREATE SEQUENCE tarefa_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE tarefa (
    codigo BIGINT PRIMARY KEY DEFAULT nextval('tarefa_sequence'),
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    status VARCHAR(50) NOT NULL,
    data_criacao DATE NOT NULL
);