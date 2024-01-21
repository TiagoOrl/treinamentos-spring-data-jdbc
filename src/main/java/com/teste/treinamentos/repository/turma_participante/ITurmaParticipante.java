package com.teste.treinamentos.repository.turma_participante;

import com.teste.treinamentos.entity.TurmaParticipante;

import java.util.List;
import java.util.Optional;

public interface ITurmaParticipante {
    List<TurmaParticipante> getTurmasByFuncionarioId(Integer funcionarioId);
    List<TurmaParticipante> getAllByTurmaId(Integer turmaId);
    Integer insertFuncionario(Integer funcionarioId, Integer turmaId);
    Integer removeFuncionarioFromTurma(Integer funcionarioId, Integer turmaId);
    Integer removeAllByTurmaId(Integer turmaId);
    Optional<TurmaParticipante> checkIfTurmaContainsFuncionario(Integer turmaId, Integer funcionarioId);
}
