CREATE TABLE IF NOT EXISTS turma_participante (
    codigo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    fk_turma_cod INT UNSIGNED NOT NULL,
    fk_funcionario_cod INT UNSIGNED NOT NULL,
    PRIMARY KEY(codigo)
);

ALTER TABLE turma_participante
ADD FOREIGN KEY(fk_turma_cod) REFERENCES turma(codigo);

ALTER TABLE turma_participante
ADD FOREIGN KEY(fk_funcionario_cod) REFERENCES funcionario(codigo);