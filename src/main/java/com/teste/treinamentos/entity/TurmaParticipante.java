package com.teste.treinamentos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaParticipante {
    private Integer codigo;
    private Integer codigoTurma;
    private Integer codigoFuncionario;
}
