package com.teste.treinamentos.repository.turma;

import com.teste.treinamentos.entity.Turma;

import java.util.List;
import java.util.Optional;

public interface ITurma {
    List<Turma> getAll();
    Optional<Turma> getById(Integer id);
    List<Turma> getAllByCourseId(Integer courseId);
    List<Turma> getAllByCourseIdAndFuncionarioId(Integer courseId, Integer funcionarioId);
    Integer insertOne(Turma turma);
    Integer deleteOneById(Integer id);
    Integer deleteAllByCourseId(Integer courseId);
}
