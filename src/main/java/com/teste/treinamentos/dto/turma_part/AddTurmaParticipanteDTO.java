package com.teste.treinamentos.dto.turma_part;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTurmaParticipanteDTO {
    @NotNull
    private Integer funcionarioId;
    @NotNull
    private Integer turmaId;
}
