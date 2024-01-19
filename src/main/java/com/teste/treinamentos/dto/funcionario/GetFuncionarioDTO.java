package com.teste.treinamentos.dto.funcionario;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetFuncionarioDTO {
    Integer codigo;
    String nome;
    String cpf;
    String cargo;
    LocalDate admissao;
    LocalDate nascimento;
    Boolean status;
}
