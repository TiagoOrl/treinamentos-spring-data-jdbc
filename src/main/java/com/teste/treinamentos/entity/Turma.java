package com.teste.treinamentos.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Turma {
    private Integer codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String localizacao;
}
