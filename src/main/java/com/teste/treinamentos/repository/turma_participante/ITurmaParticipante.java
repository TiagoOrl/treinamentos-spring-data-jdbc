package com.teste.treinamentos.repository.turma_participante;

import com.teste.treinamentos.entity.TurmaParticipante;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITurmaParticipante {
    List<TurmaParticipante> getTurmasByFuncionarioId(Integer funcionarioId);
    List<TurmaParticipante> getAllByTurmaId(Integer turmaId);
    Integer insertFuncionario(Integer funcionarioId, Integer turmaId);
    Integer removeFuncionarioFromTurma(Integer funcionarioId, Integer turmaId);
    Integer removeAllStudentsByTurmaId(Integer turmaId);
    Integer removeStudentFromAllTurmas(Integer funcionarioId);
    Optional<TurmaParticipante> checkIfTurmaContainsFuncionario(Integer turmaId, Integer funcionarioId);
}
