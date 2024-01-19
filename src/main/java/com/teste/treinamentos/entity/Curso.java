package com.teste.treinamentos.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Curso {
    private Integer codigo;
    private String nome;
    private String descricao;
    private Integer duracao;
}
