CREATE TABLE IF NOT EXISTS curso (
    codigo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    descricao varchar(4000) NOT NULL,
    duracao INT NOT NULL,
    PRIMARY KEY (codigo)
);