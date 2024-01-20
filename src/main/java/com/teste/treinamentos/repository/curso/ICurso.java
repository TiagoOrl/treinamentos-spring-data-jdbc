package com.teste.treinamentos.repository.curso;

import com.teste.treinamentos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface ICurso {
    List<Curso> getAll();
    Optional<Curso> getById(Integer id);
    List<Curso> getByName(String name);
    Integer insertOne(Curso curso);
    Integer updateNome(String nome, Integer id);
    Integer updateDescricao(String descricao, Integer id);
    Integer updateDuracao(Integer duracao, Integer id);

}
