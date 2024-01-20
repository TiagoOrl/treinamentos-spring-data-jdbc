package com.teste.treinamentos.dto.turma;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GetTurmaDTO {
    private Integer codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String localizacao;
    private Integer cursoCodigo;
}
