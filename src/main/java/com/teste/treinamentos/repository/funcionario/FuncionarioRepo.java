package com.teste.treinamentos.repository;

import com.teste.treinamentos.entity.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FuncionarioRepo implements IFuncionario {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FuncionarioRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Funcionario> getAll(Boolean isActive) {
        var active = isActive ? 1 : 0;
        var sql = """
                SELECT codigo, nome, cpf, nascimento, cargo, admissao, status
                FROM funcionario
                WHERE status = ?
                LIMIT 100;
                """;

        return jdbcTemplate.query(sql, new FuncionarioMapper(), active);
    }

    @Override
    public Optional<Funcionario> getById(Integer id) {
        var sql = """
                SELECT *
                FROM funcionario
                WHERE codigo = ?
                """;

        return jdbcTemplate.query(sql, new FuncionarioMapper(), id).stream().findFirst();
    }

    @Override
    public Integer insertOne(Funcionario funcionario) {
        var sql = """
                INSERT INTO funcionario(nome, cpf, nascimento, cargo, admissao, status)
                VALUES (?, ?, ?, ?, ?, 1);
                """;

        return jdbcTemplate.update(sql,
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getNascimento(),
                funcionario.getCargo(),
                funcionario.getAdmissao()
        );
    }

    @Override
    public Integer updateName(String name, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET nome = ?
                WHERE codigo = ?;
                """;
        return jdbcTemplate.update(sql, name, Id);
    }

    @Override
    public Integer updateCargo(String cargo, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET cargo = ?
                WHERE codigo = ?;
                """;
        return jdbcTemplate.update(sql, cargo, Id);
    }

    @Override
    public Integer updateCpf(String cpf, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET cpf = ?
                WHERE codigo = ?;
                """;
        return jdbcTemplate.update(sql, cpf, Id);
    }

    @Override
    public Integer updateNascimento(String dob, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET nascimento = ?
                WHERE codigo = ?;
                """;
        return jdbcTemplate.update(sql, dob, Id);
    }

    @Override
    public Integer softDeleteOne(Integer Id) {
        var sql = """
                UPDATE funcionario
                SET isActive = 0
                WHERE codigo = ?;
                """;

        return jdbcTemplate.update(sql, Id);
    }
}
