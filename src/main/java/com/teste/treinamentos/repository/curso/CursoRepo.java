package com.teste.treinamentos.repository.curso;

import com.teste.treinamentos.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepo implements ICurso {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CursoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Curso> getAll(Boolean ativo) {
        var sql = """
                SELECT *
                FROM curso
                WHERE ativo = ?
                LIMIT 100;
                """;

        return jdbcTemplate.query(sql, new CursoMapper(), ativo);
    }

    @Override
    public Optional<Curso> getById(Integer id, Boolean ativo) {
        var sql = """
                SELECT *
                FROM curso
                WHERE codigo = ? AND ativo = ?;
                """;

        try {
            return jdbcTemplate.query(sql, new CursoMapper(), id, ativo)
                    .stream().findFirst();
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public List<Curso> getByName(String name, Boolean ativo) {
        var sql = """
                SELECT *
                FROM curso
                WHERE nome LIKE ? AND ativo = ?;
                """;

        return jdbcTemplate.query(
                sql,
                new CursoMapper(),
                "%" + name + "%",
                ativo);
    }

    @Override
    public Integer insertOne(Curso curso) {
        var sql = """
                INSERT INTO curso (nome, descricao, duracao)
                VALUES (?, ?, ?)
                """;

        try {
            return jdbcTemplate.update(
                    sql,
                    curso.getNome(),
                    curso.getDescricao(),
                    curso.getDuracao()
            );
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateNome(String nome, Integer id) {
        var sql = """
                UPDATE curso
                SET nome = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, nome, id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateDescricao(String descricao, Integer id) {
        var sql = """
                UPDATE curso
                SET descricao = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, descricao, id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateDuracao(Integer duracao, Integer id) {
        var sql = """
                UPDATE curso
                SET duracao = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, duracao, id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateStatus(Boolean status, Integer courseId) {
        var sql = """
                UPDATE curso
                SET ativo = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, status, courseId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        var sql = """
                DELETE FROM curso
                WHERE codigo = ?
                """;

        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
