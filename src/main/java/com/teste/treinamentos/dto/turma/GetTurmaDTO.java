package com.teste.treinamentos.dto.turma;

import com.teste.treinamentos.entity.Funcionario;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class GetTurmaDTO {
    private Integer codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String localizacao;
    private Integer cursoCodigo;
    private List<Funcionario> participantes;
    private Integer quantidadeParticipantes;
}
