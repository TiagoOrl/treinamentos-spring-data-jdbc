CREATE TABLE IF NOT EXISTS turma (
    codigo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    inicio DATE NOT NULL,
    fim DATE NOT NULL,
    localizacao varchar(200),
    fk_curso_cod INT UNSIGNED NOT NULL,
    PRIMARY KEY(codigo)
);

ALTER TABLE turma
ADD FOREIGN KEY (fk_curso_cod) REFERENCES curso(codigo);