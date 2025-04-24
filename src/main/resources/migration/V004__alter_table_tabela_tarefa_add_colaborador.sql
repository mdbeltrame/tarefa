ALTER TABLE tarefa
ADD COLUMN codigo_colaborador BIGINT;

ALTER TABLE tarefa
ADD CONSTRAINT fk_tarefa_colaborador
FOREIGN KEY (codigo_colaborador)
REFERENCES colaborador(codigo);