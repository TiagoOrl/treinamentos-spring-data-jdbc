package com.teste.treinamentos.entity;

import com.teste.treinamentos.utils.DateHelper;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Funcionario {
    private Integer codigo;
    private String nome;
    private String cpf;
    private String cargo;
    private LocalDate admissao;
    private LocalDate nascimento;
    private Boolean status;

    public void setAdmissao(String date) {
        this.admissao = DateHelper.convertToLocalDate(date);
    }

    public void setNascimento(String date) {
        this.nascimento = DateHelper.convertToLocalDate(date);
    }
}
