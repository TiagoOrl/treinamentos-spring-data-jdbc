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
                VALUES (?, ?, ?, ?, ?, ?);
                """;

        return jdbcTemplate.update(sql,
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getNascimento(),
                funcionario.getCargo(),
                funcionario.getAdmissao(),
                funcionario.getStatus()
        );
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
