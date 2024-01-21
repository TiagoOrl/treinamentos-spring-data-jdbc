package com.teste.treinamentos.entity;

import com.teste.treinamentos.utils.DateHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turma {
    private Integer codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String localizacao;
    private Integer cursoCodigo;
    private List<Funcionario> participantes;
    private Integer quantidadeParticipantes;


    public void setInicio(String date) {
        this.inicio = DateHelper.convertToLocalDate(date);
    }

    public void setFim(String date) {
        this.fim = DateHelper.convertToLocalDate(date);
    }

}
