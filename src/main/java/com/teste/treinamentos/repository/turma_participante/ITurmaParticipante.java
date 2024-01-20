package com.teste.treinamentos.repository.turma_participante;

import com.teste.treinamentos.entity.Funcionario;
import com.teste.treinamentos.entity.TurmaParticipante;

import java.util.List;

public interface ITurmaParticipante {
    List<TurmaParticipante> getTurmasByFuncionarioId(Integer funcionarioId);
    Integer insertFuncionario(Integer funcionarioId, Integer turmaId);
    Integer removeFuncionarioFromTurma(Integer funcionarioId, Integer turmaId);
    Integer removeAllByTurmaId(Integer turmaId);
}
