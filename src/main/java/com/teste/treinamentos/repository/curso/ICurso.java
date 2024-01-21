package com.teste.treinamentos.repository.curso;

import com.teste.treinamentos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface ICurso {
    List<Curso> getAll(Boolean ativo);
    Optional<Curso> getById(Integer id, Boolean ativo);
    List<Curso> getByName(String name, Boolean ativo);
    Integer insertOne(Curso curso);
    Integer updateNome(String nome, Integer id);
    Integer updateDescricao(String descricao, Integer id);
    Integer updateDuracao(Integer duracao, Integer id);
    Integer updateStatus(Boolean status, Integer courseId);
    Integer deleteById(Integer id);

}
