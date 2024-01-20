package com.teste.treinamentos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    private Integer codigo;
    private String nome;
    private String descricao;
    private Integer duracao;
}
