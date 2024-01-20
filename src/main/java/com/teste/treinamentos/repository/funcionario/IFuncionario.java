package com.teste.treinamentos.repository.funcionario;

import com.teste.treinamentos.entity.Funcionario;

import java.util.List;
import java.util.Optional;

public interface IFuncionario {
    List<Funcionario> getAll(Boolean isActive);
    Optional<Funcionario> getById(Integer id);
    Integer insertOne(Funcionario funcionario);
    Integer updateName(String name, Integer Id);
    Integer updateCargo(String cargo, Integer Id);
    Integer updateCpf(String cpf, Integer Id);
    Integer updateNascimento(String dob, Integer Id);
    Integer updateAdmissao(String admissao, Integer Id);
    Integer softDeleteOne(Integer Id);
}
