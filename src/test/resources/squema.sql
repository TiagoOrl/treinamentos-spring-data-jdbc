CREATE TABLE IF NOT EXISTS funcionario (
    codigo INT NOT NULL AUTO_INCREMENT,
    nome varchar(200) NOT NULL,
    cpf char(11) UNIQUE NOT NULL,
    nascimento DATE NOT NULL,
    cargo varchar(200) NOT NULL,
    admissao DATE NOT NULL,
    status BIT NOT NULL,
    PRIMARY KEY (codigo)
);