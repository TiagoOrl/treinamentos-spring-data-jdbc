package com.teste.treinamentos.dto.curso;

import com.teste.treinamentos.dto.turma.GetTurmaDTO;
import com.teste.treinamentos.entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCursoDTO {
    private Integer codigo;
    private String nome;
    private String descricao;
    private Integer duracao;
    private Integer quantidadeTurmas;
    private List<GetTurmaDTO> turmas;
    private Boolean ativo;
}
