package com.teste.treinamentos.repository.funcionario;

import com.teste.treinamentos.entity.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            return jdbcTemplate.query(sql, new FuncionarioMapper(), active);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Optional<Funcionario> getById(Integer id) {
        var sql = """
                SELECT *
                FROM funcionario
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.query(sql, new FuncionarioMapper(), id).stream().findFirst();
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public List<Funcionario> getByName(String name) {
        var sql = """
                SELECT *
                FROM funcionario
                WHERE nome LIKE ?;
                """;

        return jdbcTemplate.query(sql, new FuncionarioMapper(), "%" + name + "%");
    }

    @Override
    public Integer insertOne(Funcionario funcionario) {
        var sql = """
                INSERT INTO funcionario(nome, cpf, nascimento, cargo, admissao, status)
                VALUES (?, ?, ?, ?, ?, 1);
                """;

        try {
            return jdbcTemplate.update(sql,
                    funcionario.getNome(),
                    funcionario.getCpf(),
                    funcionario.getNascimento(),
                    funcionario.getCargo(),
                    funcionario.getAdmissao()
            );
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateName(String name, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET nome = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, name, Id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateCargo(String cargo, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET cargo = ?
                WHERE codigo = ?;
                """;
        try {
            return jdbcTemplate.update(sql, cargo, Id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateCpf(String cpf, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET cpf = ?
                WHERE codigo = ?;
                """;
        try {
            return jdbcTemplate.update(sql, cpf, Id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateNascimento(String dob, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET nascimento = ?
                WHERE codigo = ?;
                """;
        try {
            return jdbcTemplate.update(sql, dob, Id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateAdmissao(String admissao, Integer Id) {
        var sql = """
                UPDATE funcionario
                SET admissao = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, admissao, Id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer softDeleteOne(Integer Id) {
        var sql = """
                UPDATE funcionario
                SET isActive = 0
                WHERE codigo = ?;
                """;
        try {
            return jdbcTemplate.update(sql, Id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
