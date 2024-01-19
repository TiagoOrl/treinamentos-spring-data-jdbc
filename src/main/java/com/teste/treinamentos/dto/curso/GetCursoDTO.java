package com.teste.treinamentos.dto.curso;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCursoDTO {
    private Integer codigo;
    private String nome;
    private String descricao;
    private Integer duracao;
}
