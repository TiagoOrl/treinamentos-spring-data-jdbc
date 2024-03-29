package com.teste.treinamentos.entity;

import com.teste.treinamentos.utils.DateHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
