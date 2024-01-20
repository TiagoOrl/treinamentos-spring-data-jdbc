package com.teste.treinamentos.repository.curso;

import com.teste.treinamentos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface ICurso {
    List<Curso> getAll();
    Optional<Curso> getById(Integer id);
    List<Curso> getByName(String name);
    Integer insertOne(Curso curso);
    Integer updateNome(String nome);
    Integer updateDescricao(String descricao);
    Integer updateDuracao(Integer duracao);

}
