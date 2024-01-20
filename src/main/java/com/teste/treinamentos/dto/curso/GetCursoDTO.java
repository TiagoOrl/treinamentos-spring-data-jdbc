package com.teste.treinamentos.dto.curso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCursoDTO {
    private Integer codigo;
    private String nome;
    private String descricao;
    private Integer duracao;
}
