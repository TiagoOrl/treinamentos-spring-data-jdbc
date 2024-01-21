package com.teste.treinamentos.repository.turma;

import com.teste.treinamentos.entity.Turma;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITurma {
    List<Turma> getAll();
    List<Turma> getAllByPeriod(String start, String end);
    Optional<Turma> getById(Integer id);
    List<Turma> getAllByCourseId(Integer courseId);
    List<Turma> getAllByCourseIdAndFuncionarioId(Integer courseId, Integer funcionarioId);
    Integer insertOne(Turma turma);
    Integer deleteOneById(Integer id);
    Integer deleteAllByCourseId(Integer courseId);
}
