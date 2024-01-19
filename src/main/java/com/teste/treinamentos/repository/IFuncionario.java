package com.teste.treinamentos.repository;

import com.teste.treinamentos.entity.Funcionario;

import java.util.List;
import java.util.Optional;

public interface IFuncionario {
    List<Funcionario> getAll(Boolean isActive);
    Optional<Funcionario> getById(Integer id);
    Integer insertOne(Funcionario funcionario);
    Integer softDeleteOne(Integer Id);
}
