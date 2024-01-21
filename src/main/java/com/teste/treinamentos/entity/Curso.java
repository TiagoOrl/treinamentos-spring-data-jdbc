package com.teste.treinamentos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    private Integer codigo;
    private String nome;
    private String descricao;
    private Integer duracao;
    private Boolean ativo;
    private Integer quantidadeTurmas;
    private List<Turma> turmas;
}
